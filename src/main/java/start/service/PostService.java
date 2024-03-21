package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.PostRequestDTO;
import start.dto.response.BuyArtworkResponseDTO;
import start.dto.response.UserResponseDTO;
import start.entity.*;
import start.enums.StatusEnum;
import start.enums.TransactionEnum;
import start.repository.*;
import start.utils.AccountUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class PostService {
@Autowired
    AccountUtils accountUtils;
@Autowired
    WalletRepository walletRepository;
@Autowired
    TransactionRepository transactionRepository;
@Autowired
    UserRepository userRepository;
@Autowired
    SystemProfitRepository systemProfitRepository;

     @Autowired
    ArtworkRepository artworkRepository;
    @Autowired
    EmailService emailService;

     public String getDate(){
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
         LocalDateTime createDate = LocalDateTime.now();
         String formattedCreateDate = createDate.format(formatter);
         return formattedCreateDate;
     }

    public UserResponseDTO buyPost(PostRequestDTO postRequestDTO) {
        String formattedCreateDate = getDate();

        User user2 = accountUtils.getCurrentUser();
        User user = userRepository.findUserById(user2.getId());
        Wallet wallet = walletRepository.findWalletByUser_Id(user.getId());

        if(wallet.getBalance() >= postRequestDTO.getMoney()) {

        user.setPostQuantity(user.getPostQuantity()+postRequestDTO.getQuantity());

        Transaction transaction = new Transaction();
        transaction.setDescription(postRequestDTO.getDescription());
        transaction.setAmount(postRequestDTO.getMoney());
        transaction.setTransactionDate(formattedCreateDate);
        transaction.setTransactionType(TransactionEnum.BUYPOST);
        transaction.setFrom(wallet);

        wallet.setBalance(wallet.getBalance()-postRequestDTO.getMoney());

            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setRole(user.getRole());
            userResponseDTO.setId(user.getId());
            userResponseDTO.setName(user.getName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setAvt(user.getAvt());
            userResponseDTO.setPostQuantity(user.getPostQuantity());
            userResponseDTO.setArtworks(user.getArtworks());
            userResponseDTO.setWallet(user.getWallet());

            walletRepository.save(wallet);
            transactionRepository.save(transaction);
            userRepository.save(user);
            SystemProfit systemProfit = new SystemProfit();
            systemProfit.setBalance(postRequestDTO.getMoney());
            systemProfit.setDescription("Buy Post");
            systemProfit.setDate(getDate());
            systemProfit.setTransaction(transaction);
            systemProfitRepository.save(systemProfit);
            return userResponseDTO;
        }
        else{
            throw new RuntimeException("Account has insufficient balance");
        }
    }

    public BuyArtworkResponseDTO buyArtwork(long id) {
        BuyArtworkResponseDTO buyArtworkResponseDTO = new BuyArtworkResponseDTO();
        String formattedCreateDate = getDate();
        Artwork artwork = artworkRepository.findById(id);
      if(artwork.getStatus().equals(StatusEnum.ACTIVE)){
          User user = accountUtils.getCurrentUser();
          User audience = userRepository.findUserById(user.getId());
          User creator = userRepository.findUserById(artwork.getUser().getId());
          Transaction transaction = new Transaction();
          transaction.setAmount(artwork.getPrice());
          transaction.setTransactionType(TransactionEnum.PENDING);
          transaction.setArtworkID(artwork.getId());
          transaction.setTransactionDate(formattedCreateDate);
          transaction.setFrom(audience.getWallet());
          transaction.setTo(creator.getWallet());
          transaction.setDescription("Buy Artwork");
          Transaction transactionReturn = transactionRepository.save(transaction);
          buyArtworkResponseDTO.setArtwork(artwork);
          buyArtworkResponseDTO.setTransaction(transactionReturn);
      }else{
          throw new RuntimeException("The artwork does not exist");
      }
      return buyArtworkResponseDTO;
    }


    public void threadSendMail(User user,String subject, String description){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                emailService.sendMail(user,subject,description);
            }

        };
        new Thread(r).start();
    }

    public Transaction checkOut(UUID id) {
         Transaction transaction = transactionRepository.findByTransactionID(id);
         Artwork artwork = artworkRepository.findById((long)transaction.getArtworkID());
        User audience = accountUtils.getCurrentUser();

        if(artwork.getStatus().equals(StatusEnum.ACTIVE)){

            if(audience.getWallet().getBalance() >= artwork.getPrice()){
                Wallet walletFrom = transaction.getFrom();
                Wallet walletTo = transaction.getTo();
                walletFrom.setBalance(walletFrom.getBalance()-artwork.getPrice());
                walletTo.setBalance(walletTo.getBalance()+artwork.getPrice());
                transaction.setTransactionType(TransactionEnum.BUYARTWORK);
                artwork.setStatus(StatusEnum.DONE);
                walletRepository.save(walletFrom);
                walletRepository.save(walletTo);
                transactionRepository.save(transaction);
                artworkRepository.save(artwork);
                threadSendMail(artwork.getUser(),"Professional artwork sale" , "artwork " +artwork.getTitle()+ " has been sold");
                threadSendMail(audience,"Buy Artwork Pro" , "Link Artwork "+artwork.getTitle()+"\n "+ artwork.getImage());
            }else{
                throw new RuntimeException("wallet does not have enough balance");
            }
        }else{
            throw new RuntimeException("The artwork does not exist");
        }
       return transaction;
    }
}

package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.PostRequestDTO;
import start.dto.response.UserResponseDTO;
import start.entity.SystemProfit;
import start.entity.Transaction;
import start.entity.User;
import start.entity.Wallet;
import start.enums.TransactionEnum;
import start.repository.SystemProfitRepository;
import start.repository.TransactionRepository;
import start.repository.UserRepository;
import start.repository.WalletRepository;
import start.utils.AccountUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public UserResponseDTO buyPost(PostRequestDTO postRequestDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);

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
            SystemProfit systemProfit = systemProfitRepository.findById(1L);
            systemProfit.setBalance(systemProfit.getBalance()+postRequestDTO.getMoney());
            systemProfitRepository.save(systemProfit);
            return userResponseDTO;
        }
        else{
            throw new RuntimeException("Account has insufficient balance");
        }
    }

}

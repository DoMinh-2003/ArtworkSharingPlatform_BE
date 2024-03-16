package start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.entity.Transaction;
import start.entity.User;

import start.entity.Wallet;
import start.repository.TransactionRepository;
import start.repository.WalletRepository;
import start.utils.AccountUtils;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    AccountUtils accountUtils;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletRepository walletRepository;


    public List<Transaction> getTransactionById() {
//        User user = accountUtils.getCurrentUser();
//        List<Transaction> transactions = transactionRepository.findTransactionByUserId(user.getId());
//        //List<OrderRequest> orderRequests = orderRequestRepository.findOrderRequestByUserId(user.getId());
//        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
//       List<Artwork> artworks= new ArrayList<Artwork>();
//        List<OrderRequest> orderRequests= new ArrayList<OrderRequest>();
//        for (Transaction transaction : transactions) {
//            OrderRequest orders = orderRequestRepository.findOrderRequestById(transaction.getOrderID());
//            Artwork artwork = artworkRepository.findById(transaction.getArtworkID());
//            artworks.add(artwork);
//            OrderRequest orderRequest = orderRequestRepository.findOrderRequestByUserId(transaction.getOrderID());
//            orderRequests.add(orderRequest);
//            transactionResponseDTO.setTransaction(transaction);
//            transactionResponseDTO.setArtwork(artwork);
//            transactionResponseDTO.setOrderRequest(orders);
        User user = accountUtils.getCurrentUser();
        Wallet wallet = walletRepository.findWalletByUser_Id(user.getId());
      return transactionRepository.findTransactionsByFrom_IdOrTo_Id(wallet.getWalletID());
    }
}

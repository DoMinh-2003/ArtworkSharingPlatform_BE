package start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.response.TransactionResponseDTO;
import start.entity.Transaction;
import start.entity.User;

import start.entity.Wallet;
import start.repository.TransactionRepository;
import start.repository.UserRepository;
import start.repository.WalletRepository;
import start.utils.AccountUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    AccountUtils accountUtils;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletRepository walletRepository;

@Autowired
UserRepository userRepository;

    public List<TransactionResponseDTO> getTransactionById() {
        List<TransactionResponseDTO> listTransactionResponseDTO = new ArrayList<>();
        User user = accountUtils.getCurrentUser();
        Wallet wallet = walletRepository.findWalletByUser_Id(user.getId());
        List<Transaction> transactions = transactionRepository.findTransactionsByFrom_IdOrTo_Id(wallet.getWalletID());
        for (Transaction transaction : transactions) {
            TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
            transactionResponseDTO.setTransactionID(transaction.getTransactionID());
            transactionResponseDTO.setTransactionType(transaction.getTransactionType());
            transactionResponseDTO.setAmount(transaction.getAmount());
            transactionResponseDTO.setDescription(transaction.getDescription());
            transactionResponseDTO.setTransactionDate(transaction.getTransactionDate());
            transactionResponseDTO.setArtworkID(transaction.getArtworkID());
            transactionResponseDTO.setOrderID(transaction.getOrderID());
            transactionResponseDTO.setFrom(transaction.getFrom());
            transactionResponseDTO.setTo(transaction.getTo());
            if(transaction.getFrom() != null){
                transactionResponseDTO.setUserFrom(transaction.getFrom().getUser());
            }
            if(transaction.getTo() != null){
                transactionResponseDTO.setUserTo(transaction.getTo().getUser());
            }
            listTransactionResponseDTO.add(transactionResponseDTO);
        }
      return listTransactionResponseDTO;
    }
}

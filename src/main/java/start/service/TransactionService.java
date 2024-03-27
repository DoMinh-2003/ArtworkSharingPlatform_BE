package start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.response.TransactionResponseDTO;
import start.entity.Transaction;
import start.entity.User;

import start.entity.Wallet;
import start.repository.*;
import start.utils.AccountUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    AccountUtils accountUtils;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    OrderRequestRepository orderRequestRepository;

    @Autowired
    ArtworkRepository artworkRepository;

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
            if(transaction.getOrderID() != null){
                transactionResponseDTO.setOrder(orderRequestRepository.findOrderRequestById(transaction.getOrderID()));
            }
            if(transaction.getArtworkID() != null){
                transactionResponseDTO.setArtwork(artworkRepository.findById((long)transaction.getArtworkID()));
            }
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
        listTransactionResponseDTO = listTransactionResponseDTO.stream()
                .sorted(Comparator.comparing(TransactionResponseDTO::getTransactionDate).reversed())
                .collect(Collectors.toList());

      return listTransactionResponseDTO;
    }

    public List<TransactionResponseDTO> allTransactions() {
        List<TransactionResponseDTO> listTransactionResponseDTO = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findAll();
        for (Transaction transaction : transactions) {
            TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
            transactionResponseDTO.setTransactionID(transaction.getTransactionID());
            transactionResponseDTO.setTransactionType(transaction.getTransactionType());
            transactionResponseDTO.setAmount(transaction.getAmount());
            transactionResponseDTO.setDescription(transaction.getDescription());
            transactionResponseDTO.setTransactionDate(transaction.getTransactionDate());
            if(transaction.getOrderID() != null){
                transactionResponseDTO.setOrder(orderRequestRepository.findOrderRequestById(transaction.getOrderID()));
            }
            if(transaction.getArtworkID() != null){
                transactionResponseDTO.setArtwork(artworkRepository.findById((long)transaction.getArtworkID()));
            }
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
        listTransactionResponseDTO = listTransactionResponseDTO.stream()
                .sorted(Comparator.comparing(TransactionResponseDTO::getTransactionDate).reversed())
                .collect(Collectors.toList());

        return listTransactionResponseDTO;
    }



    public List<TransactionResponseDTO> historyBuyArtworkById() {
        List<TransactionResponseDTO> listTransactionResponseDTO = new ArrayList<>();
        User user = accountUtils.getCurrentUser();
        Wallet wallet = walletRepository.findWalletByUser_Id(user.getId());
        List<Transaction> transactions = transactionRepository.findTransactionsByFrom_IdOrTo_Id(wallet.getWalletID());
        for (Transaction transaction : transactions) {
            if(transaction.getArtworkID() != null) {
                TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
                transactionResponseDTO.setTransactionID(transaction.getTransactionID());
                transactionResponseDTO.setTransactionType(transaction.getTransactionType());
                transactionResponseDTO.setAmount(transaction.getAmount());
                transactionResponseDTO.setDescription(transaction.getDescription());
                transactionResponseDTO.setTransactionDate(transaction.getTransactionDate());
                transactionResponseDTO.setArtwork(artworkRepository.findById((long) transaction.getArtworkID()));
                transactionResponseDTO.setFrom(transaction.getFrom());
                transactionResponseDTO.setTo(transaction.getTo());
                if (transaction.getFrom() != null) {
                    transactionResponseDTO.setUserFrom(transaction.getFrom().getUser());
                }
                if (transaction.getTo() != null) {
                    transactionResponseDTO.setUserTo(transaction.getTo().getUser());
                }
                listTransactionResponseDTO.add(transactionResponseDTO);
            }
        }
        listTransactionResponseDTO = listTransactionResponseDTO.stream()
                .sorted(Comparator.comparing(TransactionResponseDTO::getTransactionDate).reversed())
                .collect(Collectors.toList());

        return listTransactionResponseDTO;
    }
}

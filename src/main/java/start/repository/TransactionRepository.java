package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import start.entity.Transaction;
import start.enums.StatusEnum;
import start.enums.TransactionEnum;

import java.util.List;
import java.util.UUID;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Transaction findByTransactionID(UUID id);

    @Query("SELECT t FROM Transaction t WHERE t.from.walletID = :walletId OR t.to.walletID = :walletId")
    List<Transaction> findTransactionsByFrom_IdOrTo_Id(int walletId);

    List<Transaction> findTransactionByTransactionType(TransactionEnum status);
}

package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.Wallet;

import java.util.UUID;


@Repository

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findWalletByUser_Id(UUID userId);

}

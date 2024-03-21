package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import start.entity.SystemProfit;

import java.util.List;

@Repository
public interface SystemProfitRepository extends JpaRepository<SystemProfit,Long> {


        @Query("SELECT SUM(sp.balance) FROM SystemProfit sp")
        float getTotalProfit();

        @Query("SELECT SUM(sp.balance) FROM SystemProfit sp WHERE FUNCTION('MONTH', sp.date) = :month " +
                "AND FUNCTION('YEAR', sp.date) = :year")
        float getProfitByMonth(int month, int year);

        @Query("SELECT sp FROM SystemProfit sp WHERE FUNCTION('MONTH', sp.date) = :month " +
                "AND FUNCTION('YEAR', sp.date) = :year")
        List<SystemProfit> getAllHistorySystemProfit(int month, int year);

}

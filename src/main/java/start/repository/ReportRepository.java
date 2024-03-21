package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {


}

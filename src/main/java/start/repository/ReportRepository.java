package start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import start.entity.Report;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

      Report findById(long id);

      List<Report> findAll();

      List<Report> findByFrom_Id(UUID id);

}

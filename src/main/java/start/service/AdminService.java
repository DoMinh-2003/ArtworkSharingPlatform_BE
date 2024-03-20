package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.enums.RoleEnum;
import start.repository.SystemProfitRepository;
import start.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SystemProfitRepository systemProfitRepository;

    public int countUser() {

      int count = userRepository.countByRole(RoleEnum.CREATOR,RoleEnum.AUDIENCE);

      return count;
    }

    public float revenuePortal() {
        float revenuePortal = systemProfitRepository.getTotalProfit();
        return revenuePortal;
    }
    public float getProfitByMonth(int month, int year) {
        float revenuePortal = systemProfitRepository.getProfitByMonth( month, year);
        return revenuePortal;
    }


}

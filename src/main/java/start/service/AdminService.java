package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.response.ProfitResponseDTO;
import start.entity.SystemProfit;
import start.enums.RoleEnum;
import start.repository.SystemProfitRepository;
import start.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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


    public List<ProfitResponseDTO> getProfitByMonth(int year) {
        int i;
        List<ProfitResponseDTO> list =  new ArrayList<>();
        float revenuePortal;
        List<SystemProfit> systemProfits;


        for(i = 1 ; i <= 12 ; i++){
            int month = i;
            try {
                revenuePortal = systemProfitRepository.getProfitByMonth(month, year);
                systemProfits = systemProfitRepository.getAllHistorySystemProfit( month ,year);
            }catch(Exception e){
                revenuePortal = 0;
                systemProfits = new ArrayList<>();
            }
            ProfitResponseDTO responseDTO = new ProfitResponseDTO();
            responseDTO.setMonth(month);
            responseDTO.setRevenuePortal(revenuePortal);
            responseDTO.setSystemProfits(systemProfits);
            list.add(responseDTO);
        }
        return list;
    }


}

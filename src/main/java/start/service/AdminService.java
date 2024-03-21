package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.response.MemberToTalResponseDTO;
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

    public MemberToTalResponseDTO countUser() {
            int creatorCount = userRepository.countByRole(RoleEnum.CREATOR);
            int audienceCount = userRepository.countByRole(RoleEnum.AUDIENCE);
            int modCount = userRepository.countByRole(RoleEnum.MOD);
            int total = creatorCount + audienceCount + modCount;
        MemberToTalResponseDTO memberToTalResponseDTO = new MemberToTalResponseDTO();
        memberToTalResponseDTO.setAudienceQuantity(audienceCount);
        memberToTalResponseDTO.setCreatorQuantity(creatorCount);
        memberToTalResponseDTO.setModQuantity(modCount);
        memberToTalResponseDTO.setTotalMember(total);
        return memberToTalResponseDTO;
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

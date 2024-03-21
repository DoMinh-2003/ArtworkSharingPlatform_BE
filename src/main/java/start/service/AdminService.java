package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.response.MemberToTalResponseDTO;
import start.enums.RoleEnum;
import start.repository.SystemProfitRepository;
import start.repository.UserRepository;

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

    public float revenuePortal() {
        float revenuePortal = systemProfitRepository.getTotalProfit();
        return revenuePortal;
    }
    public float getProfitByMonth(int month, int year) {
        float revenuePortal = systemProfitRepository.getProfitByMonth( month, year);
        return revenuePortal;
    }


}

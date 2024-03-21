package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dto.request.ApproveReportRequestDTO;
import start.dto.request.ReportRequestDTO;
import start.dto.response.ReportResponseDTO;
import start.entity.Artwork;
import start.entity.OrderRequest;
import start.entity.Report;
import start.entity.User;
import start.enums.ReportEnum;
import start.enums.StatusEnum;
import start.repository.ArtworkRepository;
import start.repository.OrderRequestRepository;
import start.repository.ReportRepository;
import start.utils.AccountUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    ArtworkRepository artworkRepository;
    @Autowired
    OrderRequestRepository orderRequestRepository;

    @Autowired
    AccountUtils accountUtils;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    EmailService emailService;

    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);
        return formattedCreateDate;
    }
    public Report reportArtwork(ReportRequestDTO reportRequestDTO) {
        User userFrom = accountUtils.getCurrentUser();
        Artwork artwork = artworkRepository.findById((long)reportRequestDTO.getArtworkID());

        Report report = new Report();
        if(!userFrom.getId().equals(artwork.getUser().getId())){
            report.setTittle(reportRequestDTO.getTitle());
            report.setDiscription(reportRequestDTO.getDescription());
            report.setImage(reportRequestDTO.getEvidenceImage());
            report.setDate(getDate());
            report.setStatusReport(ReportEnum.REPORTARTWORK);
            report.setArtworkID(reportRequestDTO.getArtworkID());
            report.setFrom(userFrom);
            report.setTo(artwork.getUser());
            return reportRepository.save(report);
        }else{
            throw new RuntimeException("You cannot report yourself");
        }

    }

    public List<ReportResponseDTO> adminReports() {
       List<Report> list = reportRepository.findAll();
        List<ReportResponseDTO> reportResponseDTOS = new ArrayList<>();
        for(Report report: list){
            ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
            reportResponseDTO.setId(report.getId());
            reportResponseDTO.setTittle(report.getTittle());
            reportResponseDTO.setDiscription(report.getDiscription());
            reportResponseDTO.setImage(report.getImage());
            reportResponseDTO.setDate(report.getDate());
            reportResponseDTO.setStatusReport(report.getStatusReport());
            OrderRequest orderRequest;
            Artwork artwork;
            if(report.getOrderID() != null){
                orderRequest = orderRequestRepository.findOrderRequestById(report.getOrderID());
                reportResponseDTO.setOrder(orderRequest);
            }

            if(report.getArtworkID() != null){
                artwork = artworkRepository.findById((long)report.getArtworkID());
                reportResponseDTO.setArtwork(artwork);
            }

            reportResponseDTO.setFrom(report.getFrom());
            reportResponseDTO.setTo(report.getTo());
            reportResponseDTOS.add(reportResponseDTO);
        }
        return reportResponseDTOS;
    }

    public List<ReportResponseDTO> reportsById() {
        User user = accountUtils.getCurrentUser();
        List<Report> list = reportRepository.findByFrom_Id(user.getId());
        List<ReportResponseDTO> reportResponseDTOS = new ArrayList<>();
        for(Report report: list){
            ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
            reportResponseDTO.setId(report.getId());
            reportResponseDTO.setTittle(report.getTittle());
            reportResponseDTO.setDiscription(report.getDiscription());
            reportResponseDTO.setImage(report.getImage());
            reportResponseDTO.setDate(report.getDate());
            reportResponseDTO.setStatusReport(report.getStatusReport());
            OrderRequest orderRequest;
            Artwork artwork;
            if(report.getOrderID() != null){
                orderRequest = orderRequestRepository.findOrderRequestById(report.getOrderID());
                reportResponseDTO.setOrder(orderRequest);
            }

            if(report.getArtworkID() != null){
                artwork = artworkRepository.findById((long)report.getArtworkID());
                reportResponseDTO.setArtwork(artwork);
            }
            reportResponseDTO.setFrom(report.getFrom());
            reportResponseDTO.setTo(report.getTo());
            reportResponseDTOS.add(reportResponseDTO);
        }
        return reportResponseDTOS;
    }
    public void threadSendMail(User user,String subject, String description){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                emailService.sendMail(user,subject,description);
            }

        };
        new Thread(r).start();
    }
    public Report responseReport(ApproveReportRequestDTO reportRequestDTO) {
        Report report = reportRepository.findById(reportRequestDTO.getId());
          if(reportRequestDTO.getStatus().trim().toLowerCase().equals("approve")){
              if(report.getArtworkID() != null){
                  Artwork artwork = artworkRepository.findById((long)report.getArtworkID());
                  artwork.setStatus(StatusEnum.REPORTED);
                  artworkRepository.save(artwork);
                  report.setStatusReport(ReportEnum.APPROVE);
                  threadSendMail(report.getTo(),"Report Artwork "+artwork.getTitle(),"Your artwork has been removed for the following reason: " + report.getDiscription());
                  threadSendMail(report.getFrom(),"Approve Report "+report.getTittle(),"Cremo sincerely thanks you for your contribution");
              }else{
                  OrderRequest orderRequest = orderRequestRepository.findOrderRequestById(report.getOrderID());
                  orderRequest.setStatus(StatusEnum.REPORTED);
                  orderRequestRepository.save(orderRequest);
                  report.setStatusReport(ReportEnum.APPROVE);
                  threadSendMail(report.getTo(),"Report OrderRequest " + orderRequest.getTitle(),"Your orderRequest has been removed for the following reason: " + report.getDiscription()+"\n You can reply to this email to explain");
                  threadSendMail(report.getFrom(),"Approve Report " + report.getTittle(),"Cremo sincerely thanks you for your contribution");
              }

          }else{
              report.setStatusReport(ReportEnum.REJECT);
              report.setReasonReject(reportRequestDTO.getReasonReject());
              threadSendMail(report.getFrom(),"Reject Report "+report.getTittle(),"Cremo Reject Because: "+ reportRequestDTO.getReasonReject());
          }
          return reportRepository.save(report);
    }
}

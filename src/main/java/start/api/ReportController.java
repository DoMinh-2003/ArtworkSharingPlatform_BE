package start.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.ApproveReportRequestDTO;
import start.dto.request.ApproveRequestDTO;
import start.dto.request.PostRequestDTO;
import start.dto.request.ReportRequestDTO;
import start.dto.response.ReportResponseDTO;
import start.dto.response.UserResponseDTO;
import start.entity.Artwork;
import start.entity.Report;
import start.service.ReportService;
import start.utils.ResponseHandler;

import java.util.List;

@RestController
@SecurityRequirement(name = "api")
public class ReportController {

    @Autowired
    ReportService reportService;
    @Autowired
    ResponseHandler responseHandler;

    @PostMapping("/reportArtwork")
    public ResponseEntity reportArtwork(@RequestBody ReportRequestDTO reportRequestDTO){
        Report report = reportService.reportArtwork(reportRequestDTO);
        return  responseHandler.response(200, "Report Post Successfully!", report);
    }

    @GetMapping("/adminReports")
    public ResponseEntity adminReports(){
        List<ReportResponseDTO> report = reportService.adminReports();
        return  responseHandler.response(200, "Get All Report Successfully!", report);
    }

    @GetMapping("/userReports")
    public ResponseEntity reportsById(){
        List<ReportResponseDTO> report = reportService.reportsById();
        return  responseHandler.response(200, "Get All Report By Id Successfully!", report);
    }

    @PutMapping("/responseReport")
    public ResponseEntity responseReport(@RequestBody ApproveReportRequestDTO reportRequestDTO){
        Report report = reportService.responseReport(reportRequestDTO);
        return  responseHandler.response(200, "Response Report Successlly!", report);
    }





}

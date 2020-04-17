package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.ReportDTO;
import com.wasteless.sd.Model.ReportType;
import com.wasteless.sd.Service.ReportService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report")
    public ReportDTO getMonthlyReport(@RequestParam("type") String reportType, Principal principal) {
        return reportService.getReport(ReportType.valueOf(reportType.toUpperCase()), principal.getName());
    }
}

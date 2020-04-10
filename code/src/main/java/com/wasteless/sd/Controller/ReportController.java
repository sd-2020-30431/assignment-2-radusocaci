package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.ReportType;
import com.wasteless.sd.Service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report")
    public String getMonthlyReport(@RequestParam("type") String reportType, Principal principal, Model model) {
        model.addAttribute("report", reportService.getReport(
                ReportType.valueOf(reportType.toUpperCase()), principal.getName())
        );

        return "report";
    }
}

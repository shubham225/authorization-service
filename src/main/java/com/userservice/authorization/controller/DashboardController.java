package com.userservice.authorization.controller;

import com.userservice.authorization.common.message.AuthMessage;
import com.userservice.authorization.model.dto.CustomMetricsDTO;
import com.userservice.authorization.model.result.AppResult;
import com.userservice.authorization.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping(
            path = "/metrics",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getMetrics() {
        CustomMetricsDTO metrics = dashboardService.getMetrics();
        return AppResult.success(AuthMessage.SUCCESS_MESSAGE, metrics);
    }
}

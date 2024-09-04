package com.userservice.authorization.service;

import com.userservice.authorization.model.dto.CustomMetricsDTO;
import org.springframework.stereotype.Service;

public interface DashboardService {
    CustomMetricsDTO getMetrics();
}

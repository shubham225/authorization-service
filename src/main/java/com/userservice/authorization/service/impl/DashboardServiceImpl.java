package com.userservice.authorization.service.impl;

import com.userservice.authorization.model.dto.CustomMetricsDTO;
import com.userservice.authorization.service.ClientService;
import com.userservice.authorization.service.DashboardService;
import com.userservice.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final UserService userService;
    private final ClientService clientService;

    @Override
    public CustomMetricsDTO getMetrics() {
        CustomMetricsDTO metrics = new CustomMetricsDTO();
        metrics.setUserCount(userService.getTotalCount());
        metrics.setClientCount(clientService.getTotalCount());
        return metrics;
    }
}

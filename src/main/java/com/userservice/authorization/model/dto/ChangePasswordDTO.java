package com.userservice.authorization.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ChangePasswordDTO {
    private UUID userId;
    private String oldPassword;
    private String newPassword;
}

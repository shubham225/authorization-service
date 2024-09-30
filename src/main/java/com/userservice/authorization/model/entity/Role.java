package com.userservice.authorization.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@JsonSerialize(as = Role.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "`role`")
public class Role extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String role;
    private String description;
}

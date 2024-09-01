package com.userservice.authorization.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "`scope`")
@JsonSerialize(as = Role.class)
public class Scope extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String scope;
    private String description;
}

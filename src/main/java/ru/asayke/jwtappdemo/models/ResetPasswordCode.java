package ru.asayke.jwtappdemo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "reset_codes")
public class ResetPasswordCode extends BaseEntity {
    @NotNull
    @Column(name = "reset_code")
    private Integer resetCode;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private User user;
}
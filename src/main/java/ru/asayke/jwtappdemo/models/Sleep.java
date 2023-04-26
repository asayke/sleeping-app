package ru.asayke.jwtappdemo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sleeps")
public class Sleep extends BaseEntity{
    @Column(name = "noise", nullable = true)
    private int noise;

    @Column(name = "quality", nullable = true)
    private int quality;

    @Column(name = "went_sleep", nullable = true)
    private Date went_sleep;

    @Column(name = "waked_up_at", nullable = true)
    private Date waked_up_at;

    @Column(name = "slept_during", nullable = true)
    private Double slept_during;

    @Column(name = "fell_asleep_during", nullable = true)
    private Integer fell_asleep_during;

    @Column(name = "time_spent_in_bed", nullable = true)
    private Double time_spent_in_bed;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private User user;
}
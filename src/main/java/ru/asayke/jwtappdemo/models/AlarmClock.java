package ru.asayke.jwtappdemo.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "alarm_clocks")
public class AlarmClock extends BaseEntity {
    @Column(name = "remind_to_charge")
    private Boolean remindToCharge;

    @Column(name = "bed_time")
    private Date bedtime;

    @Column(name = "wake_up_time")
    private Date wakeupTime;

    @Column(name = "remind_to_sleep")
    private Boolean remindToSleep;

    @Column(name = "alarm_enabled")
    private Boolean alarmEnabled;

    @Column(name = "vibration_enabled")
    private Boolean vibrationEnabled;

    @Column(name = "alarm_volume")
    private Double alarmVolume;
    @Column(name = "snooze_time")
    private Double snoozeTime;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private User user;
}
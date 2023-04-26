package ru.asayke.jwtappdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlarmClockDTO {
    private Boolean remindToCharge;
    private Date bedtime;
    private Date wakeupTime;
    private Boolean remindToSleep;
    private Boolean alarmEnabled;
    private Boolean vibrationEnabled;
    private Double alarmVolume;
    private Double snoozeTime;
}
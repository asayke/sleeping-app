package ru.asayke.jwtappdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultAlarmClock {
    private Boolean remindToCharge = false;
    private Date bedtime = Date.from(Instant.now());
    private Date wakeupTime = Date.from(Instant.now());
    private Boolean remindToSleep = false;
    private Boolean alarmEnabled = false;
    private Boolean vibrationEnabled = false;
    private Double alarmVolume = 0.0;
    private Double snoozeTime = 0.0;
}
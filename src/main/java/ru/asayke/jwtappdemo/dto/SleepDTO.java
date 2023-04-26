package ru.asayke.jwtappdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SleepDTO {
    private int noise;
    private int quality;
    private Date went_sleep;
    private Date waked_up_at;
    private Double slept_during;
    private Integer fell_asleep_during;
    private Double time_spent_in_bed;
}
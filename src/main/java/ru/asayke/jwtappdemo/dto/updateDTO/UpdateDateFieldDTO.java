package ru.asayke.jwtappdemo.dto.updateDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDateFieldDTO {
    private Date field;
}
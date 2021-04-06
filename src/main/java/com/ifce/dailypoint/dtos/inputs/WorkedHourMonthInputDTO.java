package com.ifce.dailypoint.dtos.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkedHourMonthInputDTO {
    private Integer year;
    private Integer month;
    private String cpf;
    private double normalValue;
    private double holidayValue;
}

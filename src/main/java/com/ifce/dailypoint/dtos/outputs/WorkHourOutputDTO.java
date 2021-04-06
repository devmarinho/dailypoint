package com.ifce.dailypoint.dtos.outputs;

import java.time.LocalDateTime;

import com.ifce.dailypoint.entities.WorkedHour;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkHourOutputDTO {
    
    private LocalDateTime morningHours;
    private LocalDateTime afternoonHours;
    private double dayHours;

    public WorkHourOutputDTO(WorkedHour workedHour){
        setAfternoonHours(workedHour.getWorkedHoursAfternoon());
        setDayHours(workedHour.getWorkedHoursDay());
        setMorningHours(workedHour.getWorkedHoursMorning());
    }
}

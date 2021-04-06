package com.ifce.dailypoint.dtos.outputs;

import java.time.LocalDateTime;

import com.ifce.dailypoint.entities.TimePoint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimePointOutputDTO {
    
    private String tipo;
    private LocalDateTime hour;
    private Integer year;
    private Integer month;
    private Integer day;

    public TimePointOutputDTO(TimePoint time){
        setDay(time.getDay());
        setHour(time.getPointHour());
        setMonth(time.getMonth());
        setYear(time.getYear());
        setTipo(time.getType().getDescricao());
    }
}

package com.ifce.dailypoint.dtos.inputs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TimePointInputDTO {
    
    private LocalDateTime entradaManha;
    private LocalDateTime almoco;
    private LocalDateTime entradaTarde;
    private LocalDateTime saida;
    private String cpf;

    public boolean isInvalidTimePointDate(LocalDateTime hour, LocalDateTime hour2){
        if(hour.equals(hour2))
            return true;
        
        if(hour.getMonthValue() != hour2.getMonthValue())
            return true;
        
        if(hour.getYear() != hour2.getYear())
            return true;
        return hour.getDayOfMonth() != hour2.getDayOfMonth();
    }
}

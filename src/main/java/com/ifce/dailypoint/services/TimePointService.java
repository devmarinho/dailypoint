package com.ifce.dailypoint.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ifce.dailypoint.dtos.outputs.TimePointOutputDTO;
import com.ifce.dailypoint.entities.Employee;
import com.ifce.dailypoint.entities.TimePoint;
import com.ifce.dailypoint.enums.TipoPontoEnum;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.repository.TimePointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimePointService {
    
    @Autowired
    private TimePointRepository pointRepository;

    @Autowired
    private EmployeeService employeeService;

    public TimePoint save(TimePoint timePoint){
        return pointRepository.save(timePoint);
    }

    public TimePoint create(LocalDateTime hour, TipoPontoEnum type, String cpf) throws BusinessErrorException{
        Employee employee = employeeService.findByValidCpf(cpf);
        TimePoint timePoint = findExistPoint(hour.getYear(), hour.getMonthValue(), hour.getDayOfMonth(), employee, type);
        timePoint.setDay(hour.getDayOfMonth());
        timePoint.setMonth(hour.getMonthValue());
        timePoint.setYear(hour.getYear());
        timePoint.setPointHour(hour);
        timePoint.setType(type);
        timePoint.setEmployee(employee);
        return save(timePoint);
    }

    public TimePoint findExistPoint(Integer year, Integer month, Integer day, Employee employee, TipoPontoEnum type){
        TimePoint timePoint = pointRepository.findByYearAndMonthAndDayAndEmployeeAndType(year, month, day, employee, type);
        if(timePoint == null)
            return new TimePoint();
        return timePoint;
    }

    public List<TimePointOutputDTO> findByMonthAndYear(Integer year, Integer month, String cpf) throws BusinessErrorException{
        Employee employee = employeeService.findByValidCpf(cpf);
        return pointRepository.findByYearAndMonthAndEmployee(year, month, employee)
        .stream()
        .map(TimePointOutputDTO::new)
        .collect(Collectors.toList());
    }
}

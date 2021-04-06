package com.ifce.dailypoint.repository;

import java.util.List;

import com.ifce.dailypoint.entities.Employee;
import com.ifce.dailypoint.entities.TimePoint;
import com.ifce.dailypoint.enums.TipoPontoEnum;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimePointRepository extends JpaRepository<TimePoint, Long> {
    
    TimePoint findByYearAndMonthAndDayAndEmployeeAndType(Integer year, Integer month, Integer day, Employee employee, TipoPontoEnum type);

    List<TimePoint> findByYearAndMonthAndEmployee(Integer year, Integer month, Employee employee);
}

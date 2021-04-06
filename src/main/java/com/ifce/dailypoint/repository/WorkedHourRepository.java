package com.ifce.dailypoint.repository;

import java.util.List;

import com.ifce.dailypoint.entities.Employee;
import com.ifce.dailypoint.entities.WorkedHour;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkedHourRepository extends JpaRepository<WorkedHour, Long> {
    
    List<WorkedHour> findByYearAndMonthAndEmployee(Integer year, Integer month, Employee employee );
}

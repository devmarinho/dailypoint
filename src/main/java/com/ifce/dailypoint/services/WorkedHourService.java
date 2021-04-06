package com.ifce.dailypoint.services;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ifce.dailypoint.dtos.inputs.WorkedHourMonthInputDTO;
import com.ifce.dailypoint.dtos.outputs.WorkHourOutputDTO;
import com.ifce.dailypoint.entities.Employee;
import com.ifce.dailypoint.entities.TimePoint;
import com.ifce.dailypoint.entities.WorkedHour;
import com.ifce.dailypoint.exceptions.BusinessErrorEnum;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.repository.WorkedHourRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WorkedHourService {
    
    @Autowired
    private WorkedHourRepository hourRepository;

    @Autowired
    private EmployeeService employeeService;

    public WorkedHour save(WorkedHour hour){
        return hourRepository.save(hour);
    }

    public WorkedHour create(TimePoint entradaManha,TimePoint almoco,TimePoint entradaTarde,TimePoint saida) throws BusinessErrorException{
        Employee employee = entradaManha.getEmployee();
        WorkedHour workedHour = new WorkedHour();
        workedHour.setDay(entradaManha.getDay());
        workedHour.setMonth(entradaManha.getMonth());
        workedHour.setYear(entradaManha.getYear());
        workedHour.setWorkedHoursMorning(minusHours(almoco.getPointHour(), entradaManha.getPointHour()));
        workedHour.setWorkedHoursAfternoon(minusHours(saida.getPointHour(), entradaTarde.getPointHour()));
        LocalDateTime finalHourDay = plusHours(workedHour.getWorkedHoursMorning(),workedHour.getWorkedHoursAfternoon());
        workedHour.setWorkedHoursDay(calculateHours(finalHourDay));
        workedHour.setEmployee(employee);
        return save(workedHour);
    }

    private LocalDateTime plusHours(LocalDateTime hour1,LocalDateTime hour2 ){
        hour1 = hour1.plusSeconds(hour2.getSecond());
        hour1 = hour1.plusMinutes(hour2.getMinute());
        hour1 = hour2.plusHours(hour2.getHour());
        return hour1;
    }

    private LocalDateTime minusHours(LocalDateTime hour1,LocalDateTime hour2 ) throws BusinessErrorException{
        if (hour1.isBefore(hour2)) {
            throw new BusinessErrorException(12, BusinessErrorEnum.INVALID_DATE, "Uma das datas é inválida");

        }
        hour1 = hour1.minusSeconds(hour2.getSecond());
        hour1 = hour1.minusMinutes(hour2.getMinute());
        hour1 = hour1.minusHours(hour2.getHour());
        return hour1; 
    }

    private double calculateHours(LocalDateTime time){
        return time.getHour() + time.getMinute()/60.00 + time.getSecond()/3600.00;
    }

    private List<WorkedHour> findByYearAndMonth(Integer year, Integer month, Employee employee){
        return hourRepository.findByYearAndMonthAndEmployee(year, month, employee);
    }

    public List<WorkHourOutputDTO> findByYearAndMonth(Integer year, Integer month, final String cpf) throws BusinessErrorException{
        Employee employee = employeeService.findByValidCpf(cpf);
        return findByYearAndMonth(year, month, employee)
        .stream()
        .map(WorkHourOutputDTO::new)
        .collect(Collectors.toList());
    }

    public double calculateSalaryByMonthHours(Integer year, Integer month, Employee employee, double holidayValue, double normalValue){
        double finalSalary = 0;
        List<WorkedHour> list = findByYearAndMonth(year, month, employee);
        for (WorkedHour workedHour : list) {
            DayOfWeek dayOfweek = workedHour.getWorkedHoursMorning().getDayOfWeek();
            if(DayOfWeek.SATURDAY.equals(dayOfweek) || DayOfWeek.SUNDAY.equals(dayOfweek))
                finalSalary += workedHour.getWorkedHoursDay() * holidayValue;
            else
                finalSalary += workedHour.getWorkedHoursDay() * normalValue;
        }
        return finalSalary;
    }
    public double calculateSalaryByMonthHours(WorkedHourMonthInputDTO dto) throws BusinessErrorException{
        Employee employee = employeeService.findByValidCpf(dto.getCpf());
        return calculateSalaryByMonthHours(dto.getYear(), dto.getMonth(), employee,
        dto.getHolidayValue(), dto.getNormalValue());
    }
}

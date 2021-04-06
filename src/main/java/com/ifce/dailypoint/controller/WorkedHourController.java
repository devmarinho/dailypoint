package com.ifce.dailypoint.controller;

import java.util.List;

import com.ifce.dailypoint.dtos.inputs.WorkedHourMonthInputDTO;
import com.ifce.dailypoint.dtos.outputs.WorkHourOutputDTO;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.services.TokenService;
import com.ifce.dailypoint.services.WorkedHourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("workedHour")
public class WorkedHourController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private WorkedHourService workedHourService;

    @PostMapping("calculate")
    public ResponseEntity<Double> calculateHoursMonth(@RequestBody WorkedHourMonthInputDTO dto,
    @RequestHeader(name="Authorization") String token) throws BusinessErrorException {
        tokenService.fetchValidTokenByIdToken(token);
        double result = workedHourService.calculateSalaryByMonthHours(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<List<WorkHourOutputDTO>> getByMonthAndYear(@RequestBody WorkedHourMonthInputDTO dto,
    @RequestHeader(name="Authorization") String token) throws BusinessErrorException {
        tokenService.fetchValidTokenByIdToken(token);
        List<WorkHourOutputDTO> result = workedHourService.findByYearAndMonth(dto.getYear(), dto.getMonth(), dto.getCpf());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

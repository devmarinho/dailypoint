package com.ifce.dailypoint.controller;

import java.util.List;

import com.ifce.dailypoint.dtos.inputs.TimeInputByMonthInputDTO;
import com.ifce.dailypoint.dtos.inputs.TimePointInputDTO;
import com.ifce.dailypoint.dtos.outputs.TimePointOutputDTO;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.services.CadastroService;
import com.ifce.dailypoint.services.TimePointService;
import com.ifce.dailypoint.services.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("timepoint")
public class TimePointController {
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private TimePointService timePointService;

    @PostMapping
    public ResponseEntity<Void> createTimePoint(@RequestBody TimePointInputDTO dto,
    @RequestHeader(name="Authorization") String token) throws BusinessErrorException {
        tokenService.fetchValidTokenByIdToken(token);
        cadastroService.createTimePoint(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value="")
    public ResponseEntity<List<TimePointOutputDTO>> getByMonthAndYear(@RequestBody TimeInputByMonthInputDTO dto,
    @RequestHeader(name="Authorization") String token) throws BusinessErrorException {
        tokenService.fetchValidTokenByIdToken(token);
        List<TimePointOutputDTO> result = timePointService.findByMonthAndYear(dto.getYear(), dto.getMonth(), dto.getCpf());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    
}

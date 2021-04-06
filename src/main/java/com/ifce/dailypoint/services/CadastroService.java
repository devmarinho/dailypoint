package com.ifce.dailypoint.services;

import com.ifce.dailypoint.dtos.inputs.EnterpriseInputDTO;
import com.ifce.dailypoint.dtos.inputs.TimePointInputDTO;
import com.ifce.dailypoint.dtos.inputs.EmployeeInputDTO;
import com.ifce.dailypoint.entities.Enterprise;
import com.ifce.dailypoint.entities.TimePoint;
import com.ifce.dailypoint.enums.TipoPontoEnum;
import com.ifce.dailypoint.exceptions.BusinessErrorEnum;
import com.ifce.dailypoint.exceptions.BusinessErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {
    
    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TimePointService pointService;

    @Autowired
    private WorkedHourService workedHourService;

    public void createEmployee(EmployeeInputDTO employeeInputDTO, final String username) throws BusinessErrorException{
        employeeService.create(employeeInputDTO.getNome(), employeeInputDTO.getCpf(), 
        employeeInputDTO.getEndereco(), username);
    }

    public void createEnterprise(EnterpriseInputDTO enterpriseInputDTO) throws BusinessErrorException{
        enterpriseService.create(enterpriseInputDTO.getAddress(),enterpriseInputDTO.getCnpj(),
        enterpriseInputDTO.getName(),enterpriseInputDTO.getEmail(), enterpriseInputDTO.getUsername());
    }

    public void createTimePoint(TimePointInputDTO dto) throws BusinessErrorException{
        if(dto.isInvalidTimePointDate(dto.getEntradaManha(), dto.getAlmoco())
        || dto.isInvalidTimePointDate(dto.getEntradaManha(), dto.getEntradaTarde())
        || dto.isInvalidTimePointDate(dto.getEntradaManha(), dto.getSaida()))
            throw new BusinessErrorException(11, BusinessErrorEnum.INVALID_DATE, "Uma das datas é inválida");
    
        TimePoint entradaManha = pointService.create(dto.getEntradaManha(), TipoPontoEnum.ENTRADA_MANHA, dto.getCpf());
        TimePoint almoco = pointService.create(dto.getAlmoco(), TipoPontoEnum.ALMOCO, dto.getCpf());
        TimePoint entradaTarde = pointService.create(dto.getEntradaTarde(), TipoPontoEnum.ENTRADA_TARDE, dto.getCpf());
        TimePoint saida = pointService.create(dto.getSaida(), TipoPontoEnum.SAIDA, dto.getCpf());

        workedHourService.create(entradaManha, almoco, entradaTarde, saida);
    
    }
}

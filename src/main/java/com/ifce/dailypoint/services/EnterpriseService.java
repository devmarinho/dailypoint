package com.ifce.dailypoint.services;

import com.ifce.dailypoint.dtos.inputs.EnterpriseInputDTO;
import com.ifce.dailypoint.entities.Enterprise;
import com.ifce.dailypoint.exceptions.BusinessErrorEnum;
import com.ifce.dailypoint.exceptions.BusinessErrorException;
import com.ifce.dailypoint.repository.EnterpriseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public Enterprise save(Enterprise enterprise){
        return enterpriseRepository.save(enterprise);
    }

    public Enterprise create(String address, String cnpj, String name, String email, String username) throws BusinessErrorException{
        Enterprise enterprise = findExistingEnterprise(cnpj);
        enterprise.setAddress(address);
        enterprise.setCnpj(cnpj);
        enterprise.setEmail(email);
        enterprise.setName(name);
        enterprise.setUsername(username);

        return save(enterprise);
    }

    public Enterprise findByCnpj(final String cnpj){
        return enterpriseRepository.findByCnpj(cnpj);
    }

    public Enterprise findValidByCnpj(final String cnpj) throws BusinessErrorException{
        Enterprise enterprise = enterpriseRepository.findByCnpj(cnpj);
        if(enterprise == null)
            throw new BusinessErrorException(6, BusinessErrorEnum.ENTERPRISE_NOT_FOUND, "Empresa não encontrada");
    
        return enterprise;
    }
    
    public Enterprise editarEnterprise(EnterpriseInputDTO enterpriseInputDTO, final String cnpj) throws BusinessErrorException{
        Enterprise enterprise = findValidByCnpj(cnpj);
        enterprise.setAddress(enterpriseInputDTO.getAddress());
        enterprise.setCnpj(enterpriseInputDTO.getCnpj());
        enterprise.setEmail(enterpriseInputDTO.getEmail());
        enterprise.setName(enterpriseInputDTO.getName());
        return save(enterprise);
    }

    public Enterprise fetchValidByUsername(final String username) throws BusinessErrorException{
        Enterprise enterprise = enterpriseRepository.findByUsername(username);
        if(enterprise == null)
            throw new BusinessErrorException(6, BusinessErrorEnum.ENTERPRISE_NOT_FOUND, "Empresa não encontrada");
    
        return enterprise;
    }

    public Enterprise findExistingEnterprise(final String cnpj) throws BusinessErrorException{
        Enterprise enterprise = findByCnpj(cnpj);
        if(enterprise == null)
            return new Enterprise();
        throw new BusinessErrorException(13, BusinessErrorEnum.ENTERPRISE_NOT_FOUND, "Empresa já cadastrada");
    }
}

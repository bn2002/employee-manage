package com.bn2002.cukcuk.api.configs;

import com.bn2002.cukcuk.api.dtos.EmployeeDto;
import com.bn2002.cukcuk.api.models.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    public void ModelMapperConfig(){};
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
        modelMapper.createTypeMap(Employee.class, EmployeeDto.class)
                .addMapping(Employee::getEmployeeID, EmployeeDto::setId);
        return modelMapper;
    }
}

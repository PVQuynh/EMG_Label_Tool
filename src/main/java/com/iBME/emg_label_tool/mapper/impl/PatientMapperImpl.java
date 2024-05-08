package com.iBME.emg_label_tool.mapper.impl;

import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.dto.response.PatientRes;
import com.iBME.emg_label_tool.entity.Patient;
import com.iBME.emg_label_tool.mapper.PatientMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapperImpl implements PatientMapper {
    
    @Override
    public Patient toEntity(PatientReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Patient patient = modelMapper.map(dto, Patient.class);
        patient.setId(0);

        return patient;
    }

    @Override
    public List<Patient> toEntityList(List<PatientReq> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PatientRes toDTO(Patient entity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(entity, PatientRes.class);
    }

    @Override
    public List<PatientRes> toDTOList(List<Patient> entityList) {
        return entityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

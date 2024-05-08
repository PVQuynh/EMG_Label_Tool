package com.iBME.emg_label_tool.mapper.impl;

import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.response.DataFileRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.entity.Patient;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.DataFileMapper;
import com.iBME.emg_label_tool.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataFileMapperImpl implements DataFileMapper {
    private final PatientRepository patientRepository;

    @Override
    public DataFile toEntity(DataFileReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        DataFile dataFile = modelMapper.map(dto, DataFile.class);
        dataFile.setId(0);

        // Get patient
        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow(BusinessLogicException::new);
        dataFile.setPatient(patient);

        return dataFile;
    }

    @Override
    public List<DataFile> toEntityList(List<DataFileReq> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DataFileRes toDTO(DataFile entity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(entity, DataFileRes.class);
    }

    @Override
    public List<DataFileRes> toDTOList(List<DataFile> entityList) {
        return entityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

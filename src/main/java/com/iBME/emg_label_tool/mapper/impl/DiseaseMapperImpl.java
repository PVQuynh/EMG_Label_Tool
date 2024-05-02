package com.iBME.emg_label_tool.mapper.impl;

import com.iBME.emg_label_tool.dto.request.DiseaseReq;
import com.iBME.emg_label_tool.dto.response.DiseaseRes;
import com.iBME.emg_label_tool.entity.Disease;
import com.iBME.emg_label_tool.mapper.DiseaseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiseaseMapperImpl implements DiseaseMapper {

    @Override
    public Disease toEntity(DiseaseReq dto) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(dto, Disease.class);
    }

    @Override
    public List<Disease> toEntityList(List<DiseaseReq> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DiseaseRes toDTO(Disease entity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(entity, DiseaseRes.class);
    }

    @Override
    public List<DiseaseRes> toDTOList(List<Disease> entityList) {
        return entityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

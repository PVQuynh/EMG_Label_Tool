package com.iBME.emg_label_tool.mapper.impl;

import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.response.DataFileRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.mapper.DataFileMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataFileMapperImpl implements DataFileMapper {

    @Override
    public DataFile toEntity(DataFileReq dto) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(dto, DataFile.class);
    }

    @Override
    public List<DataFile> toEntityList(List<DataFileReq> dtoList) {
        return dtoList.stream()
                .map(dataFileReq -> toEntity(dataFileReq))
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
                .map(dataFile -> toDTO(dataFile))
                .collect(Collectors.toList());
    }
}

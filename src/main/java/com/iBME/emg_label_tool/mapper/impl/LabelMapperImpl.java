package com.iBME.emg_label_tool.mapper.impl;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.dto.response.LabelRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.entity.Disease;
import com.iBME.emg_label_tool.entity.Label;
import com.iBME.emg_label_tool.mapper.LabelMapper;
import com.iBME.emg_label_tool.repository.DataFileRepository;
import com.iBME.emg_label_tool.repository.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LabelMapperImpl implements LabelMapper {
    private final DiseaseRepository diseaseRepository;
    private final DataFileRepository dataFileRepository;

    @Override
    public Label toEntity(LabelReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Label label = modelMapper.map(dto, Label.class);

        // disease
        Disease disease = diseaseRepository.findById(dto.getDiseaseId()).orElse(null);
        label.setDisease(disease);

        // data file
        DataFile dataFile = dataFileRepository.findById(dto.getDataFileId()).orElse(null);
        label.setDataFile(dataFile);

        return label;
    }

    @Override
    public LabelRes toDTO(Label entity) {
        ModelMapper modelMapper = new ModelMapper();

        LabelRes labelRes = modelMapper.map(entity, LabelRes.class);
        labelRes.setLabelResId(entity.getId());

        return labelRes;
    }

    @Override
    public List<LabelRes> toDTOList(List<Label> entityList) {
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Label> toEntityList(List<LabelReq> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}

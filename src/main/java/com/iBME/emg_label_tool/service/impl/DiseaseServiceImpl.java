package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.DiseaseReq;
import com.iBME.emg_label_tool.dto.response.DiseaseRes;
import com.iBME.emg_label_tool.entity.Disease;
import com.iBME.emg_label_tool.mapper.DiseaseMapper;
import com.iBME.emg_label_tool.repository.DiseaseRepository;
import com.iBME.emg_label_tool.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseServiceImpl implements DiseaseService {
    private final DiseaseRepository diseaseRepository;
    private final DiseaseMapper diseaseMapper;

    @Override
    public void addDisease(DiseaseReq diseaseReq) {
        Disease disease = diseaseMapper.toEntity(diseaseReq);
        diseaseRepository.save(disease);
    }

    @Override
    public List<DiseaseRes> getAllDisease() {
        List<Disease> diseaseList = diseaseRepository.findAll();
        return diseaseMapper.toDTOList(diseaseList);
    }
}

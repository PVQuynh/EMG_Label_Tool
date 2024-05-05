package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.dto.response.LabelRes;
import com.iBME.emg_label_tool.entity.Label;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.LabelMapper;
import com.iBME.emg_label_tool.repository.LabelRepository;
import com.iBME.emg_label_tool.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;

    @Override
    public void addLabel(LabelReq labelReq) {
        if (labelReq.getDataFileId() <= 0 || labelReq.getDiseaseId() <= 0) {
            throw new BusinessLogicException();
        }
        Label label = labelMapper.toEntity(labelReq);
        labelRepository.save(label);
    }

    @Override
    public List<LabelRes> getAllLabelByDataFileId(long dataFileId) {
        List<Label> labelList = labelRepository.findAllByDataFileId(dataFileId);
        return labelMapper.toDTOList(labelList);
    }


}

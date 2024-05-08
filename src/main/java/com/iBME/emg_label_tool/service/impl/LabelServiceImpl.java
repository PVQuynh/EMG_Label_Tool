package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.dto.request.UpdateLabelReq;
import com.iBME.emg_label_tool.dto.response.CoordinatesRes;
import com.iBME.emg_label_tool.dto.response.LabelRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.entity.Disease;
import com.iBME.emg_label_tool.entity.Label;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.LabelMapper;
import com.iBME.emg_label_tool.repository.DataFileRepository;
import com.iBME.emg_label_tool.repository.DiseaseRepository;
import com.iBME.emg_label_tool.repository.LabelRepository;
import com.iBME.emg_label_tool.service.DataFileService;
import com.iBME.emg_label_tool.service.LabelService;
import com.iBME.emg_label_tool.utils.UploadedFileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;
    private final DataFileRepository dataFileRepository;
    private final DiseaseRepository diseaseRepository;

    @Override
    public void addLabel(LabelReq labelReq) {
        if (labelReq.getDataFileId() <= 0 || labelReq.getDiseaseId() <= 0) {
            throw new BusinessLogicException();
        }
        Label label = labelMapper.toEntity(labelReq);
        labelRepository.save(label);
    }

    @Override
    public void updateLabel(UpdateLabelReq updateLabelReq) {
        Label label = labelRepository.findById(updateLabelReq.getLabelId()).orElseThrow(BusinessLogicException::new);
        Disease disease = diseaseRepository.findById(updateLabelReq.getDiseaseId()).orElseThrow(BusinessLogicException::new);

        label.setStart(updateLabelReq.getStart());
        label.setStop(updateLabelReq.getStop());
        label.setNote(updateLabelReq.getNote());
        label.setDisease(disease);

        labelRepository.save(label);
    }

    @Override
    public List<LabelRes> getAllLabelByDataFileId(long dataFileId) {
        List<Label> labelList = labelRepository.findAllByDataFileId(dataFileId);
        return labelMapper.toDTOList(labelList);
    }

    @Override
    public List<CoordinatesRes> getGraphSegmentOfLabel(long dataFileId, float start, float stop) {
        DataFile dataFile = dataFileRepository.findById(dataFileId).orElseThrow(BusinessLogicException::new);
        return UploadedFileUtils.coordinatesListV2(dataFile.getDataFileLocation(), (int) (start * 10000), (int) (stop * 10000));
    }


}

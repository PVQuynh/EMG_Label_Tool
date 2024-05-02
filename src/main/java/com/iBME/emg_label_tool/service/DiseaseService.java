package com.iBME.emg_label_tool.service;

import com.iBME.emg_label_tool.dto.request.DiseaseReq;
import com.iBME.emg_label_tool.dto.response.DiseaseRes;

import java.util.List;

public interface DiseaseService {
    void addDisease(DiseaseReq diseaseReq);

    List<DiseaseRes> getAllDisease();
}

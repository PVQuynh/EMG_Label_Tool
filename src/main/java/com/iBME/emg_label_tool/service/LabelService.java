package com.iBME.emg_label_tool.service;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.dto.response.LabelRes;

import java.util.List;

public interface LabelService {
    void addLabel(LabelReq labelReq);

    List<LabelRes> getAllLabelByDataFileId(long dataFileId);
}

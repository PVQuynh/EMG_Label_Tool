package com.iBME.emg_label_tool.service;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.dto.request.UpdateLabelReq;
import com.iBME.emg_label_tool.dto.response.CoordinatesRes;
import com.iBME.emg_label_tool.dto.response.LabelRes;

import java.util.List;

public interface LabelService {

    void addLabel(LabelReq labelReq);

    void updateLabel(UpdateLabelReq updateLabelReq);

    List<LabelRes> getAllLabelByDataFileId(long dataFileId);

    List<CoordinatesRes> getGraphSegmentOfLabel(long dataFileId, float start, float stop);
}

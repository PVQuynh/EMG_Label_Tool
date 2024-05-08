package com.iBME.emg_label_tool.mapper;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.dto.request.UpdateLabelReq;
import com.iBME.emg_label_tool.dto.response.LabelRes;
import com.iBME.emg_label_tool.entity.Label;

import java.util.List;

public interface LabelMapper {
    Label toEntity(LabelReq dto);

    List<Label> toEntityList(List<LabelReq> dtoList);
    
    LabelRes toDTO(Label entity);

    List<LabelRes> toDTOList(List<Label> entityList);


}

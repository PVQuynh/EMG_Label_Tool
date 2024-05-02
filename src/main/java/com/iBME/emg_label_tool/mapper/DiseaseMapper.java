package com.iBME.emg_label_tool.mapper;

import com.iBME.emg_label_tool.dto.request.DiseaseReq;
import com.iBME.emg_label_tool.dto.response.DiseaseRes;
import com.iBME.emg_label_tool.entity.Disease;

import java.util.List;

public interface DiseaseMapper {
    Disease toEntity(DiseaseReq dto);
    List<Disease> toEntityList(List<DiseaseReq> dtoList);

    DiseaseRes toDTO (Disease entity);
    List<DiseaseRes> toDTOList(List<Disease> entityList);

}

package com.iBME.emg_label_tool.mapper;

import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.response.DataFileRes;
import com.iBME.emg_label_tool.entity.DataFile;

import java.util.List;

public interface DataFileMapper {
    DataFile toEntity(DataFileReq dto);
    List<DataFile> toEntityList(List<DataFileReq> dtoList);

    DataFileRes toDTO (DataFile entity);
    List<DataFileRes> toDTOList(List<DataFile> entityList);

}

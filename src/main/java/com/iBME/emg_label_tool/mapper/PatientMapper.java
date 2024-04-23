package com.iBME.emg_label_tool.mapper;

import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.dto.response.PatientRes;
import com.iBME.emg_label_tool.entity.Patient;

import java.util.List;

public interface PatientMapper{
    Patient toEntity(PatientReq dto);
    List<Patient> toEntityList(List<PatientReq> dtoList);

    PatientRes toDTO(Patient entity);
    List<PatientRes> toDTOList(List<Patient> entityList);



}

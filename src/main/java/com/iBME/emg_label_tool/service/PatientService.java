package com.iBME.emg_label_tool.service;


import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.dto.response.PatientRes;

import java.util.List;

public interface PatientService {

    void save(PatientReq patientReq);

    PatientRes getPatient(long id);

    List<PatientRes> getAllPatient();

}
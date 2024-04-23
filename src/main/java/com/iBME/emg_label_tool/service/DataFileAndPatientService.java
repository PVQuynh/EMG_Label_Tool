package com.iBME.emg_label_tool.service;


import com.iBME.emg_label_tool.dto.request.DataFileAndPatientReq;
import org.springframework.web.multipart.MultipartFile;

public interface DataFileAndPatientService {

    void save(MultipartFile file);


}
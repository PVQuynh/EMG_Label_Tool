package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.InforFile;
import com.iBME.emg_label_tool.dto.request.DataFileAndPatientReq;
import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.service.DataFileAndPatientService;
import com.iBME.emg_label_tool.service.DataFileService;
import com.iBME.emg_label_tool.service.PatientService;
import com.iBME.emg_label_tool.service.UploadDataFIleService;
import com.iBME.emg_label_tool.utils.GetDataFIleAndPatient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DataFileAndPatientServiceImpl implements DataFileAndPatientService {
    private final DataFileService dataFileService;
    private final PatientService patientService;
    private final UploadDataFIleService uploadDataFIleService;

    @Override
    public void save(MultipartFile file) {

        // luu file len minio => tra ve file location
        InforFile inforFile = uploadDataFIleService.saveAndGetFileLocation(file);

        // file location => tra ve data file, patent
//        DataFileAndPatientReq dataFileAndPatientReq = GetDataFIleAndPatient.GetDataFileAndPatientReqFromFile(inforFile.getFilePath());
        DataFileAndPatientReq dataFileAndPatientReq = GetDataFIleAndPatient.GetDataFileAndPatientReqFromFile("https://wetalk.ibme.edu.vn/upload/hust-app//Nguyen%20Thi%20HuongBs%20Giang%23001.txt");

        //
        // luu vao db
        //
        DataFileReq dataFileReq = dataFileAndPatientReq.getDataFileReq();
        dataFileReq.setFileName(inforFile.getFileName());
        DataFile dataFile = dataFileService.save(dataFileReq);

        // tu file luu thong tin benh nhan
        PatientReq patientReq = dataFileAndPatientReq.getPatientReq();
        patientReq.setDataFileId(dataFile.getId());
        patientService.save(patientReq);
    }
}

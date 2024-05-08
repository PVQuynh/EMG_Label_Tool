package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.InforFile;
import com.iBME.emg_label_tool.dto.request.DataFileAndPatientReq;
import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.dto.response.UploadedFileRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.entity.Patient;
import com.iBME.emg_label_tool.service.UploadedFileService;
import com.iBME.emg_label_tool.service.DataFileService;
import com.iBME.emg_label_tool.service.PatientService;
import com.iBME.emg_label_tool.service.UploadFIleService;
import com.iBME.emg_label_tool.utils.UploadedFileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class UploadedFileServiceImpl implements UploadedFileService {
    private final DataFileService dataFileService;
    private final PatientService patientService;
    private final UploadFIleService uploadFIleService;

    @Override
    public UploadedFileRes save(MultipartFile file) {

        // luu file len minio => tra ve file location
        InforFile inforFile = uploadFIleService.saveAndGetFileLocation(file);

        // file location => tra ve data file req, patient req
        DataFileAndPatientReq dataFileAndPatientReq = UploadedFileUtils.GetDataFileAndPatientReqFromFile(inforFile.getFilePath());
//        DataFileAndPatientReq dataFileAndPatientReq = UploadedFileUtils.GetDataFileAndPatientReqFromFile("https://wetalk.ibme.edu.vn/upload/hust-app//Nguyen%20Thi%20HuongBs%20Giang%23001.txt");

        //
        // luu vao db
        //
        // tu file luu thong tin benh nhan
        PatientReq patientReq = dataFileAndPatientReq.getPatientReq();
        Patient patient = patientService.save(patientReq);

        DataFileReq dataFileReq = dataFileAndPatientReq.getDataFileReq();
        dataFileReq.setFileName(inforFile.getFileName());
        dataFileReq.setPatientId(patient.getId());
        DataFile dataFile = dataFileService.save(dataFileReq);

        return UploadedFileRes
                .builder()
                .dataFileId(dataFile.getId())
                .build();
    }

}

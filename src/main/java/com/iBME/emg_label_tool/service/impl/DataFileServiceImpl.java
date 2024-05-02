package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.response.DataFileRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.DataFileMapper;
import com.iBME.emg_label_tool.repository.DataFileRepository;
import com.iBME.emg_label_tool.service.DataFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataFileServiceImpl implements DataFileService {
    private final DataFileRepository dataFileRepository;
    private final DataFileMapper dataFileMapper;


    @Override
    public DataFile save(DataFileReq dataFileReq) {
        DataFile dataFile = dataFileMapper.toEntity(dataFileReq);
        return dataFileRepository.save(dataFile);
    }

    @Override
    public List<DataFileRes> getAllDataFile() {
        List<DataFile> dataFileList = dataFileRepository.findAll();
        return dataFileMapper.toDTOList(dataFileList);
    }

    @Override
    public DataFileRes getDataFileByPatientId(long patientId) {
        DataFile dataFile = dataFileRepository.findByPatientId(patientId).orElseThrow(BusinessLogicException::new);
        return dataFileMapper.toDTO(dataFile);
    }
}

package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.mapper.DataFileMapper;
import com.iBME.emg_label_tool.repository.DataFileRepository;
import com.iBME.emg_label_tool.service.DataFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

package com.iBME.emg_label_tool.service;

import com.iBME.emg_label_tool.dto.response.DownLoadFileRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.repository.DataFileRepository;
import com.iBME.emg_label_tool.repository.LabelRepository;
import com.iBME.emg_label_tool.utils.CSVUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class CSVService {
    private final LabelRepository labelRepository;
    private final DataFileRepository dataFileRepository;

    public ByteArrayInputStream load(long dataFileId) {
        return CSVUtils.labelsToCSV(labelRepository.findAllICSVRes(dataFileId));
    }

    public DownLoadFileRes downLoadFileRes(long dataFileId) {
        DataFile dataFile = dataFileRepository.findById(dataFileId).orElseThrow(BusinessLogicException::new);

        return DownLoadFileRes.builder()
                .fileName(dataFile.getFileName())
                .file(new InputStreamResource(load(dataFileId)))
                .build();
    }
}

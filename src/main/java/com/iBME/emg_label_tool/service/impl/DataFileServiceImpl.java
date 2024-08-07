package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.response.CoordinatesRes;
import com.iBME.emg_label_tool.dto.response.DataFileRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.DataFileMapper;
import com.iBME.emg_label_tool.repository.DataFileRepository;
import com.iBME.emg_label_tool.service.DataFileService;
import com.iBME.emg_label_tool.utils.UploadedFileUtils;
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
    public List<DataFileRes> getDataFileByPatientId(long patientId) {
        List<DataFile> dataFileList = dataFileRepository.findAllByPatientId(patientId);
        if (dataFileList.isEmpty()) {
            throw new BusinessLogicException();
        }
        return dataFileMapper.toDTOList(dataFileList);
    }

    @Override
    public List<CoordinatesRes> getXYCoordinates(long id) {
        DataFile dataFile = dataFileRepository.findById(id).orElseThrow(BusinessLogicException::new);

        return UploadedFileUtils.coordinatesList(dataFile.getDataFileLocation());
    }

    @Override
    public List<CoordinatesRes> getXYCoordinatesV2(long id, int page, int size) {
        DataFile dataFile = dataFileRepository.findById(id).orElseThrow(BusinessLogicException::new);

        int start = (page - 1) * size + 1;
        int end = start + size - 1;
        return UploadedFileUtils.coordinatesListV2(dataFile.getDataFileLocation(), start, end);
//        return new PageImpl<>(coordinatesList.subList(start, end), pageable, coordinatesList.size());

    }

}

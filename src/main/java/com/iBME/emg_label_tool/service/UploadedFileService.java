package com.iBME.emg_label_tool.service;

import com.iBME.emg_label_tool.dto.response.CoordinatesRes;
import com.iBME.emg_label_tool.dto.response.UploadedFileRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadedFileService {

    UploadedFileRes save(MultipartFile file);


}
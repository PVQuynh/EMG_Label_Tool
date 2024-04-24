package com.iBME.emg_label_tool.service;

import com.iBME.emg_label_tool.dto.InforFile;
import org.springframework.web.multipart.MultipartFile;

public interface UploadDataFIleService {

    InforFile saveAndGetFileLocation(MultipartFile file);

}

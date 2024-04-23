package com.iBME.emg_label_tool.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadDataFIleService {
    String saveAndGetFileLocation(MultipartFile file);
}

package com.iBME.emg_label_tool.service;

import java.util.List;

public interface UploadService {

    String uploadFile(String name, byte[] content);

    List<String> getAllBucket();
}

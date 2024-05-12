package com.iBME.emg_label_tool.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Builder
@Getter
@Setter
public class DownLoadFileRes {
    String fileName;

    InputStreamResource file;

    public String fileWithoutExtension() {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            fileName = fileName.substring(0, dotIndex);
        }
        return fileName;
    }
}

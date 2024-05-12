package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.response.DownLoadFileRes;
import com.iBME.emg_label_tool.service.CSVService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/csv-controller")
public class CSVController {
    private final CSVService csvService;

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile(
            @RequestParam(required = true, defaultValue = "1") long dataFileId
    ) {
        DownLoadFileRes downLoadFileRes = csvService.downLoadFileRes(dataFileId);
        String filename = downLoadFileRes.fileWithoutExtension()+".csv";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(downLoadFileRes.getFile());
    }

}

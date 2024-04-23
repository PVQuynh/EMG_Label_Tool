package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.DataFileAndPatientReq;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.service.DataFileAndPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/data-files-and-patients")
@RequiredArgsConstructor
public class DataFileAndPatientController {

    private final DataFileAndPatientService dataFileAndPatientService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public MessageResponse save(@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        MessageResponse ms = new MessageResponse();
        try {
           dataFileAndPatientService.save(file);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }
}

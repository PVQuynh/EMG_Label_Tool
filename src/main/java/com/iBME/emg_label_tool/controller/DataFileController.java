package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.service.DataFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data-files")
@RequiredArgsConstructor
public class DataFileController {
    private final DataFileService dataFileService;

    @PostMapping
    public MessageResponse save(@RequestBody DataFileReq dataFileReq) {
        MessageResponse ms = new MessageResponse();

        try {
            dataFileService.save(dataFileReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/all")
    public MessageResponse getAllDataFile() {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = dataFileService.getAllDataFile();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/{patientId}")
    public MessageResponse getByPatientId(@PathVariable("patientId") long patientId) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = dataFileService.getDataFileByPatientId(patientId);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }
}

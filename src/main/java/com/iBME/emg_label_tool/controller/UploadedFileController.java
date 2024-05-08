package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.exception.AlreadyExistsException;
import com.iBME.emg_label_tool.service.UploadedFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/uploaded-files")
@RequiredArgsConstructor
public class UploadedFileController {

    private final UploadedFileService uploadedFileService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> save(@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        ResponseEntity<?> re = ResponseEntity.ok(null);
        try {
            re = ResponseEntity.ok(uploadedFileService.save(file));
        } catch (AlreadyExistsException ex) {
            re = ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return re;
    }


}

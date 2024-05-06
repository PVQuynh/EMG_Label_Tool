package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.service.DataFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data-files")
@RequiredArgsConstructor
public class DataFileController {
    private final DataFileService dataFileService;

//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody DataFileReq dataFileReq) {
//        ResponseEntity<?> re = ResponseEntity.ok("Add Data File Successfully!");
//
//        try {
//            dataFileService.save(dataFileReq);
//        } catch (Exception ex) {
//            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//
//        }
//
//        return re;
//    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDataFile() {
        ResponseEntity<?> re = ResponseEntity.ok(null);

        try {
            re = ResponseEntity.ok(dataFileService.getAllDataFile());
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return re;
    }

    @GetMapping("/xy-coordinates/{id}")
    public ResponseEntity<?> xyCoordinates(@PathVariable("id") long id) {
        ResponseEntity<?> re = ResponseEntity.ok(null);
        try {
            re = ResponseEntity.ok(dataFileService.getXYCoordinates(id));
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        }
        return re;
    }

    @GetMapping("/xy-coordinates/v2")
    public ResponseEntity<?> xyCoordinatesV2(
            @RequestParam(required = true) long id,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "2000") int size
    ) {
        ResponseEntity<?> re;
        try {
            re = ResponseEntity.ok(dataFileService.getXYCoordinatesV2(id, page, size));
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        }
        return re;
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getByPatientId(@PathVariable("patientId") long patientId) {
        ResponseEntity<?> re = ResponseEntity.ok(null);

        try {
            re = ResponseEntity.ok(dataFileService.getDataFileByPatientId(patientId));
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return re;
    }
}

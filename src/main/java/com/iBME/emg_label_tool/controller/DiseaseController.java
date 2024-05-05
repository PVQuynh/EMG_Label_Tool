package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.DiseaseReq;
import com.iBME.emg_label_tool.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diseases")
public class DiseaseController {
    private final DiseaseService diseaseService;

    @PostMapping
    public ResponseEntity<?> addDisease(@RequestBody DiseaseReq diseaseReq) {
        ResponseEntity<?> re = ResponseEntity.ok("Add Disease Successfully!");
        try {
            diseaseService.addDisease(diseaseReq);
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

        return re;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDisease() {
        ResponseEntity<?> re = ResponseEntity.ok(null);
        try {
            re =ResponseEntity.ok(diseaseService.getAllDisease());
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return re;
    }


}

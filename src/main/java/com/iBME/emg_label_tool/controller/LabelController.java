package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/labels")
@RestController
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @PostMapping
    public ResponseEntity<?> addLabel(@RequestBody LabelReq labelReq) {
        ResponseEntity<?> re = ResponseEntity.ok("Add Label Successfully!");

        try {
            labelService.addLabel(labelReq);
        } catch (Exception e) {
            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return re;
    }

    @GetMapping("/{dataFileId}")
    public ResponseEntity<?> getAllLabel(@PathVariable("dataFileId") long dataFileId) {
        ResponseEntity<?> re;
        
        try {
            re = ResponseEntity.ok(labelService.getAllLabelByDataFileId(dataFileId));
        } catch (Exception e) {
           re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return re;
    }
}

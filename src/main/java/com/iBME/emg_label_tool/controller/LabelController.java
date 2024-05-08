package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.dto.request.UpdateLabelReq;
import com.iBME.emg_label_tool.repository.LabelRepository;
import com.iBME.emg_label_tool.service.LabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(description = "Lấy thông tin các tất cả label thuộc về dataFileId")
    public ResponseEntity<?> getAllLabel(
            @Parameter(description = "Truyền vào id của data file", example = "1")
            @PathVariable("dataFileId") long dataFileId) {
        ResponseEntity<?> re;

        try {
            re = ResponseEntity.ok(labelService.getAllLabelByDataFileId(dataFileId));
        } catch (Exception e) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return re;
    }

    @GetMapping("/graph-segment-of-label")
    @Operation(description = "Lấy ra đoạn đồ thị đã gán nhãn")
    public ResponseEntity<?> getGraphSegment(
            @RequestParam(required = true) long dataFileId,
            @RequestParam(required = true) float start,
            @RequestParam(required = true) float stop) {
        ResponseEntity<?> re;

        try {
            re = ResponseEntity.ok(labelService.getGraphSegmentOfLabel(dataFileId, start, stop));
        } catch (Exception e) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return re;
    }

    @PutMapping
    public ResponseEntity<?> updateLabel(@RequestBody UpdateLabelReq updateLabelReq) {
        ResponseEntity<?> re = ResponseEntity.ok("Update Label Successfully!");

        try {
            labelService.updateLabel(updateLabelReq);
        } catch (Exception e) {
            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return re;
    }

}

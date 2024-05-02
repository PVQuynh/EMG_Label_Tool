package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.DiseaseReq;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.service.DiseaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diseases")
public class DiseaseController {
    private final DiseaseService diseaseService;

    @PostMapping
    public MessageResponse addDisease(@RequestBody DiseaseReq diseaseReq) {
        MessageResponse ms = new MessageResponse();
        try {
            diseaseService.addDisease(diseaseReq);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/all")
    public MessageResponse getAllDisease() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = diseaseService.getAllDisease();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }


}

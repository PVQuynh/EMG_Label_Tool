package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.LabelReq;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/labels")
@RestController
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @PostMapping
    public MessageResponse addLabel(@RequestBody LabelReq labelReq) {
        MessageResponse ms = new MessageResponse();

        try {
            labelService.addLabel(labelReq);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/all")
    public MessageResponse getAllLabel() {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = labelService.getAllLabel();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }
}

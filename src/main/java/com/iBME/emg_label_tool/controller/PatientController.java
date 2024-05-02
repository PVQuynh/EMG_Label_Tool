package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    
    @PostMapping
    public MessageResponse save(@RequestBody PatientReq patientReq) {
        MessageResponse ms = new MessageResponse();

        try {
            patientService.save(patientReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/{id}")
    public MessageResponse getPatient(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = patientService.getPatient(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/all")
    public MessageResponse getAllPatient() {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = patientService.getAllPatient();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }
}

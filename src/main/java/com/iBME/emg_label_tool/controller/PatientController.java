package com.iBME.emg_label_tool.controller;

import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    
//    @PostMapping
//    public ResponseEntity<?>  save(@RequestBody PatientReq patientReq) {
//        ResponseEntity<?> re = ResponseEntity.ok("Save patient successfully!");;
//        try {
//            patientService.save(patientReq);
//        } catch (Exception ex) {
//            re = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//        }
//
//        return re;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getPatient(@PathVariable("id") long id) {
        ResponseEntity<?> re = ResponseEntity.ok(null);

        try {
            re = ResponseEntity.ok(patientService.getPatient(id));
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return re;
    }

    @GetMapping("/all")
    public ResponseEntity<?>  getAllPatient() {
        ResponseEntity<?> re = ResponseEntity.ok(null);

        try {
            re = ResponseEntity.ok(patientService.getAllPatient());
        } catch (Exception ex) {
            re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return re;
    }
}

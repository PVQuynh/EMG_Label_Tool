package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.entity.Patient;
import com.iBME.emg_label_tool.mapper.PatientMapper;
import com.iBME.emg_label_tool.repository.PatientRepository;
import com.iBME.emg_label_tool.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public void save(PatientReq patientReq) {
        Patient patient = patientMapper.toEntity(patientReq);
        patientRepository.save(patient);
    }
}

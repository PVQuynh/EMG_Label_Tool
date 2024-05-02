package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.dto.response.PatientRes;
import com.iBME.emg_label_tool.entity.Patient;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.PatientMapper;
import com.iBME.emg_label_tool.repository.PatientRepository;
import com.iBME.emg_label_tool.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PatientRes getPatient(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(BusinessLogicException::new);
        return patientMapper.toDTO(patient);
    }

    @Override
    public List<PatientRes> getAllPatient() {
        List<Patient> patientList = patientRepository.findAll();

        return patientMapper.toDTOList(patientList);
    }
}

package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.dto.response.PatientRes;
import com.iBME.emg_label_tool.entity.Patient;
import com.iBME.emg_label_tool.exception.AlreadyExistsException;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.PatientMapper;
import com.iBME.emg_label_tool.repository.PatientRepository;
import com.iBME.emg_label_tool.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public Patient save(PatientReq patientReq) {
        Optional<Patient> patientOptional = patientRepository.findByName(patientReq.getName());
        if (patientOptional.isEmpty()) {
            Patient patient = patientMapper.toEntity(patientReq);
            return patientRepository.save(patient);
        }

        return patientOptional.get();
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

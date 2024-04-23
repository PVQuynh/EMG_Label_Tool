package com.iBME.emg_label_tool.repository;


import com.iBME.emg_label_tool.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {


}

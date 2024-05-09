package com.iBME.emg_label_tool.repository;


import com.iBME.emg_label_tool.entity.DataFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DataFileRepository extends JpaRepository<DataFile,Long> {

    List<DataFile> findAllByPatientId(long patientId);
}

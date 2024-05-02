package com.iBME.emg_label_tool.repository;


import com.iBME.emg_label_tool.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiseaseRepository extends JpaRepository<Disease,Long> {

}

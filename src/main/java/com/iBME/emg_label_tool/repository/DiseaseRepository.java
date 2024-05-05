package com.iBME.emg_label_tool.repository;


import com.iBME.emg_label_tool.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

}

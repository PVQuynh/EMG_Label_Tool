package com.iBME.emg_label_tool.repository;

import com.iBME.emg_label_tool.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Integer> {

}

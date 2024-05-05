package com.iBME.emg_label_tool.repository;

import com.iBME.emg_label_tool.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Integer> {

    List<Label> findAllByDataFileId(long dataFile_id);
}

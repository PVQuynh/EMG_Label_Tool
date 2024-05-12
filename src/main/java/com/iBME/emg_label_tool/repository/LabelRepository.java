package com.iBME.emg_label_tool.repository;

import com.iBME.emg_label_tool.constant.sql.LabelSQL;
import com.iBME.emg_label_tool.dto.response.CSVRes;
import com.iBME.emg_label_tool.dto.response.ICSVRes;
import com.iBME.emg_label_tool.dto.response.LabelID;
import com.iBME.emg_label_tool.entity.Label;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findAllByDataFileId(long dataFile_id);

    @Query(nativeQuery = true, value = "SELECT label_id as id, start FROM emg_label_tool.label WHERE label_id = :id")
    LabelID findLabelIdAndStart(@Param("id") long id);

    @Query(nativeQuery = true, value = LabelSQL.GET_LABEL_INFORMATION_CSV)
    List<CSVRes> findAllCSVRes(@Param("dataFileId") long dataFileId);

    @Query(nativeQuery = true, value = LabelSQL.GET_LABEL_INFORMATION_CSV)
    List<ICSVRes> findAllICSVRes(@Param("dataFileId") long dataFileId);

}

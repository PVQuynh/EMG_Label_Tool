package com.iBME.emg_label_tool.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataFileRes {

    private long dataFileId;

    private String fileName;

    private String muscle;

    private String side;

    private Date doe; // Date of Examination

    @Column(nullable = false)
    private String dataFileLocation;

    private boolean isLabeled;

    private long patientId;

    private String patientName;

}

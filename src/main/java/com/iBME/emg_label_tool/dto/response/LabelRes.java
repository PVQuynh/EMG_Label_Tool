package com.iBME.emg_label_tool.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelRes {

    private long labelResId;

    private float start;

    private float stop;

    private String note;

    private boolean doubleCheck;

    @Column(nullable = false)
    private String patientDataLocation;

    private long dataFileId;

    private String dataFileName;

    private long diseaseId;

    private String diseaseName;
}
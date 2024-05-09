package com.iBME.emg_label_tool.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelRes {

    private long labelId;

    private float start;

    private float stop;

    private String note;

    private boolean doubleCheck;

    private long dataFileId;

    private String dataFileName;

    private long diseaseId;

    private String diseaseName;
}

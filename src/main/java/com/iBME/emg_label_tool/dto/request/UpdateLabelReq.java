package com.iBME.emg_label_tool.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLabelReq {

    private long labelId;

    private float start;

    private float stop;

    private String note;

    private long diseaseId;

}

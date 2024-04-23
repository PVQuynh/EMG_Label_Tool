package com.iBME.emg_label_tool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseRes {

    private long diseaseId;

    private  String name;
}

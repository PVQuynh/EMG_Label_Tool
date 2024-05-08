package com.iBME.emg_label_tool.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public interface LabelID {

    @Schema(description = "labelId", defaultValue = "labelId")
    long getId();

    @Schema(description = "start", defaultValue = "start")
    long getStart();
}

package com.iBME.emg_label_tool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyRes {

    private String content;

    private String imageLocation;

    private String videoLocation;
}

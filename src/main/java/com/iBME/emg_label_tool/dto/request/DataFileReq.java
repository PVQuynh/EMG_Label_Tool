package com.iBME.emg_label_tool.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataFileReq {

    private String fileName;

    private String muscle;

    private String side;

    private Date doe; // Date of Examination

    @Column(nullable = false)
    private String dataFileLocation;

}

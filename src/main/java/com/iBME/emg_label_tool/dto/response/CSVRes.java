package com.iBME.emg_label_tool.dto.response;

import com.iBME.emg_label_tool.enum_constant.Sex;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CSVRes {
    // thông tin gán nhãn
    private Long labelId;

    private Float start;

    private Float stop;

    // thông tin bệnh
    private String diseaseName;

    private String note;

    private String nullString = "";

    // thông tin bệnh nhân
    private String patientName;

    private Long age;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    // thông tin khám bệnh
    private Date doe; // Date of Examination

    private Time hoe;

    private String muscle;

    private String side;

    private Long amplitude;

    private Long samplingFrequency;

    private Long dataFileId;

    public CSVRes(ICSVRes icsvRes) {
        BeanUtils.copyProperties(icsvRes, this);
    }
}

package com.iBME.emg_label_tool.dto.response;

import com.iBME.emg_label_tool.enum_constant.Sex;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.sql.Date;
import java.sql.Time;

public interface ICSVRes {
    // thông tin gán nhãn
    Long getLabelId();

    Float getStart();

    Float getStop();

    // thông tin bệnh
    String getDiseaseName();

    String getNote();

    String getNullString();

    // thông tin bệnh nhân
    String getPatientName();

    Long getAge();

    Sex getSex();

    // thông tin khám bệnh
    Date getDoe(); // Date of Examination

    Time getHoe();

    String getMuscle();

    String getSide();

    Long getAmplitude();

    Long getSamplingFrequency();

    Long getDataFileId();
}

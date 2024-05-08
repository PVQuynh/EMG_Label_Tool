package com.iBME.emg_label_tool.dto.request;

import com.iBME.emg_label_tool.enum_constant.Sex;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientReq {

    private  String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Date dob;

    private int age;

    private float height;

    private float weight;
}

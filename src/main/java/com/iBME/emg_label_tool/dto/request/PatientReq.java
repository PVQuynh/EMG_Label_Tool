package com.iBME.emg_label_tool.dto.request;

import com.iBME.emg_label_tool.enum_constant.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
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

    private float height;

    private float weight;

    private String phoneNumber;

    private String address;
}

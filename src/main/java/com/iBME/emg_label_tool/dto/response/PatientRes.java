package com.iBME.emg_label_tool.dto.response;

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
public class PatientRes {

    private long patientId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Date dob;

    private float height;

    private float weight;

    private long dataFileId;

}

package com.iBME.emg_label_tool.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserReq {

    private String name;

    private String phoneNumber;

    private String address;

    private String birthDay;

    private String gender;
}

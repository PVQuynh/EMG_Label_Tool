package com.iBME.emg_label_tool.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordReq {

    private String oldPassword;

    private  String newPassword;
}

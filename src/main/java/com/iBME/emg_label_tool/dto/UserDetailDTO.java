package com.iBME.emg_label_tool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDTO {

    private  long userId;

    @NotBlank
    private String name;

    @Email
    private String email;

    private String phoneNumber;

    private String address;

    private String role;

    private Date dob;

    private String sex;

    private  String avatarLocation;

    public UserDetailDTO(UserDTO userDTO) {
        this.userId = userDTO.getUserId();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.address = userDTO.getAddress();
        this.role = userDTO.getRole();
        this.dob = userDTO.getDob();
        this.sex = userDTO.getSex();
        this.avatarLocation = userDTO.getAvatarLocation();
    }
}

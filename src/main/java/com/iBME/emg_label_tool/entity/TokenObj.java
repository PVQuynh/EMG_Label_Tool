package com.iBME.emg_label_tool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenObj implements Serializable {
    private String email;

    private String password;

    private LocalDateTime created;

}

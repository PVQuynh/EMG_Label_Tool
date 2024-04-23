package com.iBME.emg_label_tool.mapper;

import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.entity.User;

import java.util.List;

public interface UserMapper{
    User toEntity(UserDTO dto);
    UserDTO toDTO (User entity);

    List<UserDTO> toDTOList(List<User> entityList);
    List<User> toEntityList(List<UserDTO> dtoList);


}

package com.iBME.emg_label_tool.mapper;

import com.iBME.emg_label_tool.dto.RoleDTO;
import com.iBME.emg_label_tool.entity.Role;

import java.util.List;

public interface RoleMapper {
    Role toEntity(RoleDTO dto);
    RoleDTO toDTO (Role entity);

    List<RoleDTO> toDTOList(List<Role> entityList);
    List<Role> toEntityList(List<RoleDTO> dtoList);


}

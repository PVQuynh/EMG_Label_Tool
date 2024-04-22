package com.iBME.emg_label_tool.service;

import com.iBME.emg_label_tool.dto.RoleDTO;
import com.iBME.emg_label_tool.entity.Role;

import java.util.List;

public interface RoleService {

    void create(Role role);

    List<RoleDTO> getAllRole();
}

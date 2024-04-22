package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.RoleDTO;
import com.iBME.emg_label_tool.entity.Role;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.impl.RoleMapper;
import com.iBME.emg_label_tool.repository.RoleRepository;
import com.iBME.emg_label_tool.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private  final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public void create(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<RoleDTO> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) throw new BusinessLogicException();

        return roleMapper.toDTOList(roles);
    }
}

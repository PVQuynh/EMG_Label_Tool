package com.iBME.emg_label_tool.mapper.impl;

import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.entity.Role;
import com.iBME.emg_label_tool.entity.User;
import com.iBME.emg_label_tool.enum_constant.Sex;
import com.iBME.emg_label_tool.mapper.UserMapper;
import com.iBME.emg_label_tool.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper{

    private final RoleRepository roleRepository;

    @Override
    public User toEntity(UserDTO dto) {
        Sex sex = dto.getSex().equals("MALE") ? Sex.MALE : Sex.FEMALE;
        Role role = roleRepository.findByCode(dto.getRole()).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<UserDTO, User> typeMap =  modelMapper.createTypeMap(UserDTO.class,User.class);
        typeMap.addMappings(mapping->mapping.map(src->role,User::setRole));

        typeMap.addMappings(mapping->mapping.map(src-> sex,User::setSex));
        return modelMapper.map(dto,User.class);
    }

    @Override
    public UserDTO toDTO(User entity) {
        String role = entity.getRole().getCode();
        String gender = ObjectUtils.isNotEmpty(entity.getSex())? entity.getSex().getValue() : null;
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<User, UserDTO> typeMap =  modelMapper.createTypeMap(User.class,UserDTO.class);
        typeMap.addMappings(mapping->mapping.map(src->role,UserDTO::setRole));

        typeMap.addMappings(mapping->mapping.map(src->gender,UserDTO::setSex));
        UserDTO userRes =  modelMapper.map(entity,UserDTO.class);

        return  userRes;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> entityList) {
        return  entityList.stream()
                .map(user -> toDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> toEntityList(List<UserDTO> dtoList) {
        return null;
    }
}

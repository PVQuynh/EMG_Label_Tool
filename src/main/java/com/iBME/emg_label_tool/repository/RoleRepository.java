package com.iBME.emg_label_tool.repository;


import com.iBME.emg_label_tool.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByCode(String code);
}

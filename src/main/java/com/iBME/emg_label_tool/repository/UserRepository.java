package com.iBME.emg_label_tool.repository;


import com.iBME.emg_label_tool.entity.User;
import com.iBME.emg_label_tool.enum_constant.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.name=:name , u.phoneNumber=:phoneNumber, u.address=:address, u.dob=:dob," +
            "u.dob=:gender where u.email=:email")
    void updateUser(@Param("email") String email, @Param("name") String name, @Param("phoneNumber") String phoneNumber,
                    @Param("address") String address, @Param("dob") Date dob, @Param("gender") Sex sex);

    @Modifying
    @Transactional
    @Query("update User u set u.role.code=:code where u.email=:email")
    void changeRole(@Param("code") String code, @Param("email") String email);

}

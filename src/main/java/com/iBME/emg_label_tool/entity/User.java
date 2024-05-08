package com.iBME.emg_label_tool.entity;

import com.iBME.emg_label_tool.enum_constant.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends  BaseEntity {

    @Column(nullable = false)
    private  String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Email(message = "Email isn't valid")
    @Column(unique = true,nullable = false)
    private  String email;

    private String password;

    private String phoneNumber;

    private String address;

    private  String avatarLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "user_patient",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patientList;

}






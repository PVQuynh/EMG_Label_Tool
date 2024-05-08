package com.iBME.emg_label_tool.entity;

import com.iBME.emg_label_tool.enum_constant.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "patient_id"))
public class Patient extends  BaseEntity {

    @Column(nullable = false)
    private  String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private float height;

    private float weight;

    private boolean isDeleted;

    @OneToMany(
            mappedBy = "patient",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<DataFile> dataFileList;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "user_patient",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList;
}

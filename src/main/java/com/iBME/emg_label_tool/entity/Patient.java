package com.iBME.emg_label_tool.entity;

import com.iBME.emg_label_tool.enum_constant.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    private Date dob;

    private float height;

    private float weight;

    private boolean isDeleted;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "data_file_id")
    private DataFile dataFile;
}

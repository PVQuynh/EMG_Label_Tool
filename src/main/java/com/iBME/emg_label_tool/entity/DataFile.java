package com.iBME.emg_label_tool.entity;

import jakarta.persistence.*;
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
@Table(name = "data_file")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "data_file_id"))
public class DataFile extends  BaseEntity {

    private String fileName;

    private String muscle;

    private String side;

    private Date DoE; // Date of Examination

    @Column(nullable = false)
    private String dataFileLocation;

    private boolean isLabeled;

    private boolean isDeleted;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "user_data_file",
            joinColumns = @JoinColumn(name = "data_file_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList;

    @OneToMany(
            mappedBy = "dataFile",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<Label> labelList;
}

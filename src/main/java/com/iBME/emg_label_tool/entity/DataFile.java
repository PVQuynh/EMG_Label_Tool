package com.iBME.emg_label_tool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
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

    @Temporal(TemporalType.DATE)
    private Date doe; // Date of Examination

    @Column(nullable = false)
    private String dataFileLocation;

    private boolean isLabeled;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(
            mappedBy = "dataFile",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<Label> labelList;
}

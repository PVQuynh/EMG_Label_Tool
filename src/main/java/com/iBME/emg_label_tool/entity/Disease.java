package com.iBME.emg_label_tool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disease")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "disease_id"))
public class Disease extends  BaseEntity {

    @Column(nullable = false)
    private  String name;

    @OneToMany(
            mappedBy = "disease",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Label> labelList;
}

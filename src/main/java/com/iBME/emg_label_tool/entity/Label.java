package com.iBME.emg_label_tool.entity;

import com.iBME.emg_label_tool.dto.request.UpdateLabelReq;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "label")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "label_id"))
public class Label extends  BaseEntity {

    private float start;

    private float stop;

    private String note;

    private boolean doubleCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_file_id")
    private DataFile dataFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id")
    private Disease disease;

}

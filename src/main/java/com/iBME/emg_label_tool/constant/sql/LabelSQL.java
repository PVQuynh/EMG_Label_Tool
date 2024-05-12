package com.iBME.emg_label_tool.constant.sql;

public class LabelSQL {
    public static final String GET_LABEL_INFORMATION_CSV =
            """
                    SELECT
                            label.label_id AS labelId,
                            label.start,
                            label.stop,
                            disease.name AS diseaseName,
                            label.note,
                            null AS nullString,
                            patient.name AS patientName,
                            YEAR(NOW()) - YEAR(patient.dob) AS age,
                            patient.sex,
                            data_file.muscle,
                            data_file.side,
                            DATE(data_file.doe) AS doe,
                            TIME(data_file.doe) AS hoe,
                            null AS amplitude,
                            null AS samplingFrequency,
                            data_file.data_file_id AS dataFileId
                        FROM
                            label
                            JOIN data_file ON data_file.data_file_id = label.data_file_id
                            JOIN disease ON disease.disease_id = label.disease_id
                            JOIN patient ON patient.patient_id = data_file.patient_id
                        WHERE
                            data_file.data_file_id = :dataFileId
            """;
}

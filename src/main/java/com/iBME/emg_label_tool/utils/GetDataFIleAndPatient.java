package com.iBME.emg_label_tool.utils;

import com.iBME.emg_label_tool.dto.request.DataFileAndPatientReq;
import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.enum_constant.Sex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class GetDataFIleAndPatient {

    public static DataFileAndPatientReq GetDataFileAndPatientReqFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(fileName).openStream()))) {
            DataFileReq dataFileReq = new DataFileReq();
            PatientReq patientReq = new PatientReq();

            boolean sideFound = false;
            boolean muscleFound = false;
            String line;
            while ((line = br.readLine()) != null) {
                // lay thong tin cho DataFile
                if (!getFileName(fileName).isEmpty()) {
                    dataFileReq.setFileName(getFileName(fileName));
                }
                if (!fileName.isEmpty()) {
                    dataFileReq.setDataFileLocation(fileName);
                }
                if (!muscleFound && line.startsWith("\"Muscle\"")) {
                    dataFileReq.setMuscle(extractValueInTextLine(line));
                }
                if (!sideFound && line.startsWith("\"Side\"")) {
                    dataFileReq.setSide(extractValueInTextLine(line));
                    sideFound = true;
                }
                if (line.startsWith("\"Date of Examination\"")) {
                    dataFileReq.setDoe(new Date(extractValueInTextLine(line)));
                }

                // lay thong tin cho Patient
                if (line.startsWith("\"Name\"")) {
                    patientReq.setName(extractValueInTextLine(line));
                }
                if (line.startsWith("\"Sex\"")) {
                    patientReq.setSex(Sex.valueOf(extractValueInTextLine(line)));
                }
                if (line.startsWith("\"Date of Birth\"")) {
                    patientReq.setDob(new Date(extractValueInTextLine(line)));
                }
//                if (line.startsWith("\"Age\"")) {
//                    patientReq.setAge(extractValueInTextLine(line));
//                }
                if (line.startsWith("\"Height\"")) {
                    patientReq.setHeight(Float.parseFloat(extractValueInTextLine(line)));
                }
                if (line.startsWith("\"Weight\"")) {
                    patientReq.setHeight(Float.parseFloat(extractValueInTextLine(line)));
                }
            }

            // tao DataFileAndPatientReq
             return DataFileAndPatientReq.builder()
                    .dataFileReq(dataFileReq)
                    .patientReq(patientReq)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            return new DataFileAndPatientReq();
        }
    }

    // lấy ra tên file
    public static String getFileName(String fileName) {
        if (fileName.lastIndexOf('.') > 0) {
            return fileName.substring(0, fileName.lastIndexOf('.'));
        } else {
            return new String();
        }
    }

    public static String extractValueInTextLine(String line) {
        // Tách chuỗi thành các phần tử dựa trên dấu phẩy
        String[] parts = line.split(",");

        // Lấy phần tử thứ hai và loại bỏ dấu ngoặc kép
        String value = parts[1].replaceAll("\"", "").trim();

        return value;
    }
}

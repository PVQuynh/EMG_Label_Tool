package com.iBME.emg_label_tool.utils;

import com.iBME.emg_label_tool.dto.request.DataFileAndPatientReq;
import com.iBME.emg_label_tool.dto.request.DataFileReq;
import com.iBME.emg_label_tool.dto.request.PatientReq;
import com.iBME.emg_label_tool.enum_constant.Sex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDataFIleAndPatient {

    public static DataFileAndPatientReq GetDataFileAndPatientReqFromFile(String fileLocation) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(fileLocation).openStream()))) {
            DataFileReq dataFileReq = new DataFileReq();
            dataFileReq.setDataFileLocation(fileLocation);

            PatientReq patientReq = new PatientReq();

            // dam bao chi dem 1 lan duy nhat
            boolean sideFound = false;
            boolean muscleFound = false;
            boolean doeFound = false;

            boolean nameFound = false;
            boolean sexFound = false;
            boolean dobFound = false;
            boolean ageFound = false;
            boolean heightFound = false;
            boolean weightFound = false;

            int count = 0;
            String line;
            while ((line = br.readLine()) != null && count < 9) {
                // lay thong tin cho DataFile
                if (!muscleFound && line.startsWith("\"Muscle\"") && !extractValueInTextLine(line).isEmpty()) {
                    dataFileReq.setMuscle(extractValueInTextLine(line));
                    muscleFound = true;
                    count ++;
                }
                if (!sideFound && line.startsWith("\"Side\"") && !extractValueInTextLine(line).isEmpty()) {
                    dataFileReq.setSide(extractValueInTextLine(line));
                    sideFound = true;
                    count ++;
                }
                if (!doeFound && line.startsWith("\"Date of Examination\"") && !extractValueInTextLine(line).isEmpty()) {
                    dataFileReq.setDoe(revertDate(extractValueInTextLine(line)));
                    doeFound = true;
                    count ++;
                }

                // lay thong tin cho Patient
                if (!nameFound && line.startsWith("\"Name\"") && !extractValueInTextLine(line).isEmpty()) {
                    patientReq.setName(extractValueInTextLine(line));
                    nameFound = true;
                    count ++;
                }
                if (!sexFound && line.startsWith("\"Sex\"") && !extractValueInTextLine(line).isEmpty()) {
                    patientReq.setSex(Sex.valueOf(extractValueInTextLine(line).toUpperCase()));
                    sexFound = true;
                    count ++;
                }
                if (!dobFound && line.startsWith("\"Date of Birth\"")) {
                    if (!extractValueInTextLine(line).isEmpty()) {
                        patientReq.setDob(revertDate(extractValueInTextLine(line)));
                    }
                    dobFound = true;
                    count ++;
                }
                if (!ageFound && line.startsWith("\"Age\"") && !extractValueInTextLine(line).isEmpty()) {
                    patientReq.setAge(Integer.parseInt(extractValueInTextLine(line)));
                    ageFound = true;
                    count ++;
                }
                if (!heightFound && line.startsWith("\"Height\"")) {
                    if (!extractValueInTextLine(line).isEmpty()) {
                        patientReq.setHeight(Float.parseFloat(extractValueInTextLine(line)));
                    }
                    heightFound = true;
                    count ++;
                }
                if (!weightFound && line.startsWith("\"Weight\"")) {
                    if (!extractValueInTextLine(line).isEmpty()) {
                        patientReq.setHeight(Float.parseFloat(extractValueInTextLine(line)));
                    }
                    weightFound = true;
                    count ++;
                }
            }

            // tao DataFileAndPatientReq
             return DataFileAndPatientReq.builder()
                    .dataFileReq(dataFileReq)
                    .patientReq(patientReq)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new DataFileAndPatientReq();
        }
    }


    public static String extractValueInTextLine(String line) {
        // Tách chuỗi thành các phần tử dựa trên dấu phẩy
        String[] parts = line.split(",");

        // Lấy phần tử thứ hai và loại bỏ dấu ngoặc kép
        return parts[1].replaceAll("\"", "").trim();
    }

    public static Date revertDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date date = inputFormat.parse(dateString);

            String outputDate = outputFormat.format(date);
            return outputFormat.parse(outputDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

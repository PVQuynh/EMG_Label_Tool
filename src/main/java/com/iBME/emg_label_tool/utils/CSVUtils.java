package com.iBME.emg_label_tool.utils;

import com.iBME.emg_label_tool.dto.response.CSVRes;

import com.iBME.emg_label_tool.dto.response.ICSVRes;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


public class CSVUtils {

    public static ByteArrayInputStream labelsToCSV(List<ICSVRes> icsvRes) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(out, StandardCharsets.UTF_8), format)) {
            List<String> dataHeader = Arrays.asList("Label Id", "Start", "Stop", "Label", "Note", "", "Họ và tên", "Tuổi", "Giới tính", "Ngày khám", "Giờ khám", "Loại cơ", "Vị trí", "Biên độ", "Tần số lấy mẫu");
            csvPrinter.printRecord(dataHeader);

            for (ICSVRes res : icsvRes) {
                csvPrinter.printRecord(
                        res.getLabelId(),
                        res.getStart(),
                        res.getStop(),
                        res.getDiseaseName(),
                        res.getNote(),
                        res.getNullString(),
                        res.getPatientName(),
                        res.getAge(),
                        res.getSex(),
                        res.getDoe(),
                        res.getHoe(),
                        res.getMuscle(),
                        res.getSide(),
                        res.getAmplitude(),
                        res.getSamplingFrequency()
                );
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to CSV file: " + e.getMessage());
        }
    }
}

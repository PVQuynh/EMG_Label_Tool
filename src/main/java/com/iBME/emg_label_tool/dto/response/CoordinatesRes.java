package com.iBME.emg_label_tool.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.N;

import java.text.DecimalFormat;

@Getter
@Setter
@NoArgsConstructor
public class CoordinatesRes {
    private float x;
    private int y;

    public static CoordinatesRes getXYCoordinates(String line) {
        CoordinatesRes res = new CoordinatesRes();
        String[] parts = line.split(",");
        DecimalFormat df = new DecimalFormat("#.####");
        df.setDecimalSeparatorAlwaysShown(true);

        if (parts.length >= 2) {
            res.setX(Float.parseFloat(df.format(Float.parseFloat(parts[0]) / 10000)));
            res.setY(Integer.parseInt(parts[1]));
        }

        return res;
    }
}


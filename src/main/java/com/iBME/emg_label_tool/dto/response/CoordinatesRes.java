package com.iBME.emg_label_tool.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.N;

@Getter
@Setter
@NoArgsConstructor
public class CoordinatesRes {
    private int x;
    private int y;

    public static CoordinatesRes getXYCoordinates(String line) {
        CoordinatesRes res = new CoordinatesRes();
        String[] parts = line.split(",");

        if (parts.length >= 2) {
            res.setX(Integer.parseInt(parts[0]));
            res.setY(Integer.parseInt(parts[1]));
        }

        return res;
    }
}


package com.hhhhhx.mbgl.param.record;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class RecordWeekData {
    private List<String> x;
    private List<Integer> y1;
    private List<Integer> y2;
}

package edu.toronto.group0162.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GeneralTrip {

    @Getter
    @Setter

    private int uid;
    private int gtid;
    private boolean finished;
    private String startTime;
//    private String endTime;
//    private String start;
//    private String stop;
//    private double totalFare;
}

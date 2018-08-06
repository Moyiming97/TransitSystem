package edu.toronto.group0162.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Station {

    @Getter
    @Setter

    private int sid;
    private int point;
    private double next_distance;
    private String name;
    private String line;

}

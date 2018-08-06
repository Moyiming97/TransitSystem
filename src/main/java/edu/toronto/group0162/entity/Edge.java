package edu.toronto.group0162.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class Edge {

    @Getter
    @Setter

    private int eid;
    private int start;
    private int stop;
    private String edge;
    private double distance;
    private int duration; //秒
}

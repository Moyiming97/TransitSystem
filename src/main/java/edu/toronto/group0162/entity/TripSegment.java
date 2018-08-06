package edu.toronto.group0162.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TripSegment {

    @Getter
    @Setter
    private int tsid;
    private int uid;
    private int gtid;
    private int cid;
    private String enterTime;
    private String exitTime;
    private String entrance;
    private String exit;

    private String transitLine;
    private double fare;
    private double cap = 6;

    public String toString(){
        return "Card "+ cid+" was used "+"with charged of "+fare+" on "+transitLine+"\n"+
                " enter at "+entrance+" on "+enterTime+" and exit at "+exit+" on "+exitTime +"\n";
    }
}

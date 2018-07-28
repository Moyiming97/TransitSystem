package entity;

import lombok.Data;

@Data
public class Card {

  private int cid;

  private double balance = 19;

  private boolean isActive;

  private int uid;
}

package entity;

import lombok.Data;

@Data
public class Card {

  private int cid;

  private double balance;

  private boolean isActive;

  private int uid;
}

package edu.toronto.group0162.entity;

import lombok.Data;

@Data
public class Card {

  private int cid;

  private double balance = 19;

  private boolean isActive = true;

  private int uid;

  private long createAt;

  private boolean deleted = false;

}

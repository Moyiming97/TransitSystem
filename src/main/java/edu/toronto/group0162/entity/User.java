package edu.toronto.group0162.entity;

import lombok.Data;


@Data
public class User {

  private int uid;
  private String name;
  private String email;
  // check if user has admin's authority
  private boolean isAdmin;
  private String password;
  //option attribute from GUI
  private String Birthday;
  private String membership;
  private long createAt;

}

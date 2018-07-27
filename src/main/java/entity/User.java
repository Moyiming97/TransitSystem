package entity;

import lombok.Data;
import lombok.Value;

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

//  public User(int uid, String name, String email, boolean isAdmin, String password){
//    this.uid = uid;
//    this.name = name;
//    this.email = email;
//    this.isAdmin = false;
//    this.password = password;
//  }
}

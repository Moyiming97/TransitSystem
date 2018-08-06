package edu.toronto.group0162.service;

import edu.toronto.group0162.dao.CardDao;
import edu.toronto.group0162.dao.UserDao;
import edu.toronto.group0162.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserService {

  @Getter
  private final UserDao userDao;

  private final CardDao cardDao;

  public User registerUser(User user) {
    return this.userDao.save(user);
  }

  public User registerAdmin(User user) {
    User save = this.userDao.save(user);
    save.setAdmin(true);
    return this.userDao.updateAdmin(save);
  }



//  public void deleteCard(Card card) {
//    //card.getCid()
////    final Card cardDelete = this.cardDao.get(card.getCid());
//    if (this.cardDao.get(card.getCid()) != null) {
//      this.cardDao.delete(card.getCid());
//    }
//
//  }

  public boolean checkEmailAvailability(String email) {
    //如果是NUL，可以加这个USER
    return this.userDao.get(email) == null;
  }

  public User searchUser(String email){
    return this.userDao.get(email);
  }
}

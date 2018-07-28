package service;

import dao.CardDao;
import dao.UserDao;
import entity.Card;
import entity.User;
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

  public Card addCard(int uid, Card card) {
    final User user = this.userDao.get(uid);
    if (null == user) {
      return null;
    }
    card.setUid(uid);
    return this.cardDao.save(card);
  }

  public boolean checkEmailAvailability(String email) {
    //如果是NUL，可以加这个USER
    return this.userDao.get(email) == null;

  }
}

package dao;

import entity.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDao extends BaseDao<Integer, Card> {

  public CardDao(Connection connection) {
    super(connection);
  }

  @Override
  public Card get(Integer cid) {
    try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM transit_system.card WHERE cardnumber = ?")) {
      ps.setInt(1, cid);
      ResultSet result = ps.executeQuery();
      if (!result.next()) {
        return null;
      }
      Card card = new Card();
      card.setActive(result.getBoolean("isactive"));
      card.setBalance(result.getDouble("balance"));
      card.setCid(result.getInt("cid"));
      card.setUid(result.getInt("uid"));

      return card;
    } catch (SQLException ex) {
      Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  //have to use since delete card!!!（消卡）
  @Override
  public void delete(Integer pk) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  //加卡
  @Override
  public Card save(Card card) {
    try (PreparedStatement ps = this.connection.prepareStatement("INSERT INTO transit_system.card (uid, isActive, balance) VALUES (?, ?, ?) RETURNING cid")) {
      ps.setInt(1, card.getUid());
      ps.setBoolean(2, card.isActive());
      ps.setDouble(3, card.getBalance());
      ResultSet result = ps.executeQuery();
      if (!result.next()) {
        return null;
      }
      Card newCard = new Card();
      newCard.setActive(card.isActive());
      newCard.setBalance(card.getBalance());
      newCard.setCid(result.getInt(1));
      newCard.setUid(card.getUid());

      return newCard;
    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  //充值？？
  public Card update(Card bean) {
    //先往数据库里填新的名字，数据库永久保存，不是往JAVA里SET
    try (PreparedStatement ps = this.connection.prepareStatement("UPDATE transit_system.card SET balance = ?, isactive = ? WHERE cid = ?")) {
      ps.setDouble(1, bean.getBalance());
      ps.setBoolean(2, bean.isActive());
      ps.setInt(3, bean.getCid());
      ps.executeUpdate();
      return bean;
    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}

package edu.toronto.group0162.dao;

import edu.toronto.group0162.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class UserDao extends BaseDao<Integer, User> {

  public UserDao(Connection connection) {
    super(connection);
  }

  //have to use to get card or sth else 查询
//  @Override
  public User get(Integer uid) {
    try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM transit_system.user WHERE uid = ?")) {
      ps.setInt(1, uid);
      ResultSet result = ps.executeQuery();
      if (!result.next()) {
        return null;
      }
      User user = new User();
      user.setUid(result.getInt("uid"));
      user.setAdmin(result.getBoolean("isadmin"));
      user.setEmail(result.getString("email"));
      user.setName(result.getString("name"));

      return user;
    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }



  //  //have to use since delete card!!!（消卡）
//  @Override
  public void delete(Integer pk) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


//  @Override
  public User save(User user) {
    //往数据库里插数据，UID数据库系统生成
    try (PreparedStatement ps = this.connection.prepareStatement("INSERT INTO transit_system.user (name, email, password, createat) VALUES (?, ?, ?, ?) RETURNING uid")) {

      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPassword());
      ps.setLong(4, Instant.now().getEpochSecond());

      ResultSet result = ps.executeQuery();

      //查询-
      ////RESULT.NEXT 是 TRUE 则是有数据 相当于-问
      if (!result.next()) {
        return null;
      }

      User newUser = new User();
      // 要展示哪些 属性 弄清
      newUser.setUid(result.getInt(1));
      newUser.setAdmin(false);
      newUser.setEmail(user.getEmail());
      newUser.setName(user.getName());

      return newUser;

    } catch (SQLException ex) {
 // logger.log(Level.SEVERE, null, ex);
    log.error("save error");
    }
    return null;

  }

  //UserService change register info to call update
  public User updateName(User user) {
    //先往数据库里填新的名字，数据库永久保存，不是往JAVA里SET
    try (PreparedStatement ps = this.connection.prepareStatement("UPDATE transit_system.user SET name = ? WHERE uid = ?")) {
      ps.setString(1, user.getName());
      ps.setInt(2, user.getUid());
      ps.executeUpdate();
      return user;
    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public User updateAdmin(User user) {

    try (PreparedStatement ps = this.connection.prepareStatement("UPDATE transit_system.user SET isadmin = ? WHERE uid = ?")) {
      ps.setBoolean(1, user.isAdmin());
      ps.setInt(2, user.getUid());
      ps.executeUpdate();
      return user;
    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public User get(String email) {
    try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM transit_system.user WHERE email = ?")) {
      ps.setString(1, email);
      ResultSet result = ps.executeQuery();

      if (!result.next()) {
        return null;
      }
      User user = new User();

      user.setUid(result.getInt("uid"));
      user.setAdmin(result.getBoolean("isadmin"));
      user.setEmail(result.getString("email"));
      user.setName(result.getString("name"));
      user.setCreateAt(result.getLong("createat"));

      return user;
    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public boolean authenticate(String email, String password) {
    try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM transit_system.user WHERE email = ? and password = ?")) {

      ps.setString(1, email);
      ps.setString(2,password);

      ResultSet result = ps.executeQuery();

      return result.next();
    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
    }

    return false;
  }
}

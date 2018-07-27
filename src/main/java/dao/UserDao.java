package dao;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends BaseDao<Integer, User> {

  public UserDao(Connection connection) {
    super(connection);
  }

  //have to use to get card or sth else 查询
  @Override
  public User get(Integer uid) {
    try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM transit_system.user WHERE uid = ?")) {
      ps.setInt(1, uid);
      ResultSet result = ps.executeQuery();
      if (!result.next()) {
        return null;
      }
      User user = new User();
      //要展示哪些 属性 弄清
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

  //prob not in use coz no delete user (check!)
  @Override
  public void delete(Integer pk) {
    try (PreparedStatement ps = this.connection.prepareStatement("DELETE FROM transit_system.user WHERE uid = ?")) {
      ps.setInt(1, pk);
      ps.execute();
    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  //UserService register to call save
  //告诉SERVICE层建了哪个用户
  //uid我说的不算，是数据库系统自己决定生成的
  @Override
  public User save(User user) {
    //往数据库里插数据，UID数据库系统生成
    try (PreparedStatement ps = this.connection.prepareStatement("INSERT INTO public.user (name, email, password) VALUES (?, ?, ?) RETURNING uid")) {
      //how about save admin user??? think!
      //啥时把 uid 自动生成好了？
      // !!!!!!!!以下三行代码是彻底的把数据保存在数据库里，电脑关了、intellij关了，这个数据都不会丢，但得保证数据库被导出做备份
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPassword());
      //result - ps.executeQuery()建立游标，然后返回给ResultSet
      ResultSet result = ps.executeQuery();

      //查询-
      ////RESULT.NEXT 是 TRUE 则是有数据 相当于-问
      if (!result.next()) {
        return null;
      }

//            return result.next()
//                    ? new User(result.getInt(1), user.getName(), user.getEmail(),user.getPassword())
//                    : null;
      //！！！以下这个newUser 是开始真正向JAVA实例化，就是说这个new才是真正建立的object并存储在java内存在，但电脑关了、intellij关了，
      //照样 这个 object会在java内存里消失 ！！！
      User newUser = new User();
      //要展示哪些 属性 弄清
      newUser.setUid(result.getInt(1));
      newUser.setAdmin(false);
      newUser.setEmail(user.getEmail());
      newUser.setName(user.getName());

      return newUser;

    } catch (SQLException ex) {
      Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
    //先往数据库里填新的名字，数据库永久保存，不是往JAVA里SET
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
      //RESULT.NEXT 是 TRUE 则是有数据
      if (!result.next()) {//没找到就返回NULL
        return null;
      }
      User user = new User();
      //要展示哪些 属性 弄清
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

}

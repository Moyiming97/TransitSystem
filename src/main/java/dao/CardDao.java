package dao;

import entity.Card;
import entity.User;
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
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM public.card WHERE cardnumber = ?")) {
            ps.setInt(1, cid);
            ResultSet result = ps.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new Card(result.getInt("cid"),
                    result.getDouble("balance"),
                    result.getBoolean("isactive"),
                    result.getInt("uid"));
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
        try (PreparedStatement ps = this.connection.prepareStatement("INSERT INTO public.card (uid, isActive, balance) VALUES (?, ?, ?) RETURNING cid")) {
            ps.setInt(1, card.uid);
            ps.setBoolean(2, card.isActive);
            ps.setDouble(3, card.balance);
            ResultSet result = ps.executeQuery();
            return result.next()
                    ? new Card(result.getInt(1),
                    result.getDouble("balance"),
                    result.getBoolean("isActive"),
                    result.getInt("uid"))
                    : null;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


      //充值？？
    public Card update(Card bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

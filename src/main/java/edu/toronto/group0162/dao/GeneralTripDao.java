package edu.toronto.group0162.dao;

import com.sun.tools.javah.Gen;
import edu.toronto.group0162.entity.Card;
import edu.toronto.group0162.entity.GeneralTrip;
import edu.toronto.group0162.entity.TripSegment;
import edu.toronto.group0162.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GeneralTripDao extends BaseDao<Integer, GeneralTripDao> {

    public GeneralTripDao(Connection connection) {
        super(connection);
}

    //  @Override
    public GeneralTrip save(GeneralTrip generalTrip) {
        try (PreparedStatement ps = this.connection.prepareStatement
                ("INSERT INTO transit_system.generaltrip " +
                        "(uid, starttime, finished) " +
                        "VALUES (?, ?, ?) RETURNING gtid")) {
            ps.setInt(1, generalTrip.getUid());
            ps.setString(2, generalTrip.getStartTime());
            ps.setBoolean(3,generalTrip.isFinished());

            ResultSet result = ps.executeQuery();
            if (!result.next()) {
                return null;
            }
            GeneralTrip newGeneralTrip = new GeneralTrip();
            newGeneralTrip.setStartTime(newGeneralTrip.getStartTime());
            newGeneralTrip.setGtid(result.getInt(1));
            newGeneralTrip.setUid(newGeneralTrip.getUid());
            newGeneralTrip.setFinished(false);


            return newGeneralTrip;
        } catch (SQLException ex) {
            Logger.getLogger(GeneralTripDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public GeneralTrip updateFinished(GeneralTrip generalTrip) {

        try (PreparedStatement ps = this.connection.prepareStatement("UPDATE transit_system.generaltrip SET finished = ? WHERE gtid = ?")) {
            ps.setBoolean(1, generalTrip.isFinished());
            ps.setInt(2, generalTrip.getGtid());
            ps.executeUpdate();
            return generalTrip;
        } catch (SQLException ex) {
            Logger.getLogger(GeneralTripDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public GeneralTrip get(Integer uid, Boolean finished) {
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM transit_system.generaltrip WHERE uid = ? and finished = ?")) {
            ps.setInt(1, uid);
            ps.setBoolean(2,finished);
            ResultSet result = ps.executeQuery();
            if (!result.next()) {
                return null;
            }

            GeneralTrip newGeneralTrip = new GeneralTrip();
            newGeneralTrip.setUid(result.getInt("uid"));
            newGeneralTrip.setGtid(result.getInt("gtid"));
            newGeneralTrip.setFinished(result.getBoolean("finished"));
            newGeneralTrip.setStartTime(result.getString("starttime"));
            return newGeneralTrip;

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void delete() {
        try (PreparedStatement ps = this.connection.prepareStatement("DELETE FROM transit_system.generaltrip WHERE starttime < '2011-09-21 08:21:22'")) {
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<GeneralTrip> getLatest(Integer uid) {
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM transit_system.generaltrip WHERE uid = ? ORDER BY starttime DESC limit 3")) {
            ps.setInt(1, uid);
            ResultSet result = ps.executeQuery();
//            if (!result.next()) {
//                return null;
//            }
            List generalTrips = new ArrayList<GeneralTrip>();
            while (result.next()) {

            GeneralTrip newGeneralTrip = new GeneralTrip();
            newGeneralTrip.setUid(result.getInt("uid"));
            newGeneralTrip.setGtid(result.getInt("gtid"));
            newGeneralTrip.setFinished(result.getBoolean("finished"));
            newGeneralTrip.setStartTime(result.getString("starttime"));
            generalTrips.add(newGeneralTrip);}

            return generalTrips;

        } catch (SQLException ex) {
            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }




}

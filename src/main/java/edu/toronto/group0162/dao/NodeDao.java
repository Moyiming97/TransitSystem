package edu.toronto.group0162.dao;

import edu.toronto.group0162.dao.BaseDao;
import edu.toronto.group0162.dao.CardDao;
import edu.toronto.group0162.entity.Node;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Data
public class NodeDao extends BaseDao<Integer, Node> {

    public NodeDao(Connection connection) {
        super(connection);
    }

    public List<Node> getNodes() {
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM transit_system.node")) {
            //ps.setInt(1, uid);
            ResultSet result = ps.executeQuery();

            List s = new ArrayList<Node>();
            while (result.next()) {
                Node node = new Node();
                node.setTransit_type(result.getString("transit_type"));
                node.setName(result.getString("name"));
                node.setNid(result.getInt("nid"));

                s.add(node);
            }
            return s;

        } catch (SQLException ex) {
            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}

package edu.toronto.group0162;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.sun.tools.javah.Gen;
import edu.toronto.group0162.dao.*;
import edu.toronto.group0162.entity.*;
import edu.toronto.group0162.service.Graph;
import lombok.extern.slf4j.Slf4j;
import edu.toronto.group0162.view.Window;

//连接java后台和数据库
//连接java前台和数据库
//启动主程序

@Slf4j
public class Main {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver"); //读取数据库驱动
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "GGG", "");
    //建立数据库连接
    //创建GUI
    final Window window = new Window(connection);
    //显示GUI
    window.setVisible(true);

    log.info("test log at sl4j");
    log.error("throw exception");
    log.warn("without tapping out");
////
//    User user = new User();
//    user.setPassword("123456");
//    user.setAdmin(false);
//    user.setName("L Tiay");
//    user.setEmail("litay@gmail.com");
//////
//
//////
//    UserDao userDao = new UserDao(connection);
//    CardDao cardDao = new CardDao(connection);
//
//    User savedUser = userDao.save(user);
//
////    Date date = new Date(1532793259000L);
////    System.out.println(date);
////
////    //test to add card/delete card
//
//    CardService cardService = new CardService(cardDao,userDao);
//    Card card1 = new Card();
//    Card card2 = new Card();
//    Card card3 = new Card();
//
//    Card savedCard1 = cardService.addCard(savedUser.getUid(),card1);
//    Card savedCard2 = cardService.addCard(savedUser.getUid(),card2);
//    Card savedCard3 = cardService.addCard(savedUser.getUid(),card3);
//
//    Card updatedCard = cardService.addBalance(savedCard1,20);
//
//    List<Card> listCards = cardDao.getByUid(savedUser.getUid());
//
//    Iterator cardsList = listCards.iterator();
//    while(cardsList.hasNext()) {
//      Card cardGet = (Card) cardsList.next();
//      System.out.print(" cid: "+ cardGet.getCid());
//    }
//    System.out.println();

//        NodeDao nodeDao = new NodeDao(connection);
//    EdgeDao edgeDao = new EdgeDao(connection);
//    StationDao stationDao = new StationDao(connection);

//    List<Node> listNodes = nodeDao.getNodes();
//    List<Edge> listEdges = edgeDao.getEdges();
//    List<Station> listStations = stationDao.getStations();
//
//    Iterator stationsList = listStations.iterator();
//    while(stationsList.hasNext()) {
//      Station stationGet = (Station) stationsList.next();
//      System.out.print(" station name: "+ stationGet.getName()+" "+"\n");
//    }
//    System.out.println();

//
//
//        Iterator nodesList = listNodes.iterator();
//    while(nodesList.hasNext()) {
//      Node nodeGet = (Node) nodesList.next();
//      System.out.print(" node name: "+ nodeGet.getName()+" ");
//    }
//    System.out.println();
//
//    Iterator edgesList = listEdges.iterator();
//    while(edgesList.hasNext()) {
//      Edge edgeGet = (Edge) edgesList.next();
//      System.out.print(" edge type: "+ edgeGet.getEdge()+" ");
//    }
//    System.out.println();
//
//    Graph graph = new Graph(listNodes, listEdges);
//
//    int[][] path = new int[graph.mVexs.length][graph.mVexs.length];
//    double[][] floy = new double[graph.mVexs.length][graph.mVexs.length];
//
//    graph.floyd(path, floy);
//
//    graph.GetShortestPath("a","e",floy,path,listNodes);
//
//    UserDao userDao = new UserDao(connection);
//    User user = new User();
//    user.setPassword("123456");
//    user.setAdmin(false);
//    user.setName("Dan Ying");
//    user.setEmail("DAsayunk@gmail.com");
//
//    User savedUser = userDao.save(user);
//
//    GeneralTripDao generalTripDao = new GeneralTripDao(connection);
//
//    GeneralTrip generalTrip1 = new GeneralTrip();
//    generalTrip1.setUid(savedUser.getUid());
//    generalTrip1.setStartTime("2011-09-21 06:22:22");
//
//    GeneralTrip generalTrip2 = new GeneralTrip();
//    generalTrip2.setUid(savedUser.getUid());
//    generalTrip2.setStartTime("2011-09-21 17:23:22");

//    GeneralTrip generalTrip3 = new GeneralTrip();
//    generalTrip3.setUid(savedUser.getUid());
//    generalTrip3.setStartTime("2011-09-21 05:25:22");
//
//    GeneralTrip savedGeneralTrip1 = generalTripDao.save(generalTrip1);
//    GeneralTrip savedGeneralTrip2 = generalTripDao.save(generalTrip2);
//    GeneralTrip savedGeneralTrip3 = generalTripDao.save(generalTrip3);

//    List<GeneralTrip> listGeneralTrips = generalTripDao.getLatest(savedUser.getUid());
//
//        Iterator generalTripsList = listGeneralTrips.iterator();
//    while(generalTripsList.hasNext()) {
//      GeneralTrip generalTripGet = (GeneralTrip) generalTripsList.next();
//      System.out.print(" gtid: "+ generalTripGet.getGtid()+" "+"\n");
//    }


//    GeneralTrip trip = generalTripDao.order();
//    System.out.println(trip.getStartTime());
//
//    GeneralTrip foundGeneralTrip = generalTripDao.get(3,false);
//    System.out.println(foundGeneralTrip.getStartTime());



  }
}

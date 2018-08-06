package edu.toronto.group0162.view;

import edu.toronto.group0162.dao.*;
import edu.toronto.group0162.entity.Card;
import edu.toronto.group0162.service.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class UserFrame extends JFrame{

    private JButton btnCardMgt;
    private JButton btnTripManagement;
    private JButton btnPersonalInfo;
    private JButton btnLogOut;
    private Window window;

    private UserService userService;
    private CardService cardService;
    private TripSegmentService tripSegmentService;
    private TimeService timeService;
    private GeneralTripService generalTripService;
    private StationService stationService;
    private NodeDao nodeDao;
    private EdgeDao edgeDao;
    private StationDao stationDao;
    private TripSegmentDao tripSegmentDao;
    private GeneralTripDao generalTripDao;

    @Getter
    @Setter
    private int currentLogInUid;

//    CardMgtFrame cardMgtFrame = new CardMgtFrame();
    CityFrame cityFrame = new CityFrame("City Selection");
    CardMgtFrame cardMgtFrame;


    private JTextField t;


    public UserFrame(String title) {

        super( title );                      // invoke the JFrame constructor
        setSize( 500, 300 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        setLayout( new FlowLayout() );       // set the layout manager

        btnCardMgt = new JButton ("Card Management");
        btnTripManagement = new JButton ("Trip Management");
        btnPersonalInfo = new JButton ("Personal Info");
        btnLogOut = new JButton ("Back");
        t = new JTextField();

        add (btnCardMgt);
        add (btnTripManagement);
        add (btnPersonalInfo);
        add(btnLogOut);
        add(t);

//        this.cardMgtFrame.setUserFrame(this);

        btnCardMgt.addActionListener((ActionEvent e) -> this.onClickCardMgt(e));
        btnLogOut.addActionListener((ActionEvent e) -> this.onClickLogOut(e));
        btnTripManagement.addActionListener((ActionEvent e) -> this.onClickTrip(e));
    }

        public void setWindow(Window window){
        this.window = window;
        }

    private void onClickCardMgt(ActionEvent e) {

        this.cardMgtFrame = new CardMgtFrame();

        this.cardMgtFrame.setUserFrame(this);

        this.setVisible(false);

        this.cardMgtFrame.frame.setVisible(true);

        this.cardMgtFrame.setCurrentLogInUid(this.currentLogInUid);

        this.cardMgtFrame.setCardService(this.cardService);


        if(null == this.cardMgtFrame.cardNumBox){
        List<Card> listCards = this.cardService.getCardDao().getByUid(this.currentLogInUid);
        Iterator cardsList = listCards.iterator();
        while(cardsList.hasNext()) {
            Card cardGet = (Card) cardsList.next();
            this.cardMgtFrame.cardNumBox.addItem(cardGet.getCid());
        }}

        else{
            this.cardMgtFrame.cardNumBox.removeAllItems();
            List<Card> listCards = this.cardService.getCardDao().getByUid(this.currentLogInUid);
            Iterator cardsList = listCards.iterator();
            while(cardsList.hasNext()) {
                Card cardGet = (Card) cardsList.next();
                this.cardMgtFrame.cardNumBox.addItem(cardGet.getCid());
            }
        }

    }

    private void onClickLogOut(ActionEvent e) {
    this.setVisible(false);
    this.window.setVisible(true);
    }

    private void onClickTrip(ActionEvent e) {
        this.setVisible(false);
        this.cityFrame.setVisible(true);
        this.cityFrame.setCurrentLogInUid(this.currentLogInUid);
        this.cityFrame.setDaos(nodeDao,edgeDao,stationDao, tripSegmentDao, generalTripDao);
        this.cityFrame.setUserFrame(this);
        this.cityFrame.setCurrentLogInUid(this.currentLogInUid);
        this.cityFrame.setServices(cardService, tripSegmentService,generalTripService,timeService,stationService);
    }

    public void setTextField(String username){
            this.t.setText(username);
    }

    public void setServices(UserService userService,CardService cardService, TripSegmentService tripSegmentService, GeneralTripService generalTripService, TimeService timeService,StationService stationService){
        this.userService = userService;
        this.cardService = cardService;
        this.tripSegmentService = tripSegmentService;
        this.generalTripService = generalTripService;
        this.timeService = timeService;
    this.stationService = stationService;}

    public void setDaos(NodeDao nodeDao, EdgeDao edgeDao, StationDao stationDao,
                        TripSegmentDao tripSegmentDao, GeneralTripDao generalTripDao){
        this.nodeDao = nodeDao;
        this.edgeDao = edgeDao;
        this.stationDao = stationDao;
        this.tripSegmentDao = tripSegmentDao;
        this.generalTripDao = generalTripDao;
    }




}

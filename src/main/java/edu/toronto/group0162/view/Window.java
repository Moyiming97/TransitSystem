package edu.toronto.group0162.view;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;

import edu.toronto.group0162.dao.*;
import edu.toronto.group0162.service.*;


public class Window extends JFrame {

  private final JButton btnLogin = new JButton("Log In");

  private final JButton btnRegister = new JButton("Sign Up");

  private final JButton btnExit = new JButton("Exit");

  private UserService userService;

  private CardService cardService;

  private StationService stationService;

  private TripSegmentService tripSegmentService;

  private TimeService timeService;
  private GeneralTripService generalTripService;

  private UserDao userDao;

  private CardDao cardDao;

  private NodeDao nodeDao;

  private EdgeDao edgeDao;

  private StationDao stationDao;

  private TripSegmentDao tripSegmentDao;

  private GeneralTripDao generalTripDao;


  /**
   * initialize edu.toronto.group0162.dao and edu.toronto.group0162.service
   * @param connection
   */
  private void initializeDependency(final Connection connection) {
    this.userDao = new UserDao(connection);
    this.cardDao = new CardDao(connection);
    this.nodeDao = new NodeDao(connection);
    this.edgeDao = new EdgeDao(connection);
    this.stationDao = new StationDao(connection);
    this.tripSegmentDao = new TripSegmentDao(connection);
    this.generalTripDao = new GeneralTripDao(connection);
    this.userService = new UserService(this.userDao, this.cardDao);
    this.cardService = new CardService(this.cardDao,this.userDao);
    this.timeService = new TimeService();
    this.stationService = new StationService(this.stationDao);
    this.generalTripService = new GeneralTripService(this.generalTripDao,this.tripSegmentService,this.tripSegmentDao);
    this.tripSegmentService = new TripSegmentService(this.tripSegmentDao,this.generalTripDao,this.cardService,this.stationService);
  }

  private void initializeComponent() {
    btnLogin.addActionListener((ActionEvent e) -> {
      edu.toronto.group0162.view.LoginDialog loginDlg =
              new edu.toronto.group0162.view.LoginDialog
                      (this,this.userService,this.cardService,this.tripSegmentService,this.generalTripService,this.timeService,
                              this.stationService,this.nodeDao,this.edgeDao,this.stationDao,this.tripSegmentDao, this.generalTripDao);
      loginDlg.setWindow(this);
      this.setVisible(false);
      loginDlg.setVisible(true);
    });

    btnRegister.addActionListener((ActionEvent e) -> {
      edu.toronto.group0162.view.SignUpDialog signUpDialog = new edu.toronto.group0162.view.SignUpDialog(this, this.userService);
      signUpDialog.setVisible(true);
      // if register successfully
//      if (signUpDialog.isSucceeded()) {
//        btnRegister.setText("Hi " + signUpDialog.getUsername() + "!");
//      }
    });

    btnExit.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });

    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    super.setSize(300, 150);
    super.setLayout(new FlowLayout());
    super.getContentPane().add(btnLogin);
    super.getContentPane().add(btnRegister);
    super.getContentPane().add(btnExit);
  }


  public Window(final Connection connection) throws HeadlessException {
    super("Transit System");
    this.initializeDependency(connection);
    this.initializeComponent();
  }

}

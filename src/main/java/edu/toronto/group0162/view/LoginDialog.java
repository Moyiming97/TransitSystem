package edu.toronto.group0162.view;

import edu.toronto.group0162.dao.*;
import edu.toronto.group0162.entity.GeneralTrip;
import edu.toronto.group0162.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class LoginDialog extends JDialog {

  @Getter
  @Setter
  private int currentLogInUid;

  private final UserService userService;
  private final CardService cardService;
  private final TripSegmentService tripSegmentService;
  private final GeneralTripService generalTripService;
  private final TimeService timeService;
  private final StationService stationService;
  private final NodeDao nodeDao;
  private final EdgeDao edgeDao;
  private final StationDao stationDao;
  private TripSegmentDao tripSegmentDao;
  private GeneralTripDao generalTripDao;

  private JTextField tfEmail;
  private JPasswordField pfPassword;
  private JLabel lbEmail;
  private JLabel lbPassword;
  private JButton btnLogin;
  private JButton btnCancel;
  private boolean succeeded;

  UserFrame userFrame = new UserFrame("User edu.toronto.group0162.Main Page");

  private Window window;

  public LoginDialog(Frame parent, UserService userService, CardService cardService, TripSegmentService tripSegmentService,
                     GeneralTripService generalTripService, TimeService timeService, StationService stationService,NodeDao nodeDao, EdgeDao edgeDao, StationDao stationDao, TripSegmentDao tripSegmentDao, GeneralTripDao generalTripDao) {

    super(parent, "Login", true);
    this.userService = userService;
    this.cardService = cardService;
    this.tripSegmentService = tripSegmentService;
    this.generalTripService = generalTripService;
    this.timeService = timeService;
    this.stationService = stationService;
    this.nodeDao = nodeDao;
    this.edgeDao = edgeDao;
    this.stationDao = stationDao;
    this.tripSegmentDao = tripSegmentDao;
    this.generalTripDao = generalTripDao;

//    this.userFrame = new UserFrame("User edu.toronto.group0162.Main Page");
    //
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();

    cs.fill = GridBagConstraints.HORIZONTAL;

    lbEmail = new JLabel("Email: ");
    cs.gridx = 0;
    cs.gridy = 0;
    cs.gridwidth = 1;
    panel.add(lbEmail, cs);

    tfEmail = new JTextField(20);
    cs.gridx = 1;
    cs.gridy = 0;
    cs.gridwidth = 2;
    panel.add(tfEmail, cs);

    lbPassword = new JLabel("Password: ");
    cs.gridx = 0;
    cs.gridy = 1;
    cs.gridwidth = 1;
    panel.add(lbPassword, cs);

    pfPassword = new JPasswordField(20);
    cs.gridx = 1;
    cs.gridy = 1;
    cs.gridwidth = 2;
    panel.add(pfPassword, cs);
    panel.setBorder(new LineBorder(Color.GRAY));

    btnLogin = new JButton("Login");

    btnLogin.addActionListener((ActionEvent e) -> this.onClickLogIn(e));

    // back to LogIn Page
    btnCancel = new JButton("Cancel");
    btnCancel.addActionListener(
        new ActionListener() {

          public void actionPerformed(ActionEvent e) {
            dispose();
          }
        });
    JPanel bp = new JPanel();
    bp.add(btnLogin);
    bp.add(btnCancel);

    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(bp, BorderLayout.PAGE_END);

    pack();
    setResizable(false);
    setLocationRelativeTo(parent);
  }

  private void showErrorMessage() {
    JOptionPane.showMessageDialog(
        LoginDialog.this,
        "Wrong email/password or Account does not exit",
        "Wong LogIn",
        JOptionPane.ERROR_MESSAGE);
    // reset username and password
    tfEmail.setText("");
    pfPassword.setText("");
    succeeded = false;
  }

  private void onClickLogIn(ActionEvent e) {

    if (this.userService.getUserDao().authenticate(this.getEmail(),this.getPassword()) == false){
      this.showErrorMessage();
      return; //提前结束 - early exit
    }

    this.setVisible(false);

    this.userFrame.setVisible(true);
    this.userFrame.setWindow(window);
    this.userFrame.setCurrentLogInUid(this.userService.getUserDao().get(this.getEmail()).getUid());
    this.userFrame.setTextField("Welcome "+this.userService.getUserDao().get(this.getEmail()).getName());
    this.userFrame.setServices(userService,cardService, tripSegmentService,generalTripService, timeService,stationService);
    this.userFrame.setDaos(this.nodeDao, this.edgeDao, this.stationDao, this.tripSegmentDao, this.generalTripDao);

//    List<Card> listCards = this.cardService.getCardDao().getByEmail(this.getEmail());
//    Iterator cardsList = listCards.iterator();
//        while(cardsList.hasNext()) {
//            Card cardGet = (Card) cardsList.next();
//            this.cardMgtFrame.getCardList().c.addItem(cardGet.getCid());
//        }

    //set up all uid's info into user main's parent/child pages
  }

  //    public String getUsername() {
  //        return tfUsername.getText().trim();
  //    }

  public String getPassword() {
    return new String(pfPassword.getPassword());
  }

  public String getEmail() {
    return tfEmail.getText().trim();
  }

//  public boolean isSucceeded() {
//    return succeeded;
//  }

  public void setWindow(Window window){
    this.window = window;
  }
}

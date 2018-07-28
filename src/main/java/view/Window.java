package view;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;

import dao.CardDao;
import dao.UserDao;
import service.UserService;

/**
 *
 * @author Rugal Bernstein
 */
public class Window extends JFrame {

  private final JButton btnLogin = new JButton("Log In");

  private final JButton btnRegister = new JButton("Sign Up");

  private UserService userService;

  private UserDao userDao;

  private CardDao cardDao;

  private void initializeDependency(final Connection connection) {
    this.userDao = new UserDao(connection);
    this.cardDao = new CardDao(connection);
    this.userService = new UserService(this.userDao, this.cardDao);
  }

  private void initializeComponent() {
    btnLogin.addActionListener((ActionEvent e) -> {
      view.LoginDialog loginDlg = new view.LoginDialog(this);
      loginDlg.setVisible(true);
      // if logon successfully
      if (loginDlg.isSucceeded()) {
        btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
      }
    });

    btnRegister.addActionListener((ActionEvent e) -> {
      view.SignUpDialog signUpDialog = new view.SignUpDialog(this, this.userService);
      signUpDialog.setVisible(true);
      // if register successfully
      if (signUpDialog.isSucceeded()) {
        btnRegister.setText("Hi " + signUpDialog.getUsername() + "!");
      }
    });

    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    super.setSize(300, 150);
    super.setLayout(new FlowLayout());
    super.getContentPane().add(btnLogin);
    super.getContentPane().add(btnRegister);
  }

  public Window(final Connection connection) throws HeadlessException {
    super("Transit System");
    this.initializeDependency(connection);
    this.initializeComponent();
    super.setVisible(true);
  }

}

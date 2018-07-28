package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;

import entity.User;
import service.UserService;

public class SignUpDialog extends JDialog {

  private final UserService userService;

  private final JTextField tfUsername;

  private final JPasswordField pfPassword;

  private final JTextField tfEmail;

  private final JLabel lbUsername;

  private final JLabel lbPassword;

  private final JLabel lbEmail;

  private final JButton btnRegister;

  private final JButton btnCancel;

  private boolean succeeded;

  private void onClickRegister(ActionEvent e) {

    User user = new User();
    user.setEmail(this.getEmail());
    user.setName(this.getName());
    user.setPassword(this.getPassword());

    final User get = this.userService.getUserDao().get(user.getEmail());
    if (get != null) {
      JOptionPane.showMessageDialog(SignUpDialog.this,
                                    "Invalid email or email existed",
                                    "Register",
                                    JOptionPane.ERROR_MESSAGE);
      // reset username and password
      tfUsername.setText("");
      pfPassword.setText("");
      succeeded = false;
      return;
    }
    this.userService.registerUser(user);

    JOptionPane.showMessageDialog(SignUpDialog.this,
                                  "! You have successfully registered.",
                                  "Register",
                                  JOptionPane.INFORMATION_MESSAGE);
    succeeded = true;
    dispose();
  }

  public SignUpDialog(Frame parent, UserService userService) {
    super(parent, "Register", true);
    this.userService = userService;
    //
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();

    cs.fill = GridBagConstraints.HORIZONTAL;

    lbUsername = new JLabel("Name: ");
    cs.gridx = 0;
    cs.gridy = 0;
    cs.gridwidth = 1;
    panel.add(lbUsername, cs);

    tfUsername = new JTextField(15);
    cs.gridx = 1;
    cs.gridy = 0;
    cs.gridwidth = 2;
    panel.add(tfUsername, cs);

    lbPassword = new JLabel("Password: ");
    cs.gridx = 0;
    cs.gridy = 1;
    cs.gridwidth = 1;
    panel.add(lbPassword, cs);

    pfPassword = new JPasswordField(15);
    cs.gridx = 1;
    cs.gridy = 1;
    cs.gridwidth = 2;
    panel.add(pfPassword, cs);
    panel.setBorder(new LineBorder(Color.GRAY));

    lbEmail = new JLabel("Email: ");
    cs.gridx = 0;
    cs.gridy = 2;
    cs.gridwidth = 1;
    panel.add(lbEmail, cs);

    tfEmail = new JPasswordField(15);
    cs.gridx = 1;
    cs.gridy = 2;
    cs.gridwidth = 2;
    panel.add(tfEmail, cs);

    btnRegister = new JButton("Register");

    btnRegister.addActionListener(SignUpDialog::onClickRegister);

    //back to LogIn Page
    btnCancel = new JButton("Cancel");
    btnCancel.addActionListener((ActionEvent e) -> {
      dispose();
    });
    JPanel bp = new JPanel();
    bp.add(btnRegister);
    bp.add(btnCancel);

    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(bp, BorderLayout.PAGE_END);

    pack();
    setResizable(false);
    setLocationRelativeTo(parent);
  }

  public String getUsername() {
    return tfUsername.getText().trim();
  }

  public String getPassword() {
    return new String(pfPassword.getPassword());
  }

  public String getEmail() {
    return tfEmail.getText().trim();
  }

  public boolean isSucceeded() {
    return succeeded;
  }
}

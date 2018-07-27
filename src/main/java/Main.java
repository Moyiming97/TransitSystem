import dao.UserDao;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","900811");
        UserDao userDao = new UserDao(connection);
        //User user = userDao.get(1);
        User user = new User();
        user.setEmail("KenChang@gmail.com");
        user.setName("Chang Ken Jin");
        user.setAdmin(false);
        user.setPassword("12345");
        userDao.save(user);
//    System.out.println(user2.getName() + " " + user2.getPassword() + " "+user2.getEmail());

    }

//    public static void main(String[] args) {
//        final JFrame frame = new JFrame("Transit System");
//        final JButton btnLogin = new JButton("Log In");
//        final JButton btnRegister = new JButton("Sign Up");
//
//        btnLogin.addActionListener(
//                new ActionListener(){
//                    public void actionPerformed(ActionEvent e) {
//                        LoginDialog loginDlg = new LoginDialog(frame);
//                        loginDlg.setVisible(true);
//                        // if logon successfully
//                        if(loginDlg.isSucceeded()){
//                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
//                        }
//                    }
//                });
//
//        btnRegister.addActionListener(
//                new ActionListener(){
//                    public void actionPerformed(ActionEvent e) {
//                        SignUpDialog signUpDialog  = new SignUpDialog(frame);
//                        signUpDialog.setVisible(true);
//                        // if register successfully
//                        if(signUpDialog.isSucceeded()){
//                            btnRegister.setText("Hi " + signUpDialog.getUsername() + "!");
//                        }
//                    }
//                });
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 150);
//        frame.setLayout(new FlowLayout());
//        frame.getContentPane().add(btnLogin);
//        frame.getContentPane().add(btnRegister);
//        frame.setVisible(true);
//    }
}

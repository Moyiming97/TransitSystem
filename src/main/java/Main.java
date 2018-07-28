import dao.CardDao;
import dao.UserDao;
import entity.Card;
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
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","jojo1234");
        UserDao userDao = new UserDao(connection);
        //User user = userDao.get(1);

        //请求建立的 object，并非是真正存储在java内存的
        User user3 = new User();
        user3.setEmail("in@gmail.com");
        user3.setName("Chan Bao");
        user3.setAdmin(false);
        user3.setPassword("123456");
        User savedUser = userDao.save(user3);


        CardDao cardDao = new CardDao(connection);
        Card card1 = new Card();
        card1.setActive(true);
        card1.setUid(savedUser.getUid());
        cardDao.save(card1);

//        User user2 = userDao.get(2);

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
//                        view.LoginDialog loginDlg = new view.LoginDialog(frame);
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
//                        view.SignUpDialog signUpDialog  = new view.SignUpDialog(frame);
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

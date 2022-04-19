package mml.Model;

import javax.swing.*;

public class LoginPage extends JFrame {
    private JPanel mainpanel;
    private JPanel toppanel;
    private JPanel bottompanel;
    private JTextField untext;
    private JTextField pwtext;
    private JLabel usernamelabel;
    private JLabel passwordlabel;
    private JLabel toplabel;
    private JButton loginbutton;

    LoginPage(){
        super("Log In Page");
        this.setContentPane(this.mainpanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


    }





    public static void main(String[] args) {
        LoginPage screen = new LoginPage();
        screen.setVisible(true);
    }
}
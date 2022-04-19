package mml.Model;

import javax.swing.*;

public class CreateAccountPage extends JFrame {
    private JLabel calabel;
    private JTextField untext;
    private JTextField pwtext;
    private JLabel unlabel;
    private JLabel pwlabel;
    private JButton cabutton;
    private JPanel mainpanel;

    CreateAccountPage(){
        super("Create Account Page");
        this.setContentPane(this.mainpanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


    }


    public static void main(String[] args) {
        CreateAccountPage screen = new CreateAccountPage();
        screen.setVisible(true);
    }

}

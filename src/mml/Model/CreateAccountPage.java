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

    private static CreateAccountPage Instance = new CreateAccountPage();

    public static CreateAccountPage getInstance(){
        return Instance;
    }

    CreateAccountPage(){

    }

    public JPanel getGUI(){
        return mainpanel;
    }
}

package mml.Model;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateAccountPage {
    private JPanel centerPanel;
    private JTextField textField1;
    private JPasswordField passwordField;
    private JRadioButton showPasswordRadioButton;
    private JPanel createAccountPagePanel;
    private JPanel signInBarPanel;
    private JLabel signInLabel;
    //private JRadioButton confirmShowPasswordRadioButton;
    private JPasswordField confirmPasswordField;
    private JButton createAccountButton;

    private static CreateAccountPage Instance = new CreateAccountPage();

    public static CreateAccountPage getInstance(){ return Instance; }

    CreateAccountPage(){

        showPasswordRadioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (showPasswordRadioButton.isSelected()){
                    passwordField.setEchoChar((char) 0);
                    confirmPasswordField.setEchoChar((char) 0);
                }
                else {
                    passwordField.setEchoChar('*');
                    confirmPasswordField.setEchoChar('*');
                }

                super.mouseClicked(e);
            }
        });

        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                super.mouseClicked(e);
            }
        });
    }

    public JPanel getGUI(){
        return createAccountPagePanel;
    }
}

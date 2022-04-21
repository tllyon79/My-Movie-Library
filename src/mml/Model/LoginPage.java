package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage {
    private JPanel loginPagePanel;
    private JLabel signInLabel;
    private JPanel signInBarPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JRadioButton showPasswordRadioButton;
    private JButton logInButton;
    private JButton createAccountButton;
    private JPanel centerPanel;
    private JTextArea invalidEntryTextArea;

    private static LoginPage Instance = new LoginPage();

    public static LoginPage getInstance(){ return Instance; }

    LoginPage(){
        invalidEntryTextArea.setText("Invalid username or password. Please try again.");
        invalidEntryTextArea.setBorder(null);
        invalidEntryTextArea.setBackground(new Color(214,217,223));
        invalidEntryTextArea.setForeground(Color.RED);
        invalidEntryTextArea.setLineWrap(false);
        invalidEntryTextArea.setWrapStyleWord(true);
        invalidEntryTextArea.setVisible(true);
        invalidEntryTextArea.setText("");

        showPasswordRadioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (showPasswordRadioButton.isSelected()){
                    passwordField1.setEchoChar((char) 0);
                }
                else {
                    passwordField1.setEchoChar('*');
                }

                super.mouseClicked(e);
            }
        });

        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogInUser();

                super.mouseClicked(e);
            }
        });

        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigationBar.getInstance().changePage(CreateAccountPage.getInstance().getGUI());

                super.mouseClicked(e);
            }
        });
    }

    public void LogInUser(){
        LoginStatus status = AccountManager.GetInstance().AttemptLogIn(textField1.getText(),String.valueOf(passwordField1.getPassword()));
        if(status == LoginStatus.Failed_AccountExistError){
            invalidEntryTextArea.setText("No account with that username could be found.");
            return;
        }
        else if (status == LoginStatus.Failed_IncorrectPassword){
            invalidEntryTextArea.setText("The password is incorrect.");
            return;
        }
        else if(status == LoginStatus.Complete){
            //it really should be if it made it past the first two, but this is effectively a failsafe
            navigationBar.getInstance().changePage(MovieLibraryPage.getInstance().getGUI());
        }
    }

    public JPanel getGUI(){
        return loginPagePanel;
    }

}
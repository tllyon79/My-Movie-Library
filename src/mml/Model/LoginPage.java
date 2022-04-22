package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GUI Page that allows a user to log into their personal account
 */
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

    /**
     * Retrieves the instance of the singleton page
     * @return The instance of the LoginPage
     */
    public static LoginPage getInstance(){ return Instance; }

    /**
     * Default constructor for the singleton page
     */
    private LoginPage(){
        invalidEntryTextArea.setText("Invalid username or password. Please try again.");
        invalidEntryTextArea.setBorder(null);
        invalidEntryTextArea.setBackground(new Color(214,217,223));
        invalidEntryTextArea.setForeground(Color.RED);
        invalidEntryTextArea.setLineWrap(false);
        invalidEntryTextArea.setWrapStyleWord(true);
        invalidEntryTextArea.setVisible(false);
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
                if (AccountManager.GetInstance().IsUserLoggedIn()){ navigationBar.getInstance().loggedInLogo(); }

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

    /**
     * Attempts to log into an account with the current specified values within the username/password fields
     * If successful, will automatically return user to the library, logged in
     */
    public void LogInUser(){
        LoginStatus status = AccountManager.GetInstance().AttemptLogIn(textField1.getText(),String.valueOf(passwordField1.getPassword()));
        if(status == LoginStatus.Failed_AccountExistError){
            invalidEntryTextArea.setText("No account with that username could be found.");
            invalidEntryTextArea.setVisible(true);
            return;
        }
        else if (status == LoginStatus.Failed_IncorrectPassword){
            invalidEntryTextArea.setText("The password is incorrect.");
            invalidEntryTextArea.setVisible(true);
            return;
        }
        else if(status == LoginStatus.Complete){
            //it really should be if it made it past the first two, but this is effectively a failsafe
            navigationBar.getInstance().changePage(MovieLibraryPage.getInstance().getGUI());
        }
    }

    /**
     * Retrieves the GUI panel component
     * @return The root JPanel
     */
    public JPanel getGUI(){
        textField1.setText(null);
        passwordField1.setText(null);
        invalidEntryTextArea.setVisible(false);
        invalidEntryTextArea.setText("");

        return loginPagePanel;
    }

}
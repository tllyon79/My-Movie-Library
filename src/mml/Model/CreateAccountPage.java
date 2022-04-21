package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GUI Page that allows a user to create a new account
 */
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
    private JTextArea errorTextArea;

    private static CreateAccountPage Instance = new CreateAccountPage();

    /**
     * Retrieves the instance of the singleton page
     * @return The instance of the CreateAccountPage
     */
    public static CreateAccountPage getInstance(){ return Instance; }

    /**
     * Default constructor for the singleton page
     */
    private CreateAccountPage(){
        errorTextArea.setBorder(null);
        errorTextArea.setBackground(new Color(214,217,223));
        errorTextArea.setForeground(Color.RED);
        errorTextArea.setLineWrap(false);
        errorTextArea.setWrapStyleWord(true);
        errorTextArea.setVisible(true);
        errorTextArea.setText("");

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

                CreateAccount();
                super.mouseClicked(e);
            }
        });
    }

    /**
     * Attempts to create an account with the current specified values within the username/password fields
     * If successful, will automatically return user to the library, logged in
     */
    private void CreateAccount() {
        errorTextArea.setText("");
        if (!AccountManager.GetInstance().CheckValidUserPass(textField1.getText())) {
            errorTextArea.setText("Username must consist of the following:\n" +
                    "5-30 alphanumeric characters (including _)");
            return;
        }
        //we pass username check
        if (!AccountManager.GetInstance().CheckValidUserPass(String.valueOf(passwordField.getPassword()))) {
            errorTextArea.setText("Password must consist of the following:\n" +
                    "5-30 alphanumeric characters (including _)");
            return;
        }
        //valid password, check confirm
        if(!(String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword())))){
            errorTextArea.setText("Passwords do not match.");
            return;
        }
        if(!AccountManager.GetInstance().UsernameAvailable(textField1.getText())){
            errorTextArea.setText("User with the specified username already exists.");
            return;
        }
        Boolean userCreated = AccountManager.GetInstance().CreateUser(textField1.getText(), String.valueOf(passwordField.getPassword()));
        if(!userCreated){
            errorTextArea.setText("User was unable to be created.");
            return;
        }
        LoginStatus status = AccountManager.GetInstance().AttemptLogIn(textField1.getText(), String.valueOf(passwordField.getPassword()));
        if (status == LoginStatus.Complete)
            navigationBar.getInstance().changePage(MovieLibraryPage.getInstance().getGUI());
        else{
            errorTextArea.setText("Failed to log in new account.");
        }
    }

    /**
     * Retrieves the GUI panel component
     * @return The root JPanel
     */
    public JPanel getGUI(){
        return createAccountPagePanel;
    }
}

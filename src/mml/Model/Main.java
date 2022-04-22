package mml.Model;

import mml.View.MovieLibraryPage;
import mml.View.navigationBar;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *  Class that runs the Main Program
 */
public class Main {
    public static final boolean IsTestingModel = false;

    /**
     * Handles program termination
     */
    public static void OnExit(){
        AccountManager.GetInstance().OnExit();
        RatingManager.GetInstance().OnExit();
        System.exit(0);
    }

    /**
     * Main function, sets look and feel, starts GUI, and starts testing if needed
     * @param args Unused
     */
    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // No code needed, Look and Feel will revert to default
        }

        JFrame frame = new JFrame("MyMovieLibrary");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                OnExit();
                super.windowClosing(e);
            }
        });
        frame.setSize(300,300);

        navigationBar.getInstance().changePage(MovieLibraryPage.getInstance().getGUI());
        frame.setContentPane(navigationBar.getInstance().getGUI());
        frame.setVisible(true);

        if(IsTestingModel){
            ModelTesting.MainFunction();
        }
    }
}

package mml.Model;

import mml.Model.AccountManager;
import mml.Model.ModelTesting;
import mml.Model.RatingManager;
import mml.View.MovieLibraryPage;
import mml.View.navigationBar;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main {
    public static final boolean IsTestingModel = false;

    public static void OnExit(){
        AccountManager.GetInstance().OnExit();
        RatingManager.GetInstance().OnExit();
        System.exit(0);
    }

    public static void main(String[] args) {
	// write your code here
        // Changes style of UI elements to possibly look better, can keep or get rid of
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

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

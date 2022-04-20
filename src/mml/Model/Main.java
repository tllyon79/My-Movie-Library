package mml.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static final boolean IsTestingModel = false;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        navigationBar.getInstance().changePage(MovieLibraryPage.getInstance().getGUI());
        frame.setContentPane(navigationBar.getInstance().getGUI());
        frame.setVisible(true);

        System.out.println(MovieLibrary.GetInstance().getAgeRatingList());

        if(IsTestingModel){
            ModelTesting.MainFunction();
        }
    }
}

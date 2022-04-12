package mml.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


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

        System.out.println("Hello World");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
      
        Gson gson = new Gson();
        JSONData json = new JSONData("SampleMovieFile.json",true);
        ArrayList<Movie> movies = gson.fromJson(json.GetData(),new TypeToken<ArrayList<Movie>>(){}.getType());

        JFrame frame = new JFrame("MyMovieLibrary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        MoviePage movie = new MoviePage(movies.get(2));
        frame.setContentPane(movie.getGUI());
        frame.setVisible(true);
      
        if(IsTestingModel){
            ModelTesting.MainFunction();
        }
    }
}

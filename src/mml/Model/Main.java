package mml.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static final boolean IsTestingModel = true;
    public static void main(String[] args) {
	// write your code here
        // Changes style of UI elements to possibly look better, can keep or get rid of
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e){
            System.out.println("Exception Occurred");
        }

        System.out.println("Hello World");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        if(IsTestingModel){
            ModelTesting.MainFunction();
        }
    }
}

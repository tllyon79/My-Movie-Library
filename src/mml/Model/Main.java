package mml.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        Gson gson = new Gson();
        JSONData json = new JSONData("SampleMovieFile.json",true);
        ArrayList<Movie> movies = gson.fromJson(json.GetData(),new TypeToken<ArrayList<Movie>>(){}.getType());
        System.out.println(movies);
    }
}

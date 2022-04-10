package mml.Model;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MovieLibrary{
    private static MovieLibrary Instance = new MovieLibrary();
    private MovieList MasterList;

    private MovieLibrary(){
        JSONData json = new JSONData("SampleMovieFile.json",true);
        ArrayList<Movie> movies = GsonHolder.GetInstance().Gson.fromJson(json.GetData(),new TypeToken<ArrayList<Movie>>(){}.getType());
        MasterList = new MovieList(movies);
    }

    public Movie GetMovie(String movieID){
        return Search.FindMovieInList(MasterList, movieID);
    }

    public static MovieLibrary GetInstance() {
        return Instance;
    }
}
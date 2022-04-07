package mml.Model;

public class MovieLibrary{
    private static MovieLibrary Instance = new MovieLibrary();

    private MovieLibrary(){

    }

    public static MovieLibrary GetInstance() {
        return Instance;
    }
}
package mml.Model;

import java.util.ArrayList;

public class MovieList {
    //model for MovieList
    private ArrayList<String> MovieIDs;
    private transient ArrayList<Movie> Movies;
    public MovieList(){
        MovieIDs = new ArrayList<>();
        Movies = new ArrayList<Movie>();
    }

    public MovieList(ArrayList<Movie> m){
        MovieIDs = new ArrayList<String>();
        for (int i = 0; i < m.size(); i++) {
            MovieIDs.add(m.get(i).getMovieId());
        }
        Movies = m;
    }

    public ArrayList<Movie> viewMovieList()
    {
        return Movies;
    }
    public void AddMovie(Movie m){
        MovieIDs.add(m.getMovieId());
        Movies.add(m);
    }

    public void RemoveMovie(Movie m){
        MovieIDs.remove(m.getMovieId());
        Movies.remove(m);
    }

    public int getSize(){
        return Movies.size();
    }
}




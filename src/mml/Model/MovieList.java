package mml.Model;

import java.util.ArrayList;


/**
 * Data class for a specific list of Movies, that can be merged, cloned, and added to / removed from.
 */
public class MovieList {
    //model for MovieList
    private ArrayList<String> MovieIDs;
    private transient ArrayList<Movie> Movies;

    /**
     * Generic constructor for the class
     */
    public MovieList(){
        MovieIDs = new ArrayList<>();
        Movies = new ArrayList<Movie>();
    }

    /**
     * Non-generic constructor for the class
     * @param m A list of Movies to initialize the object with
     */
    public MovieList(ArrayList<Movie> m){
        MovieIDs = new ArrayList<String>();
        for (int i = 0; i < m.size(); i++) {
            MovieIDs.add(m.get(i).getMovieId());
        }
        Movies = m;
    }

    /**
     * Retrieves the List of Movies within the MovieList
     * @return The list of Movies within the MovieList
     */
    public ArrayList<Movie> viewMovieList()
    {
        return Movies;
    }

    /**
     * Adds a movie to the MovieList
     * @param m Movie to add to the MovieList
     */
    public void AddMovie(Movie m){
        MovieIDs.add(m.getMovieId());
        Movies.add(m);
    }

    /**
     * Removes a movie from the MovieList
     * @param m Movie to remove from the MovieList
     */
    public void RemoveMovie(Movie m){
        MovieIDs.remove(m.getMovieId());
        Movies.remove(m);
    }

    /**
     * Creates a deep clone of the object
     * @return The cloned object
     */
    public MovieList DeepClone(){
        ArrayList<Movie> movies = (ArrayList<Movie>) Movies.clone();
        MovieList clone = new MovieList(movies);
        return clone;
    }

    /**
     * Merges another MovieList into itself
     * @param other The MovieList to merge
     */
    public void MergeList(MovieList other){
        ArrayList<Movie> targets = other.viewMovieList();
        for(Movie m : targets){
            if(!Movies.contains(m)){
                AddMovie(m);
            }
        }
    }

    /**
     * Retrieves the size of the MovieList
     * @return The size of the MovieList()
     */
    public int getSize(){
        return Movies.size();
    }
}




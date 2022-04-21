package mml.Model;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Static class that provides functions for searching, filtering, and sorting MovieLists
 */
public class Search {
    /**
     * Searches a list for movies whose title contains a specific search parameter
     * @param m The MovieList to search through
     * @param searchParameter The String parameter to compare titles to
     * @return MovieList that contains only the movies that contain the searchParameter
     */
    public static MovieList SearchList(MovieList m, String searchParameter)
    {
        MovieList ret = new MovieList();
        ArrayList<Movie> Movies = m.viewMovieList();
        for(int i = 0; i < Movies.size(); i++){
            if(Movies.get(i).getTitle().toLowerCase().contains(searchParameter)){
                ret.AddMovie(m.viewMovieList().get(i));
            }
        }
        //now fill the list with best results from fuzzy search
        List<Pair<Movie,Double>> fuzzyResults = new ArrayList<>();
        for(int i = 0; i < Movies.size(); i++){
            if(!ret.viewMovieList().contains(Movies.get(i))){
                fuzzyResults.add(new GenericPair<>(Movies.get(i),LevenshteinDistance(Movies.get(i).getTitle(),searchParameter)));
            }
        }
        //now for fun stream parameters
        Stream<Pair<Movie,Double>> stream = fuzzyResults.stream().filter(movieDoublePair -> movieDoublePair.getValue() > 0.5d);
        fuzzyResults = stream.collect(Collectors.toList());
        //now replaced with a simple sort
        Collections.sort(fuzzyResults, new SortPairsByValue());
        for (Pair<Movie,Double> kvp : fuzzyResults){
            ret.AddMovie(kvp.getKey());
        }
        return ret;
    }

    /**
     * Searches through a given MovieList for a particular movie
     * @param m The MovieList to search through
     * @param movieId The given ID to search for
     * @return A movie which matches the given id if it exists; otherwise null
     */
    public static Movie FindMovieInList(MovieList m, String movieId){
        return m.viewMovieList().stream() //create a stream
                .filter(movie -> movieId.equals(movie.getMovieId()))//filter the stream to those that match the id
                .findAny() //find any value that is equivalent
                .orElse(null); //return null if former doesn't find anything
    }

    /**
     * Checks the list of actors in a Movie to see if a particular actor is within it
     * @param actor The actor name to search for
     * @param m The movie that should be checked
     * @return True if the actor is in the movie, false if they are not
     */
    public static Boolean CheckActorInMovie(String actor, Movie m){
        for (String a : m.getActors()) {
            if(a.toLowerCase(Locale.ROOT).contains(actor)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the list of directors in a Movie to see if a particular director is within it
     * @param director The director name to search for
     * @param m The movie that should be checked
     * @return True if the director is in the movie, false if they are not
     */
    public static Boolean CheckDirectorInMovie(String director, Movie m){
        for (String a : m.getDirector()) {
            if(a.toLowerCase(Locale.ROOT).contains(director)){
                return true;
            }
        }
        return false;
    }

    /**
     * Filters a MovieList based on given parameters
     * @param m The MovieList to filter
     * @param filterParameters
     * @return A new filtered MovieList
     */
    public static MovieList FilterList(MovieList m, List<Pair<FilterType,String>> filterParameters){
        MovieList ret = new MovieList();
        for (Pair<FilterType,String> pair: filterParameters) {
            Stream<Movie> movies = m.viewMovieList().stream();
            switch(pair.getKey()){
                case Actor -> movies = movies.filter(movie -> CheckActorInMovie(pair.getValue(),movie));
                case Genre -> movies = movies.filter(movie -> movie.getGenre().contains(pair.getValue()));
                case Director -> movies = movies.filter(movie -> CheckDirectorInMovie(pair.getValue(),movie));
                case AgeRating -> movies = movies.filter(movie -> pair.getValue().equals(movie.getAgeRating()));
            }
            ret.MergeList(new MovieList((ArrayList<Movie>) movies.collect(Collectors.toList())));
        }
        return ret;
    }

    /**
     * Sorts a MovieList based on some type of ordering
     * @param m The MovieList to sort
     * @param s The type of sorting to occur
     * @return A new sorted MovieList
     */
    public static MovieList SortList(MovieList m, SortType s){
        //do nothing until implemented
        ArrayList<Movie> movies = (ArrayList<Movie>) m.viewMovieList().clone(); //don't need a "deep clone" since the movie objects are effectively singletons without reference
        Comparator<Movie> comparator = null;
        switch(s){
            case Genre -> comparator = new SortByGenre();
            case Alphabetical -> comparator = new SortByTitle();
            case AlphabeticalInverse -> comparator = new SortByTitleInverse();
            case RatingHigh -> comparator = new SortByRatingInverse();
            case RatingLow -> comparator = new SortByRating();
            case Year -> comparator = new SortByYear();

        }
        Collections.sort(movies,comparator);
        MovieList sortedList = new MovieList(movies);
        return sortedList;
    }

    /**
     * Calculates the Levenshtein Distance between two strings, used for fuzzy search
     * @param s1 The first string
     * @param s2 The second string
     * @return The normalized Levenshtein distance between two strings (from 0 to 1)
     */
    public static double LevenshteinDistance(String s1, String s2){
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int rows = s1.length() + 1;
        int cols = s2.length() + 1;
        int[][] vals = new int[rows][cols];
        for(int i = 0; i < rows ;i++){
            for (int j = 0; j < cols; j++){
                vals[i][0] = i;
                vals[0][j] = j;
            }
        }
        for(int col = 1; col < cols; col++){
            for(int row = 1; row < rows; row++){
                int cost;
                if(s1.charAt(row-1) == s2.charAt(col-1)){
                    cost = 0;
                }
                else{
                    cost = 2;
                }
                vals[row][col] = Math.min(Math.min(vals[row-1][col]+1,vals[row][col-1]+1),vals[row-1][col-1]+cost);
            }
        }

        double num =  ((s1.length()+s2.length()) - vals[s1.length()][s2.length()]) ;
        double dem = (s1.length()+s2.length());
        double result =  num/dem;
        return result;
    }

    /**
     * Returns a MovieList comprised of movies that are "similar" (2+ shared genres) to the input movie
     * @param m The input movie
     * @return A MovieList comprised of movies that are similar to the input
     */
    public MovieList GetSimilarMovies(Movie m){
        //the definition of a "similar movie" we are using is 2+ same genre
        //first grab the master list's movies
        ArrayList<Movie> Movies = MovieLibrary.GetInstance().GetMasterList().viewMovieList();
        ArrayList<Movie> SimilarMovies = new ArrayList<>();
        for (Movie o : Movies){
            if(m.IsSimilar(o)){
                if(!m.equals(o)){
                    SimilarMovies.add(o);
                }
            }
        }
        return new MovieList(SimilarMovies);
    }
}

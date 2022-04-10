package mml.Model;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {
    public static MovieList SearchList(MovieList m, String searchParameter)
    {
        MovieList ret = new MovieList();
        ArrayList<Movie> Movies = m.viewMovieList();
        for(int i = 0; i < Movies.size(); i++){
            if(Movies.get(i).getTitle().contains(searchParameter)){
                ret.AddMovie(m.viewMovieList().get(i));
            }
        }
        return ret;
    }

    public static Movie FindMovieInList(MovieList m, String movieId){
        return m.viewMovieList().stream() //create a stream
                .filter(movie -> movieId.equals(movie.getMovieId()))//filter the stream to those that match the id
                .findAny() //find any value that is equivalent
                .orElse(null); //return null if former doesn't find anything
    }

    public static MovieList FilterList(MovieList m, FilterType t, String input){
        Stream<Movie> movies = m.viewMovieList().stream();
        switch(t){
            case Actor -> movies = movies.filter(movie -> input.equals(movie.getActors())); //this isn't really gonna work
            case Genre -> movies = movies.filter(movie -> input.equals(movie.getGenre()));
            case Director -> movies = movies.filter(movie -> input.equals(movie.getDirector()));
            case AgeRating -> movies = movies.filter(movie -> input.equals(movie.getAgeRating()));
        }
        return new MovieList((ArrayList<Movie>) movies.collect(Collectors.toList()));
    }

    public static MovieList SortList(MovieList m, SortType s){
        //do nothing until implemented
        MovieList sortedList = new MovieList();
        return sortedList;
    }
}

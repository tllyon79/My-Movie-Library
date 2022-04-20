package mml.Model;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Singleton object that holds the MasterList of Movies as well as function to retrieve any particular movie.
 */
public class MovieLibrary{
    private static MovieLibrary Instance = new MovieLibrary();
    private MovieList MasterList;
    private ArrayList<String> genreMasterList;
    private ArrayList<String> ageRatingMasterList;

    /**
     * Private constructor of the singleton object, initializing it
     */
    private MovieLibrary(){
        JSONData json = new JSONData("SampleMovieFile.json",true);
        ArrayList<Movie> movies = GsonHolder.GetInstance().Gson.fromJson(json.GetData(),new TypeToken<ArrayList<Movie>>(){}.getType());
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> genreList = new ArrayList<>();
        ArrayList<String> ageRatingList = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            if(!titles.contains(movies.get(i).getTitle())){
                titles.add(movies.get(i).getTitle());
            }
            if (!ageRatingList.contains(movies.get(i).getAgeRating())){
                ageRatingList.add(movies.get(i).getAgeRating());
            }
            for (int j = 0; j < movies.get(i).getGenre().size(); j++){
                if (!genreList.contains(movies.get(i).getGenre().get(j))){
                    genreList.add(movies.get(i).getGenre().get(j));
                }
            }
            genreMasterList = genreList;
            ageRatingMasterList = ageRatingList;
        }
        for (int i = 0; i < titles.size(); i++) {
            String current = titles.get(i);
            Stream<Movie> filterStream = movies.stream().filter(movie -> movie.getTitle().equals(current));
            List<Movie> filtered = filterStream.toList();
            if(filtered.size() > 1){
                for (Movie movie: filtered) {
                    movie.setTitle(movie.getTitle() + String.format(" (%s)", movie.getYear()));
                }
            }
        }

        for (int i = 0; i < movies.size(); i++) {
            movies.get(i).createPoster();
        }

        MasterList = new MovieList(movies);
    }

    /**
     * Retrieves a particular movie given its ID
     * @param movieID The ID of the movie
     * @return The instantiated Movie if it exists; otherwise null
     */
    public Movie GetMovie(String movieID){
        return Search.FindMovieInList(MasterList, movieID);
    }

    /**
     * Retrieves the instantiated list of genres encompassing the master list
     * @return The list of Genres
     */
    public List<String> getGenreList(){
        return genreMasterList;
    }

    /**
     * Retrieves the instantiated list of age ratings encompassing the master list
     * @return The list of Age Ratings
     */
    public List<String> getAgeRatingList(){
        return ageRatingMasterList;
    }

    /**
     * Retrieves a copy of the master list of movies
     * @return A clone of the master list of movies
     */
    public MovieList GetMasterList(){
        //we go ahead and clone here to ensure no "data loss"
        return MasterList.DeepClone();
    }

    /**
     * Retrieves the static instance of the singleton object
     * @return The instance of MovieLibrary
     */
    public static MovieLibrary GetInstance() {
        return Instance;
    }
}
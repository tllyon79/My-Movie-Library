package mml.Model;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieLibrary{
    private static MovieLibrary Instance = new MovieLibrary();
    private MovieList MasterList;

    private MovieLibrary(){
        JSONData json = new JSONData("SampleMovieFile.json",true);
        ArrayList<Movie> movies = GsonHolder.GetInstance().Gson.fromJson(json.GetData(),new TypeToken<ArrayList<Movie>>(){}.getType());
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            if(!titles.contains(movies.get(i).getTitle())){
                titles.add(movies.get(i).getTitle());
            }
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
        MasterList = new MovieList(movies);
    }

    public Movie GetMovie(String movieID){
        return Search.FindMovieInList(MasterList, movieID);
    }

    public MovieList GetMasterList(){
        //we go ahead and clone here to ensure no "data loss"
        return MasterList.DeepClone();
    }

    public static MovieLibrary GetInstance() {
        return Instance;
    }
}
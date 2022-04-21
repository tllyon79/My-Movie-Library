package mml.Model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton object that manages user ratings and retrieves them.
 */
public class RatingManager {
    private static RatingManager Instance = new RatingManager();
    private static Type DictType;
    private static JSONData JsonFile;
    private Map<String, List<String>> RatingsDict;

    /**
     * Private constructor of the singleton object, initializing it
     */
    private RatingManager(){
        DictType = new TypeToken<Map<String,List<String>>>(){}.getType();
        JsonFile = new JSONData("RatingData.json",false);
        RatingsDict = GsonHolder.GetInstance().Gson.fromJson(JsonFile.GetData(),DictType);
        if(RatingsDict == null) RatingsDict = new HashMap<>();
    }

    /**
     * Retrieves a list of MovieRatings for the given movieID
     * @param movieId The id of the Movie to retrieve ratings for
     * @return A list of MovieRatings for the given Movie
     */
    public ArrayList<MovieRating> GetRatingsByMovie(String movieId){
        if(RatingsDict.containsKey(movieId)){
            ArrayList<MovieRating> ratings = new ArrayList<>();
            List<String> users = RatingsDict.get(movieId);
            for (String user: users) {
                MovieRating rating = AccountManager.GetInstance().GetRatingFromUser(movieId,user);
                if(rating != null) ratings.add(rating);
            }
            return ratings;
        }
        else{
            return new ArrayList<>();
        }
    }

    /**
     * Update the ratings list to include a new rating
     * @param movieId The ID of the movie to add a new rating to
     * @param userId The ID of the user who created the new rating
     */
    public void UpdateRatings(String movieId, String userId){
        //pretty much all you have to do here is register with RatingManager that this user has a unique rating
        if(RatingsDict.containsKey(movieId)){
            List<String> users = RatingsDict.get(movieId);
            if(!users.contains(userId)) users.add(userId);
        }
        else{
            List<String> users = new ArrayList<>();
            users.add(userId);
            RatingsDict.putIfAbsent(movieId,users);
        }
    }

    /**
     * Exit function, to be called on program exit
     */
    public void OnExit(){
        JsonFile.WriteData(GsonHolder.GetInstance().Gson.toJson(RatingsDict));
    }

    /**
     * Retrieves the static instance of the singleton object
     * @return The instance of RatingManager
     */
    public static RatingManager GetInstance(){
        return Instance;
    }
}

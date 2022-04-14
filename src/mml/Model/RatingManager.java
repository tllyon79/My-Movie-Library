package mml.Model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingManager {
    private static RatingManager Instance = new RatingManager();
    private static Type DictType;
    private static JSONData JsonFile;
    private Map<String, List<String>> RatingsDict;
    private RatingManager(){
        DictType = new TypeToken<Map<String,List<String>>>(){}.getType();
        JsonFile = new JSONData("RatingData.json",false);
        RatingsDict = GsonHolder.GetInstance().Gson.fromJson(JsonFile.GetData(),DictType);
        if(RatingsDict == null) RatingsDict = new HashMap<>();
    }

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

    public void UpdateRatings(String movieId, String userId){
        //pretty much all you have to do here is register with RatingManager that this user has a unique rating
        if(RatingsDict.containsKey(movieId)){
            List<String> users = RatingsDict.get(movieId);
            users.add(userId);
        }
        else{
            List<String> users = new ArrayList<>();
            users.add(userId);
            RatingsDict.putIfAbsent(movieId,users);
        }
    }
    public void OnExit(){
        JsonFile.WriteData(GsonHolder.GetInstance().Gson.toJson(RatingsDict));
    }

    public static RatingManager GetInstance(){
        return Instance;
    }
}

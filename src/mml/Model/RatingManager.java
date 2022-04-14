package mml.Model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingManager {
    private static RatingManager Instance = new RatingManager();
    private static Type DictType;
    private static Type RatingType;
    private static JSONData JsonFile;
    private Map<String, List<String>> RatingsDict;
    private RatingManager(){
        DictType = new TypeToken<Map<String,String>>(){}.getType();
        RatingType = new TypeToken<UserAccount>(){}.getType();
        JsonFile = new JSONData("UserData.json",false);
        RatingsDict = GsonHolder.GetInstance().Gson.fromJson(JsonFile.GetData(),DictType);
        if(RatingsDict == null) RatingsDict = new HashMap<>();
    }

    public static RatingManager GetInstance(){
        return Instance;
    }
}

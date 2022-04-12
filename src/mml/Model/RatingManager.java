package mml.Model;

public class RatingManager {
    private static RatingManager Instance = new RatingManager();

    private RatingManager(){

    }

    public static RatingManager GetInstance(){
        return Instance;
    }
}

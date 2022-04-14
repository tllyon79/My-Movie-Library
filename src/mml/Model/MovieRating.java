package mml.Model;

import java.util.*;

public class MovieRating {
    private String MovieID;
    private String UserID;
    private Double MovieRating;
    private String MovieReview;

    public void SetRating(double input) {
        MovieRating = input;
    }

    public void SetReview(String input) {
        MovieReview = input;
    }

    public double GetRating(){
        return MovieRating;
    }

    public String GetReview(){
        return MovieReview;
    }

    public MovieRating(String movieId, String userId){
        MovieRating = 5d;
        MovieReview = "";
        MovieID = movieId;
        UserID = userId;
    }
}

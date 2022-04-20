package mml.Model;

import java.util.*;

/**
 * Data class to represent a user rating of a movie
 */
public class MovieRating {
    private String MovieID;
    private String UserID;
    private Double MovieRating;
    private String MovieReview;

    /**
     * Sets the Rating of the MovieRating, clamped between 0-10
     * @param input The numerical rating of the movie
     */
    public void SetRating(double input) {
        if(input > 10d) input = 10d;
        if(input < 0d) input = 0d;
        MovieRating = input;
    }

    /**
     * Sets the Review of the MovieRating
     * @param input The text review of the movie
     */
    public void SetReview(String input) {
        MovieReview = input;
    }

    /**
     * Retrieves the numerical rating of a MovieRating
     * @return The numerical rating of a movie
     */
    public double GetRating(){
        return MovieRating;
    }

    /**
     * Retrieves the text review of a MovieRating
     * @return The text review of a movie
     */
    public String GetReview(){
        return MovieReview;
    }

    /**
     * Retrieves the movie ID of the movie this rating is associated with
     * @return The movie ID of a Movie
     */
    public String GetMovieID(){
        return MovieID;
    }

    /**
     * Retrieves the user ID of the user this rating was created by
     * @return The user's ID
     */
    public String GetUserID(){
        return UserID;
    }

    /**
     * Non-generic constructor
     * @param movieId The ID of the movie that is being rated
     * @param userId The ID of the user that is rating the movie
     */
    public MovieRating(String movieId, String userId){
        MovieRating = 5d;
        MovieReview = "";
        MovieID = movieId;
        UserID = userId;
    }
}

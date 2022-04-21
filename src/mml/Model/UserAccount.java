package mml.Model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Data class that represents a User. Contains functions for checking and changing passwords, and accessing the wishlists and ratings of a user.
 */
public class UserAccount {
    public String UserID;
    public String Username;
    private String Password;
    public ArrayList<WishList> Wishlists; //TODO:edit to Wishlist once it is implemented
    public Map<String,MovieRating> Ratings; //TODO:edit to MovieRating once it is implemented

    /**
     * Creates a new UserAccount
     * @param username The Username of the account
     * @param password The Password of the account
     * @param userID The randomly-generated userID of the account
     * @param wishlists The wishlists of the account
     * @param ratings The ratings of the account
     */
    public UserAccount(String username, String password,String userID, ArrayList<WishList> wishlists, Map<String,MovieRating> ratings){
        Username = username;
        Password = password;
        UserID = userID;
        Wishlists = wishlists;
        Ratings = ratings;
    }

    /**
     * Checks a given input to see if it matches the user's password
     * @param input The input to compare with
     * @return True if input is equal to the user's password, false if it is not
     */
    public boolean CheckPassword(String input){
        if(input.equals(Password)){
            return true;
        }
        else return false;
    }

    /**
     * Changes a user's password
     * @param input The new password of the user
     */
    public void ChangePassword(String input){
        Password = input;
    }

    /**
     * Retrieves the user's wishlists
     * @return The user's wishlists
     */
    public ArrayList<WishList> GetWishlists(){
        return Wishlists;
    }

    public WishList findWishlist(String title) {
        WishList w = null;

        for (int i = 0; i < Wishlists.size(); i++){
            if (Wishlists.get(i).GetWishlistTitle().equals(title)){ w = Wishlists.get(i); }
        }

        return w;
    }

    /**
     * Adds a wishlist to the user's wishlists
     * @param w The Wishlist to be added
     */
     public void AddWishlist(WishList w)
     {
        Wishlists.add(w);
     }

    /**
     * Removes a wishlist from the user's wishlists
     * @param w The Wishlist to be removed
     */
     public void RemoveWishlist(WishList w)
     {
        Wishlists.remove(w);
     }

    /**
     * Retrieves the user's rating for a given movie if it exists
     * @param movieID The movie to get a rating for
     * @return The MovieRating for a given movie from the user if it exists, otherwise null
     */
     public MovieRating GetRating(String movieID) {
        if(Ratings.containsKey(movieID)) return Ratings.get(movieID);
        else return null;
     }

    /**
     * Adds a rating to the user's ratings for a given movie
     * @param rating The rating to add to the user's ratings
     * @param movieId The movie to add the rating to
     */
    public void SetRating(MovieRating rating, String movieId){
        Ratings.put(movieId,rating);
    }
}

package mml.Model;

import java.util.ArrayList;

public class UserAccount {
    private String Username;
    private String Password;
    private String UserID;
    public transient ArrayList<Object> Wishlists; //TODO:edit to Wishlist once it is implemented
    public transient ArrayList<Object> Ratings; //TODO:edit to MovieRating once it is implemented

    private UserAccount(String username, String password, String userID){
        Username = username;
        Password = password;
        UserID = userID;
        Wishlists = new ArrayList<>();
        Ratings = new ArrayList<>();
    }

    public ArrayList<Object> GetWishlists(){
        return Wishlists;
    }

    public void SaveRating(){
        //TODO:finish with rating
    }
}

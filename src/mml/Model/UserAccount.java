package mml.Model;

import java.util.ArrayList;
import java.util.Map;

public class UserAccount {
    public String UserID;
    public String Username;
    private String Password;
    public ArrayList<Object> Wishlists; //TODO:edit to Wishlist once it is implemented
    public Map<String,Object> Ratings; //TODO:edit to MovieRating once it is implemented

    public UserAccount(String username, String password,String userID, ArrayList<Object> wishlists, Map<String,Object> ratings){
        Username = username;
        Password = password;
        UserID = userID;
        Wishlists = wishlists;
        Ratings = ratings;
    }
    public boolean CheckPassword(String input){
        if(input == Password){
            return true;
        }
        else return false;
    }
    public void ChangePassword(String input){
        Password = input;
    }
    public ArrayList<Object> GetWishlists(){
        return Wishlists;
    }
    /*
     public void AddWishlist(Wishlist w)
     {
        Wishlists.add(w);
     }

     public void RemoveWishlist(Wishlist w)
     {
        Wishlists.remove(w);
     }

     public MovieRating GetRating(string movieID){

    */
    public void SaveRating(){
        //TODO:finish with rating
    }
}

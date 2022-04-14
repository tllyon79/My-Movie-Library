package mml.Model;

import java.util.ArrayList;
import java.util.Map;

public class UserAccount {
    public String UserID;
    public String Username;
    private String Password;
    public ArrayList<WishList> Wishlists; //TODO:edit to Wishlist once it is implemented
    public Map<String,MovieRating> Ratings; //TODO:edit to MovieRating once it is implemented

    public UserAccount(String username, String password,String userID, ArrayList<WishList> wishlists, Map<String,MovieRating> ratings){
        Username = username;
        Password = password;
        UserID = userID;
        Wishlists = wishlists;
        Ratings = ratings;
    }
    public boolean CheckPassword(String input){
        if(input.equals(Password)){
            return true;
        }
        else return false;
    }
    public void ChangePassword(String input){
        Password = input;
    }
    public ArrayList<WishList> GetWishlists(){
        return Wishlists;
    }

     public void AddWishlist(WishList w)
     {
        Wishlists.add(w);
     }

     public void RemoveWishlist(WishList w)
     {
        Wishlists.remove(w);
     }
    /*
     public MovieRating GetRating(String movieID) {

     }*/

    public void SaveRating(){
        //TODO:finish with rating
    }
}

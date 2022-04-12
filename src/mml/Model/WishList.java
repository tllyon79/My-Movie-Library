package mml.Model;

import mml.Model.*;

import java.util.ArrayList;
import java.util.List;

public class WishList { // model for wishlist
    private String WishlistTitle;
    private String WishlistDescription;
    private MovieList Movies;
    public WishList()
    {
        WishlistTitle = "New Wishlist";
        WishlistDescription = "";
        Movies = new MovieList();  //creates empty list that contains movie objects
    }

    public String GetWishlistTitle(){
        return WishlistTitle;
    }

    public String GetWishlistDescription(){
        return WishlistDescription;
    }

    public void SetWishlistTitle(String input){
        WishlistTitle = input;
    }

    public void SetWishlistDescription(String input){
        WishlistDescription = input;
    }

    public void AddMovie(String movieId)   {
        Movies.AddMovie(MovieLibrary.GetInstance().GetMovie(movieId));
    }


    public void RemoveMovie(Movie m)   {
        Movies.RemoveMovie(m);
    }

    public int getSize()   {  //returns size of wishlist
        return Movies.getSize();
    }


    public MovieList Search(String inputParameter)   {

        //scan in user input for keyword
        //search through wishlist for matching movie objects
        //should be able to access individual movies' elements?

        return Search.SearchList(Movies,inputParameter);

    }
    public MovieList Filter(FilterType f, String input)   {

        //scan in user input for filter
        //similar to above but different criteria
        return Search.FilterList(Movies,f,input);


    }

    public MovieList Sort(SortType s){
        return Search.SortList(Movies,s);
    }
}

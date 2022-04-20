package mml.Model;

import mml.Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class for a user's wishlist. It has a title and description alongside a list of movies, and can search/sort/filter itself.
 */
public class WishList { // model for wishlist
    private String WishlistTitle;
    private String WishlistDescription;
    private MovieList Movies;

    /**
     * Generic constructor for the class
     */
    public WishList()
    {
        WishlistTitle = "New Wishlist";
        WishlistDescription = "";
        Movies = new MovieList();  //creates empty list that contains movie objects
    }

    /**
     * Retrieves the title of the Wishlist
     * @return The Title of the Wishlist
     */
    public String GetWishlistTitle(){
        return WishlistTitle;
    }

    /**
     * Retrieves the description of the Wishlist
     * @return The Description of the Wishlist
     */
    public String GetWishlistDescription(){
        return WishlistDescription;
    }

    /**
     * Sets the title of the wishlist to the specified value
     * @param input The new title of the wishlist
     */
    public void SetWishlistTitle(String input){
        WishlistTitle = input;
    }

    /**
     * Sets the description of the wishlist to the specified value
     * @param input The new description of the wishlist
     */
    public void SetWishlistDescription(String input){
        WishlistDescription = input;
    }

    /**
     * Adds a Movie to the wishlist given its ID
     * @param movieId The ID of the Movie to add to the wishlist
     */
    public void AddMovie(String movieId)   {
        Movies.AddMovie(MovieLibrary.GetInstance().GetMovie(movieId));
    }

    /**
     * Removes a given Movie from the Wishlist
     * @param m The Movie to remove from the Wishlist
     */
    public void RemoveMovie(Movie m)   {
        Movies.RemoveMovie(m);
    }

    /**
     * Retrieves the size of the Wishlist
     * @return The size of the wishlist
     */
    public int getSize()   {  //returns size of wishlist
        return Movies.getSize();
    }

    /**
     * Searches a Wishlist for Movies with the given parameter in their titles
     * @param inputParameter The value to check for in titles
     * @return The MovieList of Movies that match the input to some degree
     */
    public MovieList Search(String inputParameter)   {

        //scan in user input for keyword
        //search through wishlist for matching movie objects
        //should be able to access individual movies' elements?

        return Search.SearchList(Movies,inputParameter);

    }

    /**
     * Filters a Wishlist based on a list of filter parameters
     * @param filterParameters A list of paired FilterTypes and Strings
     * @return The filtered MovieList
     */
    public MovieList Filter(List<Pair<FilterType,String>> filterParameters)   {

        //scan in user input for filter
        //similar to above but different criteria
        return Search.FilterList(Movies,filterParameters);


    }

    /**
     * Sorts a Wishlist based on the given SortType
     * @param s SortType that decides the type of sorting
     * @return Sorted MovieList consisting of the movies in the Wishlist
     */
    public MovieList Sort(SortType s){
        return Search.SortList(Movies,s);
    }
}

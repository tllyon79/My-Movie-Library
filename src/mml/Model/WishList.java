package mml.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WishList { // model for wishlist

    private String wlName;

    {
        //force user inputted name for each wishlist
        Scanner namer = new Scanner(System.in);
        wlName = input.nextLine();

        List<Movie> wlName = new ArrayList<Movie>(); //creates wishlist with given name

        /*
        int i=0;

        List<Movie> WishList + i = new ArrayList<Movie>();  //creates empty list that contains movie objects

        i++;  //each wishlist is named "Wishlist" i to give them a unique name, i iterates after each new wishlist is made
        */
    }

    public void AddMovie()   {

//scan in user inputted movie to add to wishlist
        Scanner adder = new Scanner(System.in);
        String movName =  input.nextLine();

        /**loop through movielist
         * for (int i=0; i<movielist; i++)
         * {
         *     if(movName == movie.getTitle(){
         *     wlName.add (movie)
         *
         *     }
         *
         * }
         *
         */

        //WishList.add(movName);
    }

    public void RemoveMovie()   {

//scan in user input to remove movie from wishlist
        Scanner remover = new Scanner(System.in);
        String movName =  input.nextLine();

        /**loop through wishlist
         * for (int i=0; i<wlName; i++)
         * {
         *     if(movName == movie.getTitle(){
         *     wlName.remove(movie);
         *
         *     }
         *
         * }
         *
         */
    }

    public int getSize()   {  //returns size of wishlist
        return wlName.size();
    }


  /*
    public List<Movie> Search()   {

//scan in user input for keyword
//search through wishlist for matching movie objects
//should be able to accsess individual movies' elements?

        List <Movie> search = new ArrayList <Movie>();

        for (loop through wishlist being searched){

            if (input == Movie.getTitle() || input == Movie.getYear() ||
                    input == Movie.getActors() || input == Movie.getDirector())
            {
                search.add (Movie);
            }

        }

        return search;

    }
*/


    /*
    public List<Movie> Filter()   {

//scan in user input for filter
//similar to above but different criteria

        List<Movie> filter = new ArrayList<Movie>();

        for (loop through selected wishlist){

            if(input == Movie.getYear() || input == Movie.getGenre){
                filter.add(Movie);
            }

        }

        return filter;

    }
*/

    public void Save()   {

//not sure on this one

    }

public List<WishList>    {

//probably need to specify Which wishlist lol
        return wlName;
    }
}

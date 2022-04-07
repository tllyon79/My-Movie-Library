public class WishList { // model for wishlist

    {

        List<Movie> WishList+i = new ArrayList<Movie>();  //creates empty list that contains movie objects

        i++;  //each wishlist is named "Wishlist" i to give them a unique name, i iterates after each new wishlist is made
    }

    public void AddMovie()   {

//scan in user inputted movie to add to wishlist

        WishList+i.add(scanned in object);

    }


    public void RemoveMovie()   {

//scan in user input to remove movie from wishlist

//peek through list to find matching input and remove? or does java know to remove classes
// by name like strings?

//WishList+i.remove (input)

    }

    public int getSize(WishList)   {  //returns size of wishlist
        wsize = WishList.size();   //sets wsize = to size of selected wishlist

        return wsize;
    }


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


    public void Save()   {

//not sure on this one

    }

public List<WishList> ()   {

//probably need to specify Which wishlist lol

        return WishList;

    }


}

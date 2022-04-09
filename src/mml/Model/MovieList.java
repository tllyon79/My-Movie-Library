package mml.Model;

public class MovieList {
    //model for MovieList

//go through json to fill master list with movies

        Gson gson = new Gson();
        JSONData json = new JSONData("SampleMovieFile.json",true);
        ArrayList<Movie> MovieList = gson.fromJson(json.GetData(),new TypeToken<ArrayList<Movie>>(){}.getType());


//should create an array list named MovieList with all movies from json if i'm understanding correctly


        public ArrayList<MovieList> viewMovieList()  {
            return MovieList;
        }

    }




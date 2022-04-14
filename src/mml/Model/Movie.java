package mml.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    private ArrayList<WebsiteRating> Ratings;
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String Type;
    private String DVD;
    private String BoxOffice;
    private String Production;
    private String Website;

    public String getTitle(){
        return Title;
    }
    public void setTitle(String input){
        Title = input;
    }
    public String getYear(){
        return Year;
    }

    public List<String> getActors(){
        if (Actors.equals("N/A")){
            return null;
        }
        return Arrays.asList(Actors.split("\\s*,\\s*"));
    }

    public List<String> getDirector(){
        if (Director.equals("N/A")){
            return null;
        }
        return Arrays.asList(Director.split("\\s*,\\s*"));
    }

    public List<String> getGenre(){
        if (Genre.equals("N/A")){
            return null;
        }
        return Arrays.asList(Genre.split("\\s*,\\s*"));
    }

    public String getPlot(){
        if (Plot.equals("N/A")){
            return "No Plot Summary Available";
        }
        return Plot;
    }

    public String getAgeRating(){
        return Rated;
    }

    public String getMovieId(){
        return imdbID;
    }

    public ArrayList<WebsiteRating> getWebsiteRatings(){
        return Ratings;
    }
}

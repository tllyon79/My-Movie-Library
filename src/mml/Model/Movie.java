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

    public String getYear(){
        return Year;
    }

    public List<String> getActors(){
        if (Actors.equals("N/A")){
            return null;
        }
        return Arrays.asList(Actors.split("\\s*,\\s*"));
    }

    public String getDirector(){
        return Director;
    }

    public String getGenre(){
        return Genre;
    }

    public String getPlot(){
        if (Plot.equals("N/A")){
            return "No Plot Summary Available";
        }
        return Plot;
    }
}

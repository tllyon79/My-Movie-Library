package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Genre;
    private String Director;
    private String Actors;
    private String Plot;
    private String Language;
    private String Poster;
    private String imdbRating;
    private String imdbID;

    transient private ImageIcon posterIcon;


    /***
     * Retrieves the movie's title
     * @return The title of the movie
     */
    public String getTitle(){
        return Title;
    }

    /***
     * Sets the title of the movie, intended to be used only by MovieLibrary
     * @param input The new title of the movie
     */
    void setTitle(String input){
        Title = input;
    }

    /***
     * Retrieves the year the movie was released
     * @return The year the movie was released
     */
    public String getYear(){
        return Year;
    }

    /***
     * Retrieves the list of actors starring within the movie
     * @return The list of actors within the movie
     */
    public List<String> getActors(){
        if (Actors.equals("N/A")){
            return new ArrayList<>();
        }
        return Arrays.asList(Actors.split("\\s*,\\s*"));
    }

    /***
     * Retrieves the list of directors who directed the movie
     * @return
     */
    public List<String> getDirector(){
        if (Director.equals("N/A")){
            return new ArrayList<>();
        }
        return Arrays.asList(Director.split("\\s*,\\s*"));
    }

    /***
     * Retrieves the list of genres of this movie
     * @return The list of genres this movie is categorized with
     */
    public List<String> getGenre(){
        if (Genre.equals("N/A")){
            return new ArrayList<>();
        }
        return Arrays.asList(Genre.split("\\s*,\\s*"));
    }

    /***
     * Retrieves the plot summary of this movie
     * @return The plot summary of this movie
     */
    public String getPlot(){
        if (Plot.equals("N/A")){
            return "No Plot Summary Available";
        }
        return Plot;
    }

    /***
     * Retrieves the age rating of the movie
     * @return The age rating of the movie
     */
    public String getAgeRating(){
        return Rated;
    }

    /***
     * Retrieves the ID string of the movie
     * @return The ID string of the movie
     */
    public String getMovieId(){
        return imdbID;
    }

    /***
     * Retrieves the poster of the movie
     * @return The poster of the movie in ImageIcon form
     */
    public ImageIcon getPoster() {
        return posterIcon;
    }

    /**
     * Retrieves the release date of the movie
     * @return The release date of the movie
     */
    public String getReleased() { return Released; }

    /**
     * Retrieves the languages the movie was released in
     * @return The list of languages the movie was released in
     */
    public String getLanguage() { return Language; }

    /**
     * Checks to see if a file exists, and creates it if it does not
     * @param Filepath The file to check existence
     * @return True if the file exists, false if the file does not exist (and could not be created)
     */
    private Boolean CheckCreateFile(String Filepath){
        try {
            File f = new File(Filepath);
            File p = f.getParentFile();
            if (p != null) p.mkdirs();
            if (!f.exists()) return f.createNewFile();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    /**
     * Populates the "posterIcon" field by either retrieving an on-disk image or downloading it
     */
    public void createPoster(){
        File posterFile = new File("src/Images/Posters/" + Title + ".jpg");
        if (posterFile.exists()){
            posterIcon = new ImageIcon(new ImageIcon(posterFile.getPath()).getImage()
                    .getScaledInstance(300, 445, Image.SCALE_DEFAULT));
        }
        else {
            try {
                String destinationFile = "src/Images/Posters/" + Title + ".jpg";
                URL url = new URL(Poster);
                InputStream is = url.openStream();
                if(CheckCreateFile(destinationFile)) {
                    OutputStream os = new FileOutputStream(destinationFile);

                    byte[] b = new byte[2048];
                    int length;

                    while ((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }

                    is.close();
                    os.close();

                    posterIcon = new ImageIcon(new ImageIcon(destinationFile).getImage()
                            .getScaledInstance(300, 445, Image.SCALE_DEFAULT));
                }
                else{
                    throw new FileNotFoundException();
                }
            }
            catch (Exception e){
                posterIcon = new ImageIcon(new ImageIcon("src/Images/defaultPoster.jpg").getImage()
                        .getScaledInstance(300, 445, Image.SCALE_DEFAULT));
            }
        }
    }

    /**
     * Retrieves the rating of the movie
     * @return The rating of the movie
     */
    public String getImdbRating(){
        return imdbRating;
    }
}

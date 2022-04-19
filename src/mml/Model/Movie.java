package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
            return null;
        }
        return Arrays.asList(Actors.split("\\s*,\\s*"));
    }

    /***
     * Retrieves the list of directors who directed the movie
     * @return
     */
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

    public ImageIcon getPoster() {
        return posterIcon;
    }

    public String getReleased() { return Released; }

    public String getLanguage() { return Language; }

    public void createPoster(){
        File posterFile = new File("src/Images/Posters/" + Title);
        if (posterFile.exists()){
            posterIcon = new ImageIcon(new ImageIcon(posterFile.getPath()).getImage()
                    .getScaledInstance(300, 445, Image.SCALE_DEFAULT));
        }
        else {
            try {
                String destinationFile = "src/Images/Posters/" + Title;
                URL url = new URL(Poster);
                InputStream is = url.openStream();
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
            catch (Exception e){
                posterIcon = new ImageIcon(new ImageIcon("src/Images/defaultPoster.jpg").getImage()
                        .getScaledInstance(300, 445, Image.SCALE_DEFAULT));
            }
        }
    }

    public String getImdbRating(){
        return imdbRating;
    }
}

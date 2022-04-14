package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class MoviePage {
    private JPanel moviePage;
    private JLabel movieTitle;
    private JLabel starIcon;
    private JLabel movieScore;
    private JLabel moviePoster;
    private JPanel movieData;
    private JLabel directorLabel;
    private JLabel releasedLabel;
    private JLabel ageRatingLabel;
    private JLabel languageLabel;
    private JScrollBar scrollBar1;
    private JLabel actorLabel;
    private JLabel plotLabel;
    private JTextArea textArea1;
    private Movie movie;

    public MoviePage(Movie movie){
        this.movieTitle.setText(movie.getTitle());
        this.movieScore.setText("5.0");
        this.directorLabel.setText(directorLabel.getText() + movie.getDirector());
        if (movie.getReleased() != "N/A"){
            this.releasedLabel.setText(releasedLabel.getText() + movie.getReleased());
        }
        else{
            this.releasedLabel.setText(releasedLabel.getText() + movie.getYear());
        }
        this.ageRatingLabel.setText(ageRatingLabel.getText() + movie.getAgeRating());
        this.languageLabel.setText(languageLabel.getText() + movie.getLanguage());
        this.actorLabel.setText(actorLabel.getText() + movie.getActors());
        this.plotLabel.setText(plotLabel.getText() + movie.getPlot());
        this.textArea1.setText(movie.getPlot());
        this.movie = movie;
    }

    public JComponent getGUI(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/star.png").getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        starIcon.setIcon(imageIcon);

        moviePoster.setIcon(movie.getPoster());
        moviePoster.setText("");

        textArea1.setEditable(false);
        textArea1.setLineWrap(true);

        return moviePage;
    }
}

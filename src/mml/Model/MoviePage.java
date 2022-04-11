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
    private String posterURL;

    public MoviePage(Movie movie){
        this.movieTitle.setText(movie.getTitle());
        this.movieScore.setText("5.0");
        this.posterURL = movie.getPoster();
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
    }

    public JComponent getGUI(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/star.png").getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        starIcon.setIcon(imageIcon);

        moviePoster.setIcon(createPoster());
        moviePoster.setText("");

        textArea1.setEditable(false);
        textArea1.setLineWrap(true);

        return moviePage;
    }

    public ImageIcon createPoster(){
        try {
            String destinationFile = "src/poster.jpg";
            URL url = new URL(posterURL);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        }
        catch (Exception e){
            System.out.println("File Exception Occurred");
        }
        ImageIcon posterIcon = new ImageIcon(new ImageIcon("src/poster.jpg").getImage()
                .getScaledInstance(300, 445, Image.SCALE_DEFAULT));
        return posterIcon;
    }
}

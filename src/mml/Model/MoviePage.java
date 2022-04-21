package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private JLabel genreLabel;
    private JPanel ratingsPanel;
    private Movie movie;

    public MoviePage(Movie movie){
        this.movieTitle.setText(movie.getTitle());
        this.movieTitle.setFont(new Font(Font.SERIF, Font.BOLD, 40));

        this.movieScore.setText(movie.getImdbRating() + "/10");
        this.movieScore.setFont(new Font(Font.SERIF, Font.BOLD, 20));

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
        this.genreLabel.setText(genreLabel.getText() + movie.getGenre());
        this.textArea1.setText(movie.getPlot());
        this.movie = movie;
    }

    public JPanel getGUI(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/star.png").getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        starIcon.setIcon(imageIcon);

        moviePoster.setIcon(new ImageIcon(movie.getPoster().getImage()
                .getScaledInstance(800, 1200, Image.SCALE_SMOOTH)));
        moviePoster.setSize(new Dimension(800, 1200));
        moviePoster.setIcon(movie.getPoster());
        moviePoster.setText("");

        textArea1.setEditable(false);
        textArea1.setLineWrap(true);

        ArrayList<MovieRating> ratings = RatingManager.GetInstance().GetRatingsByMovie(movie.getMovieId());
        ratingsPanel.removeAll();
        ratingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0,30,0,100);
        constraints.gridx = 0;
        for (int i = 0; i < ratings.size(); i++){
            constraints.gridy = i;
            ratingsPanel.add(new UserReviewGUI(ratings.get(i)).getGUI(), constraints);
        }

        return moviePage;
    }

    public static class UserReviewGUI {
        private JPanel userReviewPanel;
        private JTextArea textArea1;
        private JLabel userIDLabel;
        private JLabel userRatingStarIconLabel;
        private JLabel userRatingValueLabel;

        public UserReviewGUI(MovieRating rating){
            userIDLabel.setText(rating.GetUserID());

            ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/star.png").getImage()
                    .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            userRatingStarIconLabel.setIcon(imageIcon);
            userRatingStarIconLabel.setText(null);

            userRatingValueLabel.setText(rating.GetRating() + "/10");

            textArea1.setText(rating.GetReview());
            textArea1.setFocusable(false);
            textArea1.setBorder(null);
            textArea1.setOpaque(false);
            textArea1.setBackground(new Color(214,217,223));
        }

        public JPanel getGUI(){
            return userReviewPanel;
        }
    }
}

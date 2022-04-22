package mml.View;

import mml.Model.AccountManager;
import mml.Model.Movie;
import mml.Model.MovieRating;
import mml.Model.RatingManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MoviePage {
    private JPanel moviePage;
    private JLabel starIcon;
    private JLabel movieScore;
    private JLabel moviePoster;
    private JPanel movieData;
    private JLabel directorLabel;
    private JLabel releasedLabel;
    private JLabel ageRatingLabel;
    private JLabel languageLabel;
    private JLabel actorLabel;
    private JTextArea plotTextArea;
    private JLabel genreLabel;
    private JPanel ratingsPanel;
    private JTextArea movieTitleTextArea;
    private JLabel myReviewLabel;
    private JPanel myReviewPanel;
    private JButton reviewMovieButton;
    private Movie movie;
    private JDialog d;
    private MoviePage mP;

    public MoviePage(Movie movie){
        mP = this;
        myReviewPanel.setVisible(false);
        myReviewLabel.setVisible(false);
        reviewMovieButton.setEnabled(false);
        reviewMovieButton.setVisible(false);
        this.movieTitleTextArea.setText(movie.getTitle());
        this.movieTitleTextArea.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        movieTitleTextArea.setFocusable(false);
        movieTitleTextArea.setEditable(false);
        movieTitleTextArea.setBorder(null);
        movieTitleTextArea.setOpaque(false);
        movieTitleTextArea.setBackground(new Color(214,217,223));
        movieTitleTextArea.setLineWrap(false);
        movieTitleTextArea.setWrapStyleWord(false);

        plotTextArea.setFocusable(false);
        plotTextArea.setBorder(new LineBorder(Color.BLACK));
        plotTextArea.setOpaque(false);
        plotTextArea.setBackground(new Color(214,217,223));

        this.movieScore.setText(" " + movie.getImdbRating() + "/10");
        this.movieScore.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        if (movie.getDirector().isEmpty()){
            directorLabel.setText(directorLabel.getText() + "N/A");
        }
        else {
            this.directorLabel.setText(directorLabel.getText() + movie.getDirector().toString()
                    .substring(1, movie.getDirector().toString().length() - 1));
        }
        if (movie.getReleased() != "N/A"){
            this.releasedLabel.setText(releasedLabel.getText() + movie.getReleased());
        }
        else{
            this.releasedLabel.setText(releasedLabel.getText() + movie.getYear());
        }
        this.ageRatingLabel.setText(ageRatingLabel.getText() + movie.getAgeRating());
        this.languageLabel.setText(languageLabel.getText() + movie.getLanguage());
        if (movie.getActors().isEmpty()){
            actorLabel.setText(actorLabel.getText() + "N/A");
        }
        else {
            this.actorLabel.setText(actorLabel.getText() + movie.getActors().toString()
                    .substring(1, movie.getActors().toString().length() - 1));
        }
        if (movie.getGenre().isEmpty()){
            genreLabel.setText(genreLabel.getText() + "N/A");
        }
        else {
            this.genreLabel.setText(genreLabel.getText() + movie.getGenre().toString()
                    .substring(1, movie.getGenre().toString().length() - 1));
        }
        this.plotTextArea.setText(" " + movie.getPlot());
        this.movie = movie;
        reviewMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d = new JDialog();
                d.setTitle("Review Movie");
                d.setLocation(navigationBar.getInstance().getGUI().getWidth()/2 - 250,
                        navigationBar.getInstance().getGUI().getHeight()/2 - 175);
                d.setModal(true);
                d.add(new UserReviewGUI(movie, mP).getGUI());
                d.setSize(new Dimension(500, 350));
                d.setVisible(true);

                navigationBar.getInstance().changePage(mP.getGUI());
            }
        });
    }

    public void closeDialog(){
        d.setVisible(false);
        d.dispose();
    }

    public JScrollPane getGUI(){
        myReviewPanel.setVisible(false);
        myReviewLabel.setVisible(false);
        reviewMovieButton.setEnabled(false);
        reviewMovieButton.setVisible(false);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/star.png").getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        starIcon.setIcon(imageIcon);
        starIcon.setText(null);

        moviePoster.setIcon(new ImageIcon(movie.getPoster().getImage()
                .getScaledInstance(800, 1200, Image.SCALE_SMOOTH)));
        moviePoster.setSize(new Dimension(800, 1200));
        moviePoster.setIcon(movie.getPoster());
        moviePoster.setText("");

        plotTextArea.setEditable(false);
        plotTextArea.setLineWrap(true);

        ArrayList<MovieRating> ratings = RatingManager.GetInstance().GetRatingsByMovie(movie.getMovieId());
        ratingsPanel.removeAll();
        ratingsPanel.setLayout(new GridBagLayout());
        if (!ratings.isEmpty()) {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(12, 12, 0, 12);
            constraints.gridx = 0;
            for (int i = 0; i < ratings.size(); i++) {
                constraints.gridy = i;
                if (i == ratings.size() - 1) {
                    constraints.insets = new Insets(12, 12, 12, 12);
                }
                ratingsPanel.add(new UserReviewGUI(ratings.get(i)).getGUI(), constraints);
            }
            constraints.gridx = 1;
            constraints.weightx = 100;
            constraints.weighty = 100;
            constraints.insets.set(0, 0, 0, 0);
            ratingsPanel.add(new JLabel(), constraints);
        }
        else {
            ratingsPanel.add(new JLabel("No Reviews Available"));
        }


        myReviewPanel.removeAll();
        myReviewPanel.setLayout(new GridBagLayout());
        myReviewPanel.setBorder(new LineBorder(Color.BLACK));
        if (AccountManager.GetInstance().IsUserLoggedIn()) {
            if (AccountManager.GetCurrentUser().GetRating(movie.getMovieId()) != null) {
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(12, 12, 12, 12);
                constraints.gridx = 0;
                constraints.gridy = 0;
                myReviewPanel.add(new UserReviewGUI(AccountManager.GetCurrentUser()
                        .GetRating(movie.getMovieId())).getGUI(), constraints);
                constraints.gridx = 1;
                constraints.weightx = 100;
                constraints.weighty = 100;
                constraints.insets.set(0, 0, 0, 0);
                myReviewPanel.add(new JLabel(), constraints);

                reviewMovieButton.setText("Edit Review");
            } else {
                myReviewPanel.add(new JLabel("No Review Available"));

                reviewMovieButton.setText("Review Movie");
            }
            myReviewPanel.setVisible(true);
            myReviewLabel.setVisible(true);
            reviewMovieButton.setEnabled(true);
            reviewMovieButton.setVisible(true);
        }

        JScrollPane j = new JScrollPane();
        j.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        j.setVerticalScrollBar(new JScrollBar(Adjustable.VERTICAL));
        j.setViewportView(moviePage);

        return j;
    }

    public static class UserReviewGUI {
        private JPanel userReviewPanel;
        private JTextArea textArea1;
        private JLabel userIDLabel;
        private JLabel userRatingStarIconLabel;
        private JLabel userRatingValueLabel;
        private JTextArea enterRatingHereTextArea;
        private JButton confirmButton;
        private JLabel entryInvalidTextArea;

        public UserReviewGUI(MovieRating rating){
            userIDLabel.setText(rating.GetUserID());

            entryInvalidTextArea.setVisible(false);

            enterRatingHereTextArea.setEnabled(false);
            enterRatingHereTextArea.setVisible(false);

            confirmButton.setEnabled(false);
            confirmButton.setVisible(false);

            ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/star.png").getImage()
                    .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            userRatingStarIconLabel.setIcon(imageIcon);
            userRatingStarIconLabel.setText(null);

            userRatingValueLabel.setText(" " + rating.GetRating() + "/10");

            textArea1.setText(rating.GetReview());
            textArea1.setFocusable(false);
            textArea1.setBorder(null);
            textArea1.setOpaque(false);
            textArea1.setBackground(new Color(214,217,223));
        }

        public UserReviewGUI(Movie m, MoviePage mp){
            userIDLabel.setText(AccountManager.GetCurrentUser().UserID);

            entryInvalidTextArea.setVisible(false);

            confirmButton.setEnabled(true);
            confirmButton.setVisible(true);

            ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/star.png").getImage()
                    .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            userRatingStarIconLabel.setIcon(imageIcon);
            userRatingStarIconLabel.setText(null);

            userRatingValueLabel.setEnabled(false);
            userRatingValueLabel.setVisible(false);

            enterRatingHereTextArea.setEnabled(true);
            enterRatingHereTextArea.setText("Enter Star Rating Here");
            enterRatingHereTextArea.setVisible(true);

            textArea1.setText("Enter Review Here");
            textArea1.setFocusable(true);
            textArea1.setEditable(true);
            textArea1.setBorder(null);
            textArea1.setOpaque(false);
            textArea1.setBackground(Color.WHITE);

            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MovieRating r = new MovieRating(m.getMovieId(), AccountManager.GetCurrentUser().UserID);
                    try {
                        r.SetRating(Double.parseDouble(enterRatingHereTextArea.getText()));
                        r.SetReview(textArea1.getText());
                        AccountManager.GetCurrentUser().SetRating(r, m.getMovieId());
                        RatingManager.GetInstance().UpdateRatings(m.getMovieId(), AccountManager.GetCurrentUser().UserID);
                        mp.closeDialog();
                    }
                    catch (NumberFormatException ne){
                        entryInvalidTextArea.setVisible(true);
                    }
                }
            });
        }

        public JPanel getGUI(){
            return userReviewPanel;
        }
    }
}

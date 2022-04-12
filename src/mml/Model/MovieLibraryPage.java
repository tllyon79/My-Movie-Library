package mml.Model;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class MovieLibraryPage {
    private JPanel movieLibraryPage;
    private JButton filterButton;
    private JComboBox sortBox;
    private JPanel moviesPanel;
    private JScrollPane libraryScroll;
    private MovieList movies;

    public MovieLibraryPage(MovieList movieList){
        movies = movieList;
    }

    public JComponent getGUI(){
        int i;
        if (movies.getSize()%5 == 0){
            i = movies.getSize()/5;
        }
        else {
            i = movies.getSize()/5+1;
        }
        int j = 5;
        int movieCount = 0;

        moviesPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        libraryScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                if (movieCount < movies.getSize()) {
                    constraints.gridy = m;
                    constraints.gridx = n;
                    constraints.insets = new Insets(0,30,0,30);
                    moviesPanel.add(new miniMovies(movies.viewMovieList().get(movieCount)).getMiniMovieGUI(), constraints);
                    movieCount++;
                }
                else{
                    moviesPanel.add(new JPanel());
                }
            }
        }
        return movieLibraryPage;
    }

    public class miniMovies {
        private JPanel miniPanel;
        private JLabel miniPoster;
        private JButton wishlistButton;
        private JTextArea miniTitle;
        private JLabel miniScore;
        private JLabel miniStar;
        private Movie movie;

        public miniMovies(Movie movie){
            this.movie = movie;
        }

        public JComponent getMiniMovieGUI(){
            miniTitle.setText(movie.getTitle());
            miniTitle.setFocusable(false);
            miniTitle.setBorder(null);
            miniTitle.setOpaque(false);
            miniTitle.setBackground(new Color(214,217,223));
            miniTitle.setFont(new Font(Font.SERIF, Font.BOLD, 20));

            ImageIcon star = new ImageIcon(new ImageIcon("src/star.png").getImage()
                    .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            miniStar.setIcon(star);
            miniStar.setText("");

            miniScore.setText(" " + movie.getImdbRating());

            ImageIcon mini = new ImageIcon(movie.createPoster(movie.getPoster())
                    .getImage().getScaledInstance(200,400, Image.SCALE_SMOOTH));
            miniPoster.setIcon(mini);
            miniPoster.setText("");

            return miniPanel;
        }
    }
}

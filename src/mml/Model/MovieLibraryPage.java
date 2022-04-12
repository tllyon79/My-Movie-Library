package mml.Model;

import javax.swing.*;
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
        JPanel[][] panelArray = new JPanel[i][j];
        moviesPanel.setLayout(new GridLayout(i, j));
        libraryScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                if (movieCount < movies.getSize()) {
                    panelArray[m][n] = new JPanel();
                    moviesPanel.add(panelArray[m][n]);
                    panelArray[m][n].add(new miniMovies(movies.viewMovieList().get(movieCount)).getMiniMovieGUI());
                    movieCount++;
                }
                else{
                    panelArray[m][n] = new JPanel();
                    moviesPanel.add(panelArray[m][n]);
                }
            }
        }
        return movieLibraryPage;
    }

    public class miniMovies {
        private JPanel miniPanel;
        private JLabel miniPoster;
        private JButton wishlistButton;
        private JLabel miniTitle;
        private JLabel miniScore;
        private JLabel miniStar;
        private Movie movie;

        public miniMovies(Movie movie){
            this.movie = movie;
        }

        public JComponent getMiniMovieGUI(){
            miniTitle.setText(movie.getTitle());

            ImageIcon star = new ImageIcon(new ImageIcon("src/star.png").getImage()
                    .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            miniStar.setIcon(star);
            miniStar.setText("");

            ImageIcon mini = new ImageIcon(movie.createPoster(movie.getPoster())
                    .getImage().getScaledInstance(200,400, Image.SCALE_DEFAULT));
            miniPoster.setIcon(mini);
            miniPoster.setText("");

            return miniPanel;
        }
    }
}

package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovieLibraryPage {
    private JPanel movieLibraryPage;
    private JButton filterButton;
    private JComboBox sortBox;
    private JPanel moviesPanel;
    private JScrollPane libraryScroll;
    private JPanel paginationPanel;
    private JButton leftArrowButton;
    private JButton rightArrowButton;
    private JLabel pageLabel;
    private JPanel filterPanel;
    private JButton applyFiltersButton;
    private JTextField actorFilterTextField;
    private JTextField directorFilterTextField;
    private MovieList movies;
    private int page = 1;

    private static MovieLibraryPage Instance = new MovieLibraryPage();

    public static MovieLibraryPage getInstance(){
        return Instance;
    }

    public MovieLibraryPage(){
        movies = MovieLibrary.GetInstance().GetMasterList();

        leftArrowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                previousPage();
                super.mouseClicked(e);
            }
        });
        rightArrowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextPage();
                super.mouseClicked(e);
            }
        });

        ItemListener itemListener = event -> {
            if (event.getItem().equals("Alphabetically")){
                movies = Search.SortList(movies, SortType.Alphabetical);
            }
            if (event.getItem().equals("Rating: High to Low")){
                movies = Search.SortList(movies, SortType.RatingHigh);
            }
            if (event.getItem().equals("Rating: Low to High")){
                movies = Search.SortList(movies, SortType.RatingLow);
            }
            if (event.getItem().equals("Year")){
                movies = Search.SortList(movies, SortType.Year);
            }
            getGUI();
        };

        sortBox.addItemListener(itemListener);

        filterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filterPanel.setVisible(!filterPanel.isVisible());

                super.mouseClicked(e);
            }
        });
    }

    public JComponent changeList(MovieList newList){
        movies = newList;
        page = 1;
        return getGUI();
    }

    public void resetSortBox(){
        sortBox.setSelectedItem(sortBox.getItemAt(0));
    }

    public MovieList createPageList(){
        MovieList shortenedList = new MovieList();
        for (int i = 20 * (page - 1); i < 20 * page; i++){
            if (i < movies.getSize()) {
                shortenedList.AddMovie(movies.viewMovieList().get(i));
            }
            else break;
        }
        return shortenedList;
    }

    public void nextPage(){
        int pages;
        if (movies.getSize() % 20 != 0){
            pages = movies.getSize()/20 + 1;
        }
        else {
            pages = movies.getSize()/20;
        }
        if (page < pages){
            page++;
            getGUI();
        }
    }

    public void previousPage(){
        if (page > 1){
            page--;
            getGUI();
        }
    }

    public JPanel getGUI(){
        int i;
        MovieList visibleMovies = createPageList();
        if (visibleMovies.getSize() == 20) { i = 4; }
        else {
            if (visibleMovies.getSize() % 5 == 0) {
                i = visibleMovies.getSize() / 5;
            } else {
                i = visibleMovies.getSize() / 5 + 1;
            }
        }
        int movieCount = 0;

        moviesPanel.removeAll();
        moviesPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0,30,0,30);

        for(int m = 0; m < i; m++) {
            for(int n = 0; n < 5; n++) {
                constraints.gridx = n;
                constraints.gridy = m;
                if (movieCount < visibleMovies.getSize()) {
                    moviesPanel.add(new miniMovies(visibleMovies.viewMovieList().get(movieCount)).getMiniMovieGUI(), constraints);
                    movieCount++;
                }
                else{
                    moviesPanel.add(new JPanel(), constraints);
                }
            }
        }

        libraryScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        ImageIcon left = new ImageIcon(new ImageIcon("src/Images/Icons/leftArrowIcon.png").getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        leftArrowButton.setIcon(left);
        leftArrowButton.setText(null);

        ImageIcon right = new ImageIcon(new ImageIcon("src/Images/Icons/rightArrowIcon.png").getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        rightArrowButton.setIcon(right);
        rightArrowButton.setText(null);

        filterPanel.setVisible(false);

        int pages;
        if (movies.getSize() % 20 != 0){
            pages = movies.getSize()/20 + 1;
        }
        else {
            pages = movies.getSize()/20;
        }
        pageLabel.setText("Page " + page + "/" + pages);

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

        public miniMovies(Movie givenMovie) {
            this.movie = givenMovie;
            miniPoster.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    navigationBar.getInstance().changePage(new MoviePage(movie).getGUI());

                    super.mouseClicked(e);
                }
            });
        }

        public JComponent getMiniMovieGUI(){
            miniTitle.setText(movie.getTitle());
            miniTitle.setFocusable(false);
            miniTitle.setBorder(null);
            miniTitle.setOpaque(false);
            miniTitle.setBackground(new Color(214,217,223));
            miniTitle.setFont(new Font(Font.SERIF, Font.BOLD, 20));

            ImageIcon star = new ImageIcon(new ImageIcon("src/Images/Icons/star.png").getImage()
                    .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            miniStar.setIcon(star);
            miniStar.setText("");

            miniScore.setText(" " + movie.getImdbRating());

            ImageIcon mini = new ImageIcon(movie.getPoster()
                    .getImage().getScaledInstance(200,400, Image.SCALE_SMOOTH));
            miniPoster.setIcon(mini);
            miniPoster.setText("");

            return miniPanel;
        }
    }
}

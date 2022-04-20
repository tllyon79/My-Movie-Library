package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static mml.Model.Search.SearchList;

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
    private JPanel filterByGenrePanel;
    private JScrollPane filterByGenreScrollPane;
    private JButton removeFiltersButton;
    private JPanel filterByAgeRatingPanel;
    private JScrollPane filterByAgeRatingScrollPane;
    private MovieList movies;
    private MovieList unfilteredMovies;
    private int page = 1;
    private ArrayList<String> genreFilterCriteria;
    private ArrayList<String> ageRatingFilterCriteria;

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

        unfilteredMovies = MovieLibrary.GetInstance().GetMasterList();

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

        applyFiltersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filterMovieLibrary();
                getGUI();

                super.mouseClicked(e);
            }
        });

        filterByGenrePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        genreFilterCriteria = new ArrayList<>();
        for (int i = 0; i < MovieLibrary.GetInstance().getGenreList().size(); i++) {
            constraints.gridy = i / 4;
            JCheckBox genreCheckBox = new JCheckBox(MovieLibrary.GetInstance().getGenreList().get(i), false);
            filterByGenrePanel.add(genreCheckBox, constraints);
            if (constraints.gridx != 3) {
                constraints.gridx++;
            } else {
                constraints.gridx = 0;
            }
            genreCheckBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (genreCheckBox.isSelected()) {
                        genreFilterCriteria.add(genreCheckBox.getText());
                    } else {
                        genreFilterCriteria.remove(genreCheckBox.getText());
                    }
                }
            });
        }

        filterByAgeRatingPanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        ageRatingFilterCriteria = new ArrayList<>();
        for (int i = 0; i < MovieLibrary.GetInstance().getAgeRatingList().size(); i++) {
            constraints.gridy = i / 4;
            JCheckBox ageRatingCheckBox = new JCheckBox(MovieLibrary.GetInstance().getAgeRatingList().get(i), false);
            filterByAgeRatingPanel.add(ageRatingCheckBox, constraints);
            if (constraints.gridx != 3) {
                constraints.gridx++;
            } else {
                constraints.gridx = 0;
            }
            ageRatingCheckBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (ageRatingCheckBox.isSelected()) {
                        ageRatingFilterCriteria.add(ageRatingCheckBox.getText());
                    } else {
                        ageRatingFilterCriteria.remove(ageRatingCheckBox.getText());
                    }
                }
            });
        }

        removeFiltersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removeFilters();
                removeFiltersButton.setEnabled(false);
                if (!navigationBar.getInstance().getSearchBarContents().isEmpty()){
                    movies = SearchList(MovieLibrary.GetInstance().GetMasterList(),
                            navigationBar.getInstance().getSearchBarContents().toLowerCase());
                    resetSortBox();
                    navigationBar.getInstance().changePage(getGUI());
                }
                else { movies = MovieLibrary.GetInstance().GetMasterList(); }
                getGUI();

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

    public void setUnfilteredMovies(MovieList unfiltered){ unfilteredMovies = unfiltered; }

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

    public void removeFilters(){
        actorFilterTextField.setText(null);
        directorFilterTextField.setText(null);
        for (int i = 0; i < filterByGenrePanel.getComponentCount(); i++){
            JCheckBox genreClear = (JCheckBox) filterByGenrePanel.getComponent(i);
            genreClear.setSelected(false);
        }
        for (int i = 0; i < filterByAgeRatingPanel.getComponentCount(); i++){
            JCheckBox ageRatingClear = (JCheckBox) filterByAgeRatingPanel.getComponent(i);
            ageRatingClear.setSelected(false);
        }
    }

    public void filterMovieLibrary(){
        MovieList temp = unfilteredMovies.DeepClone();
        if (!actorFilterTextField.getText().isEmpty()){
            temp = Search.FilterList(temp, FilterType.Actor, actorFilterTextField.getText().toLowerCase());
        }
        if (!directorFilterTextField.getText().isEmpty()){
            temp = Search.FilterList(temp, FilterType.Director, directorFilterTextField.getText().toLowerCase());
        }
        if (!genreFilterCriteria.isEmpty()){
            for (int i = 0; i < genreFilterCriteria.size(); i++){
                temp = Search.FilterList(temp, FilterType.Genre, genreFilterCriteria.get(i));
            }
        }
        if (!ageRatingFilterCriteria.isEmpty()){
            for (int i = 0; i < ageRatingFilterCriteria.size(); i++){
                temp = Search.FilterList(temp, FilterType.AgeRating, ageRatingFilterCriteria.get(i));
            }
        }
        if (!actorFilterTextField.getText().isEmpty() ||
                !directorFilterTextField.getText().isEmpty() ||
                !genreFilterCriteria.isEmpty() ||
                !ageRatingFilterCriteria.isEmpty())
        {
                movies = temp;
                removeFiltersButton.setEnabled(true);
        }
        else {
            if (navigationBar.getInstance().getSearchBarContents().isEmpty()){
                movies = MovieLibrary.GetInstance().GetMasterList();
            }
            else { movies = unfilteredMovies; }
            removeFiltersButton.setEnabled(false);
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
        if (Toolkit.getDefaultToolkit().getScreenSize().getWidth() < 1536 &&
                Toolkit.getDefaultToolkit().getScreenSize().getHeight() < 864){
            libraryScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }

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

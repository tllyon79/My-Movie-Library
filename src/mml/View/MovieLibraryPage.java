package mml.View;

import mml.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static mml.Model.Search.SearchList;

/**
 * GUI Page that shows the MovieLibrary and any searched/filtered MovieLists
 */
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
    private JDialog d;
    private JDialog d2;

    private static MovieLibraryPage Instance = new MovieLibraryPage();

    /**
     * Retrieves the instance of the singleton page
     * @return The instance of the MovieLibraryPage
     */
    public static MovieLibraryPage getInstance(){
        return Instance;
    }

    /**
     * Default constructor for the singleton page
     */
    private MovieLibraryPage(){
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

            /**
             * Listens for checkboxes in genre filter field, and add them to a filter list if checked
             */
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

            /**
             * Listens for checkboxes in age rating filter field, and add them to a filter list if checked
             */
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

        /**
         * On button push, resets filled out filter fields and unfilters list
         */
        removeFiltersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
    }

    /**
     * Changes the MovieList to be shown in the MovieLibraryPage
     * @param newList new MovieList to be shown
     * @return the updated MovieLibraryPage GUI
     */
    public JComponent changeList(MovieList newList){
        movies = newList;
        page = 1;
        return getGUI();
    }

    /**
     * resets the selection of the sort box
     */
    public void resetSortBox(){
        sortBox.setSelectedItem(sortBox.getItemAt(0));
    }

    /**
     * Sets the unfiltered MovieList
     * @param unfiltered the unfiltered MovieList
     */
    public void setUnfilteredMovies(MovieList unfiltered){ unfilteredMovies = unfiltered; }

    /**
     * Handles limiting the shown MovieList to only 20 movies
     * @return limited MovieList
     */
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

    /**
     * Handles getting current page, and swapping to the next one if available
     */
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

    /**
     * Handles getting current page, and swapping to the previous one if available
     */
    public void previousPage(){
        if (page > 1){
            page--;
            getGUI();
        }
    }

    /**
     * Handles emptying the filled out filter fields
     */
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

    /**
     * Closes the inner dialog, the one for adding a new wishlist from MovieLibraryPage
     */
    public void closeDialog2(){
        d2.setVisible(false);
        d2.dispose();
    }

    /**
     * Handles filtering the movie list based on which filter fields are selected
     */
    public void filterMovieLibrary(){
        MovieList temp = unfilteredMovies.DeepClone();
        ArrayList<Pair<FilterType,String>> filterParameters = new ArrayList<>();
        if (!actorFilterTextField.getText().isEmpty()){
            filterParameters.add(new GenericPair<>(FilterType.Actor, actorFilterTextField.getText()));
            temp = Search.FilterList(temp, filterParameters);
            filterParameters.remove(0);
        }
        if (!directorFilterTextField.getText().isEmpty()){
            filterParameters.add(new GenericPair<>(FilterType.Director, directorFilterTextField.getText()));
            temp = Search.FilterList(temp, filterParameters);
            filterParameters.remove(0);
        }
        if (!genreFilterCriteria.isEmpty()){
            for (int i = 0; i < genreFilterCriteria.size(); i++){
                filterParameters.add(new GenericPair<>(FilterType.Genre, genreFilterCriteria.get(i)));
            }
            temp = Search.FilterList(temp, filterParameters);
            for (int i = 0; i < filterParameters.size(); i++){ filterParameters.remove(i); }
        }
        if (!ageRatingFilterCriteria.isEmpty()){
            for (int i = 0; i < ageRatingFilterCriteria.size(); i++){
                filterParameters.add(new GenericPair<>(FilterType.AgeRating, ageRatingFilterCriteria.get(i)));
            }
            temp = Search.FilterList(temp, filterParameters);
            for (int i = 0; i < filterParameters.size(); i++){ filterParameters.remove(i); }
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

    /**
     * Retrieves GUI panel component
     * @return the root panel
     */
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

    /**
     * GUI object that handles the creation of the small movie representation in the library
     */
    public class miniMovies {
        private JPanel miniPanel;
        private JLabel miniPoster;
        private JButton wishlistButton;
        private JTextArea miniTitle;
        private JLabel miniScore;
        private JLabel miniStar;
        private Movie movie;

        /**
         * Parameterized constructor for the object
         * @param givenMovie the movie to be shown in the mini format
         */
        public miniMovies(Movie givenMovie) {
            this.movie = givenMovie;
            miniPoster.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    navigationBar.getInstance().changePage(new MoviePage(movie).getGUI());

                    super.mouseClicked(e);
                }
            });
            wishlistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d = new JDialog();
                    d.setTitle("Add to Wishlist");
                    d.setLocation(navigationBar.getInstance().getGUI().getWidth()/2 - 250,
                            navigationBar.getInstance().getGUI().getHeight()/2 - 175);
                    d.setModal(true);
                    d.add(new MLPAddtoWishlistGUI(movie).getGUI());
                    d.setSize(new Dimension(500, 350));
                    d.setVisible(true);
                }
            });
        }

        /**
         * Retrieves the GUI panel component
         * @return The root JPanel
         */
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

            if (!AccountManager.GetInstance().IsUserLoggedIn()){
                wishlistButton.setEnabled(false);
                wishlistButton.setVisible(false);
            }
            else {
                wishlistButton.setEnabled(true);
                wishlistButton.setVisible(true);
            }

            return miniPanel;
        }
    }

    /**
     * GUI object that handles the creation of the add to wishlist GUI in MovieLibraryPage
     */
    public class MLPAddtoWishlistGUI {
        private JPanel addToWishlistPanel;
        private JButton newWishlistButton;
        private JPanel wishlistsPanel;
        private JButton confirmButton;
        private JButton cancelButton;
        private JButton refreshButton;
        private Movie wishlistMovie;

        /**
         * Parameterized constructor for the object
         * @param movie the movie to be added to selected wishlist
         */
        public MLPAddtoWishlistGUI(Movie movie){
            wishlistMovie = movie;

            /**
             * Closes the dialog when clicked
             */
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeDialog();
                }
            });

            /**
             * Button to add new wishlist from within dialog
             */
            newWishlistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d2 = new JDialog();
                    d2.setTitle("Add to Wishlist");
                    d2.setLocation(navigationBar.getInstance().getGUI().getWidth()/2 - 250,
                            navigationBar.getInstance().getGUI().getHeight()/2 - 175);
                    d2.setModal(true);
                    d2.add(new newWishlistGUI().getGUI());
                    d2.setSize(new Dimension(500, 350));
                    d2.setVisible(true);
                }
            });

            /**
             * Button that handles adding the selected movie to the selected wishlists
             */
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox temp;
                    for (int i = 0; i < wishlistsPanel.getComponentCount(); i++){
                        if (!(wishlistsPanel.getComponent(i) instanceof JLabel)) {
                            temp = (JCheckBox) wishlistsPanel.getComponent(i);
                            temp.setHorizontalTextPosition(SwingConstants.LEFT);
                            temp.setHorizontalAlignment(SwingConstants.LEFT);
                            if (temp.isSelected()) {
                                AccountManager.GetCurrentUser().GetWishlists().get(i).AddMovie(wishlistMovie.getMovieId());
                            }
                        }
                    }

                    closeDialog();
                }
            });

            /**
             * Refreshes the view to show a newly added wishlist
             */
            refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeDialog();
                    d = new JDialog();
                    d.setTitle("Add to Wishlist");
                    d.setLocation(navigationBar.getInstance().getGUI().getWidth()/2 - 250,
                            navigationBar.getInstance().getGUI().getHeight()/2 - 175);
                    d.setModal(true);
                    d.add(new MLPAddtoWishlistGUI(movie).getGUI());
                    d.setSize(new Dimension(500, 350));
                    d.setVisible(true);
                }
            });
        }

        /**
         * Retrieves the GUI panel component
         * @return The root JPanel
         */
        public JPanel getGUI(){
            wishlistsPanel.setLayout(new GridBagLayout());
            GridBagConstraints wishlistConstraints = new GridBagConstraints();
            wishlistConstraints.gridx = 0;

            ArrayList<WishList> wishlists = AccountManager.GetCurrentUser().GetWishlists();
            for (int i = 0; i < wishlists.size(); i++){
                wishlistConstraints.gridy = i;
                wishlistsPanel.add(new JCheckBox(wishlists.get(i).GetWishlistTitle()), wishlistConstraints);
            }

            wishlistConstraints.gridx = 1;
            wishlistConstraints.gridy++;
            wishlistConstraints.weightx = 1;
            wishlistConstraints.weighty = 1;
            wishlistsPanel.add(new JLabel(), wishlistConstraints);

            return addToWishlistPanel;
        }

        /**
         * Closes the main dialog for adding movie to wishlist
         */
        private void closeDialog(){
            d.setVisible(false);
            d.dispose();

            JCheckBox temp;
            for (int i = 0; i < wishlistsPanel.getComponentCount(); i++){
                if (!(wishlistsPanel.getComponent(i) instanceof JLabel)) {
                    temp = (JCheckBox) wishlistsPanel.getComponent(i);
                    temp.setSelected(false);
                }
            }
        }
    }
}

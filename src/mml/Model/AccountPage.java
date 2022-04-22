package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AccountPage {
    private JPanel accountPagePanel;
    private JPanel signInBarPanel;
    private JLabel signInLabel;
    private JPanel accountInfoPanel;
    private JLabel usernameLabel;
    private JScrollPane outerScrollPane;
    private JButton logOutButton;
    private JPanel wishlistMoviesPanel;
    private JButton newWishlistButton;
    private JDialog d;

    private static AccountPage Instance = new AccountPage();

    public static AccountPage getInstance() {
        return Instance;
    }

    private AccountPage() {
        outerScrollPane.setBorder(null);
        outerScrollPane.setViewportBorder(null);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountManager.GetInstance().LogOutUser();
                navigationBar.getInstance().changePage(MovieLibraryPage.getInstance().getGUI());
                navigationBar.getInstance().loggedInLogo();
            }
        });
        newWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d = new JDialog();
                d.setTitle("New Wishlist");
                d.setLocation(navigationBar.getInstance().getGUI().getWidth()/2 - 250,
                        navigationBar.getInstance().getGUI().getHeight()/2 - 175);
                d.setModal(true);
                d.add(new newWishlistGUI().getGUI());
                d.setSize(new Dimension(500, 350));
                d.setVisible(true);
            }
        });
    }

    public JPanel getGUI() {
        usernameLabel.setText("Username: " + AccountManager.GetCurrentUser().Username);

        wishlistMoviesPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        wishlistMoviesPanel.removeAll();
        ArrayList<WishList> wishListList = AccountManager.GetCurrentUser().Wishlists;
        constraints.insets.set(24, 12, 0, 12);
        for (int i = 0; i < wishListList.size(); i++){
            constraints.gridy = i;
            if (i == wishListList.size() - 1){ constraints.insets.set(24, 12, 24, 12); }
            wishlistMoviesPanel.add(new wishlistTemplate(AccountManager.GetCurrentUser().Wishlists.get(i)).getGUI(), constraints);
        }

        return accountPagePanel;
    }

    public static class wishlistTemplate {
        private JPanel wishlistTemplatePanel;
        private JTextArea wishlistDescriptionTextArea;
        private JPanel wishlistMoviesPanel;
        private JButton editWishlistButton;
        private JTextArea wishlistTitleTextArea;
        private WishList shownWishlist;
        private boolean editWishlist = false;

        public wishlistTemplate(WishList wishlist){
            shownWishlist = wishlist;
            wishlistTitleTextArea.setText(wishlist.GetWishlistTitle());
            wishlistTitleTextArea.setLineWrap(false);
            if (wishlist.GetWishlistDescription().isEmpty()){
                wishlistDescriptionTextArea.setText("No Description");
            }
            else {
                wishlistDescriptionTextArea.setText(wishlist.GetWishlistDescription());
            }
            wishlistDescriptionTextArea.setFocusable(false);
            wishlistDescriptionTextArea.setBorder(null);
            wishlistDescriptionTextArea.setOpaque(false);
            wishlistDescriptionTextArea.setBackground(new Color(214,217,223));
            wishlistDescriptionTextArea.setFont(new Font(Font.SERIF, Font.BOLD, 14));
            wishlistTitleTextArea.setFocusable(false);
            wishlistTitleTextArea.setBorder(null);
            wishlistTitleTextArea.setOpaque(false);
            wishlistTitleTextArea.setBackground(new Color(214,217,223));
            editWishlistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (editWishlistButton.getText().equals("Edit Wishlist")){
                        editWishlistButton.setText("Save Changes");
                        wishlistDescriptionTextArea.setBackground(Color.WHITE);
                        wishlistDescriptionTextArea.setEditable(true);
                        wishlistDescriptionTextArea.setFocusable(true);

                        wishlistTitleTextArea.setBackground(Color.WHITE);
                        wishlistTitleTextArea.setEditable(true);
                        wishlistTitleTextArea.setFocusable(true);

                        editWishlist = true;
                    }
                    else {
                        editWishlistButton.setText("Edit Wishlist");
                        wishlistDescriptionTextArea.setBackground(new Color(214,217,223));
                        wishlistDescriptionTextArea.setEditable(false);
                        wishlistDescriptionTextArea.setFocusable(false);

                        wishlistTitleTextArea.setBackground(new Color(214,217,223));
                        wishlistTitleTextArea.setEditable(false);
                        wishlistTitleTextArea.setFocusable(false);
                        editWishlist = false;

                        shownWishlist.SetWishlistDescription(wishlistDescriptionTextArea.getText());
                        shownWishlist.SetWishlistTitle(wishlistTitleTextArea.getText());
                    }
                    wishlistTemplatePanel = getGUI();
                    wishlistTemplatePanel.revalidate();
                }
            });
        }

        public JPanel getGUI(){
            wishlistMoviesPanel.removeAll();
            wishlistMoviesPanel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridy = 0;
            shownWishlist.GetMovies().viewMovieList();

            for (int i = 0; i < shownWishlist.getSize(); i++){
                constraints.gridx = i;
                wishlistMiniMovies temp = new wishlistMiniMovies(shownWishlist.GetMovies()
                        .viewMovieList().get(i), shownWishlist);
                if (editWishlist){ temp.showEditWishlist(); }
                wishlistMoviesPanel.add(temp.getWishlistMiniMovieGUI(), constraints);
            }

            return wishlistTemplatePanel;
        }
    }

    public static class wishlistMiniMovies {
        private JPanel miniPanel;
        private JLabel miniPoster;
        private JButton removeMovieButton;
        private JTextArea miniTitle;
        private Movie movie;
        private WishList miniWishlist;
        private boolean showEditWishlist = false;

        public wishlistMiniMovies(Movie givenMovie, WishList wishList) {
            movie = givenMovie;
            miniWishlist = wishList;
            miniPoster.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    navigationBar.getInstance().changePage(new MoviePage(movie).getGUI());

                    super.mouseClicked(e);
                }
            });
            removeMovieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeMovieButton.setText("Removed");
                    removeMovieButton.setEnabled(false);
                    miniWishlist.RemoveMovie(movie);
                }
            });
        }

        public void showEditWishlist(){
            showEditWishlist = true;
        }

        public JComponent getWishlistMiniMovieGUI(){
            if (showEditWishlist){
                removeMovieButton.setEnabled(true);
                removeMovieButton.setVisible(true);
            }
            else {
                removeMovieButton.setEnabled(false);
                removeMovieButton.setVisible(false);
            }

            miniTitle.setText(movie.getTitle());
            miniTitle.setFocusable(false);
            miniTitle.setBorder(null);
            miniTitle.setOpaque(false);
            miniTitle.setBackground(new Color(214,217,223));
            miniTitle.setFont(new Font(Font.SERIF, Font.BOLD, 20));

            ImageIcon mini = new ImageIcon(movie.getPoster()
                    .getImage().getScaledInstance(150,200, Image.SCALE_SMOOTH));
            miniPoster.setIcon(mini);
            miniPoster.setText("");

            return miniPanel;
        }
    }
}




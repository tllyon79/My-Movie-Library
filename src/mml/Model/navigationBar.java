package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static mml.Model.Search.SearchList;

public class navigationBar {
    private JPanel navigationBarPanel;
    private JLabel mmlLabel;
    private JLabel searchIconLabel;
    private JTextField searchBarTextField;
    private JLabel accountIconLabel;
    private JPanel pagePanel;

    private static navigationBar Instance = new navigationBar();

    public static navigationBar getInstance(){
        return Instance;
    }

    private navigationBar(){
        mmlLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePage(MovieLibraryPage.getInstance().getGUI());

                super.mouseClicked(e);
            }
        });
        searchBarTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (searchBarTextField.hasFocus() && e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (!searchBarTextField.getText().isEmpty()){
                        MovieList searchResults = SearchList(MovieLibrary.GetInstance().GetMasterList(),
                                searchBarTextField.getText().toLowerCase());
                        MovieLibraryPage.getInstance().changeList(searchResults);
                        MovieLibraryPage.getInstance().setUnfilteredMovies(searchResults);
                        MovieLibraryPage.getInstance().resetSortBox();
                        MovieLibraryPage.getInstance().filterMovieLibrary();
                        changePage(MovieLibraryPage.getInstance().getGUI());
                    }
                    else {
                        MovieLibraryPage.getInstance().changeList(MovieLibrary.GetInstance().GetMasterList());
                        MovieLibraryPage.getInstance().setUnfilteredMovies(MovieLibrary.GetInstance().GetMasterList());
                        MovieLibraryPage.getInstance().resetSortBox();
                        MovieLibraryPage.getInstance().filterMovieLibrary();
                        changePage(MovieLibraryPage.getInstance().getGUI());
                    }
                }
                super.keyPressed(e);
            }
        });
        accountIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (AccountManager.GetCurrentUser() != null){
                    changePage(AccountPage.getInstance().getGUI());
                }
                else{
                    changePage(LoginPage.getInstance().getGUI());
                }

                super.mouseClicked(e);
            }
        });
    }

    public String getSearchBarContents(){
        return searchBarTextField.getText();
    }

    public JComponent getGUI(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/searchIcon.png").getImage()
                .getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        searchIconLabel.setIcon(imageIcon);
        searchIconLabel.setText(null);

        loggedInLogo();

        ImageIcon logo = new ImageIcon(new ImageIcon("src/Images/Icons/MML_Logo.png").getImage()
                .getScaledInstance(60, 30, Image.SCALE_SMOOTH));
        mmlLabel.setIcon(logo);
        mmlLabel.setText(null);

        return navigationBarPanel;
    }

    public void loggedInLogo(){
        if (AccountManager.GetCurrentUser() != null){
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/accountIcon.png").getImage()
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            accountIconLabel.setIcon(imageIcon);
            accountIconLabel.setText(null);
        }
        else{
            accountIconLabel.setText("Sign In");
            accountIconLabel.setFont(new Font(Font.SERIF, Font.BOLD, 14));
            accountIconLabel.setIcon(null);
        }
    }

    public void changePage(JPanel page){
        try{
            pagePanel.remove(0);
        }
        catch (Exception e){

        }
        page.revalidate();
        page.repaint();
        pagePanel.add(page, 0);
        navigationBarPanel.revalidate();
        navigationBarPanel.repaint();
    }

    public void changePage(JScrollPane page){
        try{
            pagePanel.remove(0);
        }
        catch (Exception e){

        }
        page.revalidate();
        page.repaint();
        pagePanel.add(page, 0);
        navigationBarPanel.revalidate();
        navigationBarPanel.repaint();
    }
}

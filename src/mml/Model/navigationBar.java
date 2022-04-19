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
                        MovieLibraryPage.getInstance().resetSortBox();
                        changePage(MovieLibraryPage.getInstance().getGUI());
                    }
                    else {
                        MovieLibraryPage.getInstance().changeList(MovieLibrary.GetInstance().GetMasterList());
                        MovieLibraryPage.getInstance().resetSortBox();
                        changePage(MovieLibraryPage.getInstance().getGUI());
                    }
                }
                super.keyPressed(e);
            }
        });
        accountIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePage(AccountPage.getInstance().getGui());

                super.mouseClicked(e);
            }
        });
    }

    public JComponent getGUI(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/searchIcon.png").getImage()
                .getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        searchIconLabel.setIcon(imageIcon);
        searchIconLabel.setText(null);

        imageIcon = new ImageIcon(new ImageIcon("src/Images/Icons/accountIcon.png").getImage()
                .getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        accountIconLabel.setIcon(imageIcon);
        accountIconLabel.setText(null);

        return navigationBarPanel;
    }

    public JPanel changePage(JPanel page){
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

        return navigationBarPanel;
    }
}

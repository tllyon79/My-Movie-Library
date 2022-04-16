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
        //this.pagePanel.add(page);
        mmlLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePage(new MovieLibraryPage().getGUI());

                super.mouseClicked(e);
            }
        });
        searchBarTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (searchBarTextField.hasFocus() && e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (!searchBarTextField.getText().isEmpty()){
                        MovieList searchResults = SearchList(MovieLibrary.GetInstance().GetMasterList(), searchBarTextField.getText());
                        MovieLibraryPage.getInstance().changeList(searchResults);
                        changePage(MovieLibraryPage.getInstance().getGUI());
                    }
                }
                super.keyPressed(e);
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

    public JComponent changePage(JComponent page){
        try {
            this.pagePanel.remove(0);
        }
        catch (ArrayIndexOutOfBoundsException e){

        }
        this.pagePanel.add(page);

        return navigationBarPanel;
    }
}

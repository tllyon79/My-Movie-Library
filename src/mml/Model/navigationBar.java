package mml.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;import java.awt.event.ContainerAdapter;

public class navigationBar {
    private JPanel navigationBarPanel;
    private JLabel mmlLabel;
    private JLabel searchIconLabel;
    private JTextField searchBarTextField;
    private JLabel accountIconLabel;
    private JPanel pagePanel;

    public navigationBar(){
        //this.pagePanel.add(page);
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

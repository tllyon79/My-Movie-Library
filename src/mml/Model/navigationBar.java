package mml.Model;

import javax.swing.*;
import java.awt.*;

public class navigationBar {
    private JPanel navigationBarPanel;
    private JLabel mmlLabel;
    private JLabel searchIconLabel;
    private JTextField searchBarTextField;
    private JLabel accountIconLabel;
    private JPanel pagePanel;

    public navigationBar(JComponent page){
        this.pagePanel.add(page);
    }

    public JComponent getGUI(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/searchIcon.png").getImage()
                .getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        searchIconLabel.setIcon(imageIcon);
        searchIconLabel.setText(null);

        return navigationBarPanel;
    }

    public JComponent changeGUI(JComponent page){
        this.pagePanel.remove(0);
        this.pagePanel.add(page);

        return navigationBarPanel;
    }
}

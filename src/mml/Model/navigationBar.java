package mml.Model;

import javax.swing.*;

public class navigationBar {
    private JPanel navigationBarPanel;
    private JLabel mmlLabel;
    private JLabel searchIconLabel;
    private JTextField textField1;
    private JLabel accountIconLabel;
    private JPanel pagePanel;

    public navigationBar(JComponent page){
        this.pagePanel.add(page);
    }

    public JComponent getGUI(){
        return navigationBarPanel;
    }
}

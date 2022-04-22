package mml.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newWishlistGUI {
    private JPanel newWishlistPanel;
    private JTextArea wTitleTextArea;
    private JTextArea wDescriptionTextArea;
    private JButton createWishlistButton;

    public newWishlistGUI(){
        createWishlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WishList w = new WishList();

                if (!wTitleTextArea.getText().isEmpty()){
                    w.SetWishlistTitle(wTitleTextArea.getText());
                }

                if (!wDescriptionTextArea.getText().isEmpty()){
                    w.SetWishlistTitle(wTitleTextArea.getText());
                }
                else {
                    w.SetWishlistDescription(null);
                }

                AccountManager.GetCurrentUser().AddWishlist(w);
            }
        });
    }

    public JPanel getGUI(){
        return newWishlistPanel;
    }
}

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
                    w.SetWishlistDescription("");
                }

                AccountManager.GetCurrentUser().AddWishlist(w);

                try {
                    AccountPage.getInstance().closeDialog();
                    navigationBar.getInstance().changePage(AccountPage.getInstance().getGUI());
                }
                catch (NullPointerException a){
                    // Do nothing
                }

                try {
                    MovieLibraryPage.getInstance().closeDialog2();
                }
                catch (NullPointerException a){
                    // Do nothing
                }
            }
        });
    }

    public JPanel getGUI(){
        return newWishlistPanel;
    }
}

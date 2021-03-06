package mml.View;

import mml.Model.AccountManager;
import mml.Model.WishList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI Page that handles the new wishlist popup GUI
 */
public class newWishlistGUI {
    private JPanel newWishlistPanel;
    private JTextArea wTitleTextArea;
    private JTextArea wDescriptionTextArea;
    private JButton createWishlistButton;

    /**
     * Default constructor for the object
     */
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

    /**
     * Retrieves GUI panel component
     * @return the root panel
     */
    public JPanel getGUI(){
        return newWishlistPanel;
    }
}

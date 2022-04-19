package mml.Model;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountPage extends JFrame {
    private JPanel toppanel;
    private JPanel leftpanel;
    private JLabel toplabel;
    private JLabel leftlabel;
    private JPanel rightpanel;
    private JLabel rightlabel;
    private JList wlist;
    private JLabel movielabel;
    private JList mlist;
    private JLabel wlistlabel;
    private JButton deletebutton;
    private JButton addwlbutton;
    private JButton deletewlbutton;
    private JPanel mainpanel;
    private JLabel createlabel;
    private JTextField nametext;
    private ArrayList<WishList> userList;
    private DefaultListModel wlistmodel;
    private DefaultListModel mlistmodel;
    private UserAccount user;

    AccountPage(){
        super("Account Page");
        this.setContentPane(this.mainpanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        userList = new ArrayList<WishList>();

        wlistmodel = new DefaultListModel<>();
        wlist.setModel(wlistmodel);

        //not sure of the best way to access the movies in the wishlists
        mlistmodel = new DefaultListModel<>();
        mlist.setModel(mlistmodel);


       //shows wlist contents when selected hopefully
        wlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int wishlistnum = wlist.getSelectedIndex();

                if(wishlistnum >= 0){
                     WishList w = userList.get(wishlistnum);

                     //mlistmodel.addElement(w.viewMovieList());

                    // i would like to populate the movies jlist with the individual movie contents of
                    //the selected wishlist but i'm not sure uh... how
                }

                //button should delete wishlist currently selected on list
                //and it works!
                deletewlbutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(wishlistnum>=0){
                            WishList w = userList.get(wishlistnum);
                            deletewishlist(w);
                        }
                    }
                });

            }
        });

        //button creates wishlist with given title and adds it to user list
        // and baby it works
        addwlbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String wlname = nametext.getText().trim();
                WishList created = new WishList();
                created.SetWishlistTitle(wlname);
                adduserList(created);
            }
        });


    }

    //precious methods

    //refreshes the model to display the current userlist
    public void refreshuserList(){
        wlistmodel.removeAllElements();
        System.out.println("clearing list");

        for(WishList w : userList){
            System.out.println("adding wishlist " + w.GetWishlistTitle());
            wlistmodel.addElement(w.GetWishlistTitle());
        }
    }

    //deletes from user list and then refreshes
    public void deletewishlist(WishList w){
        userList.remove(w);
        refreshuserList();
    }

    // adds to user list and then refreshes
    public void adduserList(WishList w){
        userList.add(w);
        refreshuserList();
    }

    //add specific users wishlists to userList?



    public static void main(String[] args) {
        AccountPage screen = new AccountPage();
        screen.setVisible(true);

        WishList test = new WishList();
        WishList test2 = new WishList();

        screen.adduserList(test);
        screen.adduserList(test2);

    }

}

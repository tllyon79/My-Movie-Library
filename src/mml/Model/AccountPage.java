package mml.Model;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountPage extends JFrame {
    private JPanel mainpanel;
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
    private JLabel createlabel;
    private JTextField nametext;
    private ArrayList<WishList> userList;
    private DefaultListModel wlistmodel;
    private DefaultListModel mlistmodel;
    private UserAccount user;

    private static AccountPage Instance = new AccountPage();

    public static AccountPage getInstance() {
        return Instance;
    }

    AccountPage() {


        super("Account Page");
        this.setContentPane(this.mainpanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        //returns current user and adds their wishlists to model
        user = AccountManager.GetCurrentUser();
        if (user != null && user.GetWishlists() != null) {
            userList = user.GetWishlists();
        } else {
            userList = new ArrayList<WishList>();
        }


        wlistmodel = new DefaultListModel<>();
        wlist.setModel(wlistmodel);

        //not sure of the best way to access the movies in the wishlists
        // xx created getter hopefully works
        mlistmodel = new DefaultListModel<>();
        mlist.setModel(mlistmodel);


        //shows wlist contents when selected hopefully
        wlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int wishlistnum = wlist.getSelectedIndex();

                if (wishlistnum >= 0) {
                    mlistmodel.removeAllElements(); //clear list with each click (so they don't pile up eternally)
                    WishList w = userList.get(wishlistnum);

                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!may need work!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
                    mlistmodel.addElement(w.GetMovies().viewMovieList());  //hopefully shows indv movies

                    //populates side list with contents of selected wishlist
                }


                //button should delete wishlist currently selected on list
                //and it works!
                deletewlbutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (wishlistnum >= 0) {
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





        //precious methods :: wish list related

        //refreshes the model to display the current userlist
        public void refreshuserList () {
            wlistmodel.removeAllElements();
            System.out.println("clearing list");

            for (WishList w : userList) {
                System.out.println("adding wishlist " + w.GetWishlistTitle());
                wlistmodel.addElement(w.GetWishlistTitle());
                getGui();
            }
        }

        //deletes from user list and then refreshes
        public void deletewishlist (WishList w){
            userList.remove(w);
            if (user != null) {
                user.RemoveWishlist(w);    //permanent delete from users wishlists
            }
            refreshuserList();
            getGui();
        }

        // adds to user list and then refreshes
        public void adduserList (WishList w){
            userList.add(w);
            if (user != null) {
                user.AddWishlist(w);  //permanent add to users wishlists
            }
            refreshuserList();
            getGui();
        }

        //precious methods :: wishlist content related
/*
    public void deletemovie(Movie m){
        //i foresee problems here, because i believe im returning entire movielists into the right panel

        WishList w = userList.get(wishlistnum);
        w.GetMovies().viewMovieList();


    }

 */



    public JPanel getGui(){
        return mainpanel;
    }


/*main for testing account page
    public static void main(String[] args) {
        AccountPage screen = new AccountPage();
        screen.setVisible(true);

        WishList test = new WishList();
        WishList test2 = new WishList();

        test.SetWishlistTitle("test 1");
        test2.SetWishlistTitle("test 2");
        test.SetWishlistDescription("this is a public broadcasting test");
        test2.SetWishlistDescription("national emergency");



        //test.AddMovie("tt4154796");
        //test2.AddMovie("10");        breaks it lol

        screen.adduserList(test);
        screen.adduserList(test2);

        screen.getInstance();
    }

 */

}

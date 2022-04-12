package mml.Model;

public class ModelTesting {
    public static void MainFunction(){
        MovieList MasterList = MovieLibrary.GetInstance().GetMasterList();
        for (int i = 0; i < MasterList.getSize(); i++) {
            System.out.println(MasterList.viewMovieList().get(i).getTitle());
        }
        LoginStatus LoginAttempt = AccountManager.GetInstance().AttemptLogIn("user","pass");
        while(LoginAttempt != LoginStatus.Complete){
            if(LoginAttempt == LoginStatus.Failed_AccountExistError) {
                AccountManager.GetInstance().CreateUser("user", "pass");
                LoginAttempt = AccountManager.GetInstance().AttemptLogIn("user","pass");
            }
            else if (LoginAttempt == LoginStatus.Failed_IncorrectPassword){
                break;
            }
        }
        if(LoginAttempt == LoginStatus.Complete){
            //attempt creation of a new wishlist and add it to the user
            WishList list = new WishList();
            list.SetWishlistTitle("TestWishlist");
            list.SetWishlistDescription("This is a test wishlist, and I am a test description.");
            //now let's add some movies, testing MovieLibrary in the process
            list.AddMovie("tt0100669");
            list.AddMovie("tt1905041");
            list.AddMovie("tt0232500");
            AccountManager.GetCurrentUser().AddWishlist(list);
        }
        System.out.println(AccountManager.GetCurrentUser().Username);
        System.out.println(AccountManager.GetCurrentUser().UserID);
        AccountManager.GetInstance().OnExit();
    }
}

package mml.Model;

public class ModelTesting {
    /**
     * A test function for the Model package
     */
    public static void MainFunction(){
        MovieList MasterList = MovieLibrary.GetInstance().GetMasterList();
        for (int i = 0; i < MasterList.getSize(); i++) {
            System.out.println(MasterList.viewMovieList().get(i).getTitle());
        }
        LoginStatus LoginAttempt = AccountManager.GetInstance().AttemptLogIn("user","pass");
        while(LoginAttempt != LoginStatus.Complete) {
            if (LoginAttempt == LoginStatus.Failed_AccountExistError) {
                AccountManager.GetInstance().CreateUser("user", "pass");
                LoginAttempt = AccountManager.GetInstance().AttemptLogIn("user", "pass");
            } else if (LoginAttempt == LoginStatus.Failed_IncorrectPassword) {
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
            //add some ratings to these movies
            MovieRating rating = new MovieRating("tt0100669",AccountManager.GetCurrentUser().UserID);
            rating.SetRating(5);
            rating.SetReview("This is a good movie.");
            AccountManager.GetCurrentUser().SetRating(rating,"tt0100669");
            RatingManager.GetInstance().UpdateRatings("tt0100669",AccountManager.GetCurrentUser().UserID);
        }
        System.out.println(AccountManager.GetCurrentUser().Username);
        System.out.println(AccountManager.GetCurrentUser().UserID);
        for (MovieRating rating: RatingManager.GetInstance().GetRatingsByMovie("tt0100669")) {
            System.out.println(rating.GetRating());
            System.out.println(rating.GetReview());
        }
        AccountManager.GetInstance().OnExit();
        RatingManager.GetInstance().OnExit();
    }
}

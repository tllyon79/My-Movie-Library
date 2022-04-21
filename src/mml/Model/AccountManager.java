package mml.Model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Singleton manager for UserAccounts. Handles logins and user data.
 */
public class AccountManager {
    private static AccountManager Instance = new AccountManager();
    private static Type DictType;
    private static Type UserType;
    private JSONData JsonFile;
    private Map<String,String> UserIDs;
    private Random rng;
    private final String IDChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String UserPassRegex = "^[A-Za-z]\\w{4,29}$";
    private static UserAccount CurrentUser = null;

    /**
     * Private constructor of the singleton object, initializing it
     */
    private AccountManager(){
        DictType = new TypeToken<Map<String,String>>(){}.getType();
        UserType = new TypeToken<UserAccount>(){}.getType();
        JsonFile = new JSONData("UserData.json",false);
        UserIDs = GsonHolder.GetInstance().Gson.fromJson(JsonFile.GetData(),DictType);
        if(UserIDs == null) UserIDs = new HashMap<>();
        rng = new Random();
    }

    /**
     * Retrieves the static instance of the singleton object
     * @return The instance of AccountManager
     */
    public static AccountManager GetInstance() {
        return Instance;
    }

    /**
     * Retrieves the currently logged-in user
     * @return
     */
    public static UserAccount GetCurrentUser(){
        return CurrentUser;
    }

    /**
     * Check if the inputted string is a valid username/password
     * @param input The input string to test
     * @return False if the input is null or otherwise does not match criteria, True otherwise
     */
    public boolean CheckValidUserPass(String input){
        //check for null
        if(input == null) return false;
        if(input.equals("")) return false;
        //this is honestly a really nice Regex, gotten online
        Pattern p = Pattern.compile(UserPassRegex);
        if(p.matcher(input).matches()) return true;
        else return false;
    }

    /**
     * Generates a unique user ID for user creation
     * @return A 9-character unique user ID
     */
    private String GenerateUniqueUserID(){
        String out = "";
        for(int i = 0; i < 9; i++){
            out += IDChars.charAt(rng.nextInt(IDChars.length()));
        }
        if(UserIDs.values().contains(out)){
            return GenerateUniqueUserID();
        }
        else{
            return out;
        }
    }

    /**
     * Logs in a user, deserializing their data and setting them as the value of CurrentUser
     * @param user
     */
    private void LogInUser(String user){
        JSONData userData = new JSONData(new StringBuilder("Users/").append(user).append(".json").toString(),false);
        UserAccount userA = GsonHolder.GetInstance().Gson.fromJson(userData.GetData(),UserType);
        if(userA != null){
            CurrentUser = userA;
        }
    }

    /**
     * Logs out a user, serializing their data and setting CurrentUser to null
     */
    private void LogOutUser(){
        if(CurrentUser != null) {
            UserAccount user = CurrentUser;
            CurrentUser = null;
            JSONData userData = new JSONData(new StringBuilder("Users/").append(user.UserID).append(".json").toString(), false);
            userData.WriteData(GsonHolder.GetInstance().Gson.toJson(user));
        }
    }

    /**
     * Attempts to log in a user,
     * @param username
     * @param password
     * @return LoginStatus.Complete if the login is successful;\n LoginStatus.Failed_AccountExistError if the account does not exist;\n LoginStatus.Failed_IncorrectPassword if the password is incorrect
     */
    public LoginStatus AttemptLogIn(String username, String password){
        //first "log in" and confirm login based on user account
        String id = UserIDs.getOrDefault(username,"");
        if(id == "") return LoginStatus.Failed_AccountExistError;
        LogInUser(id);
        if(CurrentUser == null) return LoginStatus.Failed_AccountExistError;
        //check password
        if(!(CurrentUser.CheckPassword(password))) { LogOutUser(); return LoginStatus.Failed_IncorrectPassword;}
        return LoginStatus.Complete;
    }

    /**
     * Attempts to create an account with the provided username and password
     * @param username The username to create the account with
     * @param password The password to create the account with
     * @return True if the account was created, false if it was not (by an error or if the account already exists)
     */
    public boolean CreateUser(String username, String password){
        try {
            if(UserIDs.containsKey(username)) return false;
            UserAccount user = new UserAccount(username, password, GenerateUniqueUserID(), new ArrayList<>(), new HashMap<>());
            UserIDs.putIfAbsent(user.Username,user.UserID);
            JSONData userData = new JSONData(new StringBuilder("Users/").append(user.UserID).append(".json").toString(), false);
            userData.WriteData(GsonHolder.GetInstance().Gson.toJson(user));
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    /**
     * Gets a MovieRating of a specified movie from a specified user if it exists
     * @param movieId The ID of the movie to check for reviews
     * @param userId The ID of the user to check for reviews
     * @return MovieRating from the user if it exists; otherwise null
     */
    public MovieRating GetRatingFromUser(String movieId, String userId){
        JSONData userData = new JSONData(new StringBuilder("Users/").append(userId).append(".json").toString(),false);
        UserAccount userA = GsonHolder.GetInstance().Gson.fromJson(userData.GetData(),UserType);
        if(userA != null){
            MovieRating rating = userA.GetRating(movieId);
            return rating; //can return a valid MovieRating or null
        }
        else return null;
    }

    /**
     * Exit function, to be called on program exit
     */
    public void OnExit(){
        LogOutUser();
        SaveToJson();
    }

    /**
     * Write UserIDs to file
     */
    private void SaveToJson(){
        JsonFile.WriteData(GsonHolder.GetInstance().Gson.toJson(UserIDs));
    }

    /**
     * Checks to see if a user is logged in
     * @return True if a user is logged in, otherwise false
     */
    public Boolean IsUserLoggedIn(){
        return (CurrentUser != null);
    }

    /**
     * Checks to see if the given username is already in use
     * @param username The username to look for
     * @return True if the username is not in use, otherwise false
     */
    public Boolean UsernameAvailable(String username){
        return !UserIDs.containsKey(username);
    }
}

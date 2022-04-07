package mml.Model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Pattern;

public class AccountManager {
    public static AccountManager Instance = new AccountManager();
    private static Type DictType = new TypeToken<Map<String,String>>(){}.getType();
    private static Type UserType = new TypeToken<UserAccount>(){}.getType();
    private JSONData JsonFile;
    private Map<String,String> UserIDs;
    private Random rng;
    private final String IDChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String UserPassRegex = "^[A-Za-z]\\w{4,29}$";
    private static UserAccount CurrentUser = null;

    private AccountManager(){
        JsonFile = new JSONData("UserData.json",false);
        UserIDs = GsonHolder.Instance.Gson.fromJson(JsonFile.GetData(),DictType);
        rng = new Random();
    }

    public static UserAccount GetCurrentUser(){
        return CurrentUser;
    }
    public boolean CheckValidUsername(String username){
        //check for null
        if(username == null) return false;
        //this is honestly a really nice Regex, gotten online
        Pattern p = Pattern.compile(UserPassRegex);
        if(p.matcher(username).matches()) return true;
        else return false;
    }
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
    public void LogInUser(String user){
        JSONData userData = new JSONData(new StringBuilder("Users/").append(user).append(".json").toString(),false);
        UserAccount userA = GsonHolder.Instance.Gson.fromJson(userData.GetData(),UserType);
        if(userA != null){
            CurrentUser = userA;
        }
    }
    public void LogOutUser(){
        UserAccount user = CurrentUser;
        CurrentUser = null;
        JSONData userData = new JSONData(new StringBuilder("Users/").append(user.UserID).append(".json").toString(),false);
        userData.WriteData(GsonHolder.Instance.Gson.toJson(user));
    }
    public LoginStatus AttemptLogIn(String username, String password){
        //first "log in" and confirm login based on user account
        String id = UserIDs.getOrDefault(username,"");
        if(id == "") return LoginStatus.Failed_AccountExistError;
        LogInUser(id);
        if(CurrentUser == null) return LoginStatus.Failed_AccountExistError;
        //check password
        if(!(CurrentUser.CheckPassword(password))) return LoginStatus.Failed_IncorrectPassword;
        return LoginStatus.Complete;
    }
    public boolean CreateUser(String username, String password){
        try {
            UserAccount user = new UserAccount(username, password, GenerateUniqueUserID(), new ArrayList<>(), new HashMap<>());
            JSONData userData = new JSONData(new StringBuilder("Users/").append(user.UserID).append(".json").toString(), false);
            userData.WriteData(GsonHolder.Instance.Gson.toJson(user));
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    private void SaveToJson(){
        JsonFile.WriteData(GsonHolder.Instance.Gson.toJson(UserIDs));
    }

}

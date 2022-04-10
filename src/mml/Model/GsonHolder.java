package mml.Model;

import com.google.gson.Gson;

public class GsonHolder {
    private static GsonHolder Instance = new GsonHolder();
    public Gson Gson;
    private GsonHolder(){
        Gson = new Gson();
    }
    public static GsonHolder GetInstance(){
        return Instance;
    }
}

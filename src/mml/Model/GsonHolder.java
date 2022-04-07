package mml.Model;

import com.google.gson.Gson;

public class GsonHolder {
    public static GsonHolder Instance = new GsonHolder();
    public Gson Gson;
    private GsonHolder(){
        Gson = new Gson();
    }
}

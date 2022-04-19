package mml.Model;

import com.google.gson.Gson;

public class GsonHolder {
    private static GsonHolder Instance = new GsonHolder();
    public Gson Gson;

    /**
     * Private constructor of the singleton object, initializing it
     */
    private GsonHolder(){
        Gson = new Gson();
    }

    /**
     * Retrieves the instance of the singleton object
     * @return The instance of GsonHolder
     */
    public static GsonHolder GetInstance(){
        return Instance;
    }
}

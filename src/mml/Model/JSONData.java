package mml.Model;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;


public class JSONData {
    private String Filename;
    private String Data;
    private boolean ReadOnly;

    public JSONData(String filename, boolean readOnly){
        Filename = filename;
        try {
            Data = Files.readString(Path.of(Filename));
        }
        catch(Exception ex){
            Data = "";
        }
        ReadOnly = readOnly;
    }

    public String GetData(){
        return Data;
    }

    public void WriteData(String input){
        if(!ReadOnly){
            try {
                Files.writeString(Path.of(Filename), input);
                Data = input;
            }
            catch(Exception ex){
                //might go and write some form of exception window eventually
            }
        }
    }
}

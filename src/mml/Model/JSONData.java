package mml.Model;

import java.io.File;
import java.io.FileOutputStream;
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

    private Boolean CheckCreateFile(String Filepath){
        try {
            File f = new File(Filepath);
            File p = f.getParentFile();
            if (p != null) p.mkdirs();
            if (!f.exists()) return f.createNewFile();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public void WriteData(String input){
        if(!ReadOnly){
            try {
                Boolean fileExists = CheckCreateFile(Filename);
                if(fileExists){
                    Files.writeString(Path.of(Filename), input);
                    Data = input;
                }
            }
            catch(Exception ex){
                System.out.println(ex);
                //might go and write some form of exception window eventually
            }
        }
    }
}

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

    /**
     * Creates a new JSONData object
     * @param filename The full path of the JSONData, used for reading/writing
     * @param readOnly Boolean that tells whether this data should be read-only
     */
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

    /**
     * Retrieves the string data of the JSONData
     * @return The string data of the JSONData
     */
    public String GetData(){
        return Data;
    }
    /**
     * Checks to see if a file exists, and creates it if it does not
     * @param Filepath The file to check existence
     * @return True if the file exists, false if the file does not exist (and could not be created)
     */
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

    /**
     * Writes the given input to disk, and sets Data equal to that input
     * @param input The data to write to disk, and to set as Data
     */
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

package mml.Model;

public class WebsiteRating {
    private String Source;
    private String Value;

    public String GetSource(){
        return Source;
    }

    public Double GetValueAsDouble(){
        String[] vals = new String[2];
        if (Value.contains("%")){
            vals[0] = Value.replace("%","");
            vals[1] = "100";
        }
        else if (Value.contains("/")){
            vals = Value.split("/");
        }
        else{
            vals = Value.split("\\\\");
        }
        if(vals.length != 2) return 0d;

        return Double.parseDouble(vals[0]) / Double.parseDouble(vals[1]);
    }
}

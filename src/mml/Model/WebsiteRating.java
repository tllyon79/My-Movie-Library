package mml.Model;

public class WebsiteRating {
    private String Source;
    private String Value;

    public String GetSource(){
        return Source;
    }

    public Double GetValueAsDouble(){
        return Double.parseDouble(Value);
    }
}

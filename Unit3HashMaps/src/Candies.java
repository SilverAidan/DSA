import java.util.ArrayList;

public class Candies {
    ArrayList<String> types;

    public Candies(ArrayList<String> types){
        this.types = types;
    }

    @Override
    public String toString() {
        return "Candies [types=" + types + "]";
    }
    
}

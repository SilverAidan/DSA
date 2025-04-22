import java.util.HashSet;

public class Airline {
    HashSet<City> cities;
    City hub;
    
    public Airline(HashSet<City> cities, City hub) {
        this.cities = cities;
        this.hub = hub;
    }

    @Override
    public String toString() {
        String output = "hub: " + hub.name + "\nconnections\n";
        for(City c:cities){
            output +=c + "\n";
        }
        return output;
    }
    
}

import java.util.HashSet;
import java.util.Set;

public class Travel {
    public static void main(String[] args) {
        City[] cities = new City[6];
        cities[0] = new City("BDL");
        cities[1] = new City("SYD");
        cities[2] = new City("CDG");
        cities[3] = new City("LAX");
        cities[4] = new City("HND");
        cities[5] = new City("EZE");
        
        cities[0].connections.put(cities[3], 250.00);
        cities[0].connections.put(cities[2], 800.00);
        cities[3].connections.put(cities[5], 500.00);
        cities[3].connections.put(cities[1], 1000.00);
        cities[3].connections.put(cities[4], 900.00);
        cities[5].connections.put(cities[1], 700.00);
        cities[2].connections.put(cities[4], 900.00);

        HashSet<City> set = new HashSet<City>(Set.of(cities));

        Airline DSAir = new Airline(set, cities[0]);
        System.out.println(DSAir);
    }
}

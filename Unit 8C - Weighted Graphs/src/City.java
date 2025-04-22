import java.util.HashMap;

public class City implements Comparable<City> {
    String name;
    HashMap<City, Double> connections;
    double price;
    
    public City(String name, HashMap<City, Double> connections, double price) {
        super();
        this.name = name;
        this.connections = connections;
        this.price = price;
    }

    public City(String name, HashMap<City, Double> connections) {
        this.name = name;
        this.connections = connections;
    }

    public City(String name) {
        this(name, new HashMap<City,Double>(), 0);
    }

    @Override
    public int compareTo(City o) {
        // TODO Auto-generated method stub
        return (int) ((this.price - o.price)*100);
    }

    @Override
    public String toString() {
        String output = name + ": ";
        for (City c : connections.keySet()){
            output += c.name + ", " + connections.get(c);
        }
        output += "\n + relax price" + price;
        return output;
    }
}

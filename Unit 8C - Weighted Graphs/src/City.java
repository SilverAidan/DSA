import java.util.HashMap;

public class City implements Comparable<City> {
    String name;
    HashMap<City, Double> connections;
    double price;
    City previous;
    public City(String name, HashMap<City, Double> connections, double price) {
        this.name = name;
        this.connections = connections;
        this.price = price;
    }

    public City(String name, HashMap<City, Double> connections) {
        this(name, connections, 0);
    }

    public City(String name) {
        this(name, new HashMap<City, Double>(), 0);
    }

    @Override
    public int compareTo(City o) {
        return (int)((this.price - o.price) * 100);
    }

    @Override
    public String toString() {
        String output = "{" + name + ": ";
        for (City c : connections.keySet())
            output += c.name + ", " + connections.get(c) + "; ";
        return output + " From " + (this.previous != null ? this.previous.name : "") + "\n + relax price: " + price + "}";
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        if (previous == null) {
            return this.name;
        }
        return this.previous.getRoute() + "=>" + this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<City, Double> getConnections() {
        return connections;
    }

    public void setConnections(HashMap<City, Double> connections) {
        this.connections = connections;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public City getPrevious() {
        return previous;
    }

    public void setPrevious(City previous) {
        this.previous = previous;
    }

    
    

    
}

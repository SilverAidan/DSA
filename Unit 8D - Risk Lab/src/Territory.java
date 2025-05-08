import java.util.*;

public class Territory implements Comparable<Territory> {
    String name;
    HashMap<Territory, Integer> connections;
    int soldiers;
    Territory previous;
    double cost;

    public Territory(String name, int soldiers) {
        this.name = name;
        this.soldiers = soldiers;
        this.connections = new HashMap<>();
        this.previous = null;
        this.cost = Double.MAX_VALUE;
    }

    public void addConnection(Territory neighbor, int soldierCost) {
        connections.put(neighbor, soldierCost);
    }

    @Override
    public int compareTo(Territory other) {
        return Double.compare(this.cost, other.cost);
    }

    @Override
    public String toString() {
        return name;
    }
}

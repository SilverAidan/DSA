import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Airline {
    HashSet<City> cities;
    City hub;

    public Airline(HashSet<City> cities, City hub) {
        this.cities = cities;
        this.hub = hub;
    }

    @Override
    public String toString() {
        String output = "Hub: " + hub.name + " Connections:\n";
        for (City c : cities) {
            output += c + "\n";
        }
        return output;
    }

    public void undirect() {
        for (City c : cities) {
            for (City q : c.connections.keySet())
                q.connections.put(c, c.connections.get(q));
        }
    }

    public ArrayList<Edge> prim() {
        ArrayList<Edge> mst = new ArrayList<Edge>();
        PriorityQueue<Edge> routes = new PriorityQueue<Edge>();
        HashSet<City> visited = new HashSet<City>();
        //Populate hub's neighbors
        for (City c : hub.connections.keySet()) {
            routes.add(new Edge(hub, c, hub.connections.get(c)));
        }

        visited.add(hub);
        while (!routes.isEmpty()) {
            Edge e = routes.poll();
            if (!visited.contains(e.B)) {
                mst.add(e);
                visited.add(e.B);
                for (City c : e.B.connections.keySet()) {
                    routes.add(new Edge(e.B, c, e.B.connections.get(c)));
                }
            }
            if (visited.size() == cities.size()) return mst;
        }
        return null;
    }

    public void dijkstra() {
        PriorityQueue<City> toVisit = new PriorityQueue<City>();
        for (City c : cities) {
            if (c == hub)
                c.price = 0;
            else
                c.price = Double.MAX_VALUE;
            toVisit.add(c);
        }
        HashSet<City> visited = new HashSet<City>();
        while (!toVisit.isEmpty()) {
            City exploring = toVisit.poll();
            if (!visited.contains(exploring)) { // Avoid re-heapifying
                for (City c : exploring.connections.keySet()) {
                    if (!visited.contains(c)) {
                        if (c.price > exploring.price + exploring.connections.get(c)) {
                            c.price = exploring.price + exploring.connections.get(c);
                            c.previous = exploring;
                        }
                        toVisit.offer(c);
                    }

                }
            }
        }
    }

}

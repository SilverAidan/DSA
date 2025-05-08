import java.util.*;

public class Risk {
    HashMap<String, Territory> territories = new HashMap<>();

    public void addTerritory(String name, int soldiers) {
        territories.put(name, new Territory(name, soldiers));
    }

    public void connectTerritories(String from, String to, int soldierCost) {
        Territory a = territories.get(from);
        Territory b = territories.get(to);
        if (a != null && b != null) {
            a.connections.put(b, soldierCost);
            b.connections.put(a, soldierCost);
        }
    }

    public ArrayList<Edge> prim(String startName) {
        ArrayList<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> routes = new PriorityQueue<>();
        HashSet<Territory> visited = new HashSet<>();

        Territory start = territories.get(startName);
        if (start == null) return null;

        visited.add(start);
        for (Territory neighbor : start.connections.keySet()) {
            routes.add(new Edge(start, neighbor, start.connections.get(neighbor)));
        }

        while (!routes.isEmpty() && visited.size() < territories.size()) {
            Edge edge = routes.poll();
            if (!visited.contains(edge.B)) {
                mst.add(edge);
                visited.add(edge.B);
                for (Territory neighbor : edge.B.connections.keySet()) {
                    if (!visited.contains(neighbor)) {
                        routes.add(new Edge(edge.B, neighbor, edge.B.connections.get(neighbor)));
                    }
                }
            }
        }

        return mst;
    }

    public List<Territory> dijkstra(String from, String to, int initialSoldiers) {
        PriorityQueue<Territory> pq = new PriorityQueue<>();
        Territory start = territories.get(from);
        Territory end = territories.get(to);
        if (start == null || end == null) return null;

        for (Territory t : territories.values()) {
            t.cost = Double.MAX_VALUE;
            t.previous = null;
        }

        start.cost = 0;
        pq.add(start);

        while (!pq.isEmpty()) {
            Territory curr = pq.poll();

            if (curr == end) break;

            for (Territory neighbor : curr.connections.keySet()) {
                int soldierLoss = curr.connections.get(neighbor);
                double newCost = curr.cost + soldierLoss;

                if (newCost < neighbor.cost && initialSoldiers >= newCost) {
                    neighbor.cost = newCost;
                    neighbor.previous = curr;
                    pq.add(neighbor);
                }
            }
        }

        if (end.previous == null) return null;

        LinkedList<Territory> path = new LinkedList<>();
        for (Territory at = end; at != null; at = at.previous) {
            path.addFirst(at);
        }

        return path;
    }
}

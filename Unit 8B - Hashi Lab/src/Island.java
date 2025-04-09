import java.util.*;

public class Island {
    private int x, y, value;
    private List<Island> connections;

    public Island(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.connections = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRequiredBridges() {
        return value;
    }

    public void addConnection(Island other) {
        if (other != null && !connections.contains(other)) {
            connections.add(other);
            other.connections.add(this);
        }
    }

    public int getBridgeCount() {
        return connections.size();
    }

    public List<Island> getConnections() {
        return Collections.unmodifiableList(connections);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Island)) return false;
        Island island =

import java.util.Objects;

public class Edge implements Comparable<Edge> {
    Territory A, B;
    int cost;

    public Edge(Territory a, Territory b, int cost) {
        A = a;
        B = b;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return A.name + "--" + B.name + "; " + cost;
    }

    @Override
    public int compareTo(Edge o) {
        return (int)((this.cost - o.cost) * 100);
    }

    @Override
    public int hashCode() {
        return A.name.hashCode()+B.name.hashCode()+(int)cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        return ((Objects.equals(A.name, other.A.name) && Objects.equals(B.name, other.B.name)||Objects.equals(A.name, other.B.name) && Objects.equals(B.name, other.A.name)))
                && Double.doubleToLongBits(cost) == Double.doubleToLongBits(other.cost);
    }

    

    
}

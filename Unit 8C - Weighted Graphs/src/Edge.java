public class Edge implements Comparable<Edge> {
    City A, B;
    double cost;

    public Edge(City a, City b, double cost) {
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

    
}

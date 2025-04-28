public class Edge {
    City A;
    City B;
    double cost;

    public Edge(City a, City b, double cost) {
        A = a;
        B = b;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return A.name + " : " + B.name + " ; " + cost;
    }

    
}

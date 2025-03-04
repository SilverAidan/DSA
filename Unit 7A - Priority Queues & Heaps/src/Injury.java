public class Injury implements Comparable<Injury>{
    String ailment;
    int priority;

    public Injury(String ailment, int priority) {
        super();
        this.ailment = ailment;
        this.priority = priority;
    }

    public Injury() {
        ailment = "Fever";
        priority = 5;
    }

    @Override
    public int compareTo(Injury o) {
        return this.priority-o.priority;
    }

    @Override
    public String toString() {
        return ailment + " " + priority;
    }
}

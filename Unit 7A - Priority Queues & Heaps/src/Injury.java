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


    public int compareToMax(Injury o){
        return o.priority-this.priority;
    }

    public int compareTo(Injury o, boolean isMin){
        return isMin?compareTo(o):this.compareToMax(o);
    }

    @Override
    public String toString() {
        return ailment + " " + priority;
    }
}

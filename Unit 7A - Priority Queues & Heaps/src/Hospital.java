import java.util.ArrayList;

public class Hospital {
    public static void main(String[] args) {
        ArrayList<Injury> injuries = new ArrayList<>();
        Triage heap = new Triage(injuries);
        heap.add(new Injury("Headache", 5));
        heap.add(new Injury("Shot Arm", 3));
        heap.add(new Injury("Shot Chest", 1));
        heap.add(new Injury("Shot Kidney", 2));
        heap.add(new Injury("In Labor", 2));
        heap.add(new Injury("Paper Cut", 6));
        System.out.println(heap);
    }
}

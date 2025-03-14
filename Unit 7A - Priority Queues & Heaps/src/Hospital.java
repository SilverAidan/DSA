import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

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
        System.out.println(heap.treat());
        System.out.println(heap);
        heap.setPriority(3,1);
        System.out.println(heap);

        Injury[] unordered = {new Injury("A", 10), new Injury("B", 2),
                new Injury("C", 1), new Injury("D", 1), new Injury("E" ,2),
                new Injury("F",8)};
        Triage newOrder = new Triage(unordered);
        System.out.println(newOrder);
        PriorityQueue<Integer> pq = new PriorityQueue<>();//default min
        pq.add(5);pq.add(2);pq.add(8);pq.add(2);pq.add(1);
        System.out.println(pq.poll());//remove
        System.out.println(pq.peek());//get
        System.oust.println(pq.remove());//remove but could crash
        PriorityQueue<Integer> pqMax = new PriorityQueue<>(Collections.reverseOrder());//default min
    }
}

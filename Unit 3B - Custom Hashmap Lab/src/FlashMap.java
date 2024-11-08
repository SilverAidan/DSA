import java.io.File;
import java.util.Iterator;

public class FlashMap implements Iterable<FlashEntry> {
     FlashEntry[] Views; 
     int defaultCapacity = 1000;
 
     public FlashMap() {
        Views = new FlashEntry[defaultCapacity];
     }
 
     public void put(FlashEntry e) {
        int index = e.key.hashCode()%Views.length;
        if (Views[index] == null) {
            Views[index] = e; return;
        }
        FlashEntry runner = Views[index];
        while (Views[index].next != null) {  
            runner = runner.next;
            
        }
        runner.next = e;
     }
 
     public WatchTime get(Streamer addy) {
        throw new UnsupportedOperationException("TODO");
     }
 
     public String toString() {
        String output;
        for(int i = 0; i < 10; i++){
            output += Views[i] + "\n";
        }
     }
 
     public int size() {
        throw new UnsupportedOperationException("TODO");
     }
 
     public WatchTime remove(Streamer addy) {
        throw new UnsupportedOperationException("TODO");
     }
 
     public void rehash() { 
        throw new UnsupportedOperationException("TODO");
     }
 
     public void loadFromFile(File file) {
        throw new UnsupportedOperationException("TODO");
     }
 
     public double loadFactor() {
        throw new UnsupportedOperationException("TODO");
     }
 
     public boolean equals(FlashEntry entry) {
        throw new UnsupportedOperationException("TODO");
     }
 
     public int hashCode() {
        throw new UnsupportedOperationException("TODO");
     }
 
     @Override
     public Iterator<FlashEntry> iterator() {
        throw new UnsupportedOperationException("TODO");
     }
 }
 
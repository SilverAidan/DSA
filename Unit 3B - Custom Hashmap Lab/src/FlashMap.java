public class FlashMap {
   FlashEntry[] Views;
   int defaultCapacity = 50;
   int collisionCount = 0;

   public FlashMap() {
      Views = new FlashEntry[defaultCapacity];
   }

   public void put(FlashEntry e) {
      if (size() / (double) Views.length > 0.75) {
         rehash();
      }
      int index = Math.abs(e.key.hashCode() % Views.length);
      if (Views[index] == null) {
         Views[index] = e;
         return;
      }
      FlashEntry runner = Views[index];
      while (runner.next != null) {
         if (runner.key.equals(e.key)) {
            runner.value = e.value;
            return;
         }
         runner = runner.next;
      }
      runner.next = e;
      collisionCount++;
   }

   public WatchTime get(Streamer addy) {
      int index = Math.abs(addy.hashCode() % Views.length);
      FlashEntry current = Views[index];
      if (current == null) {
         return null;
      }
      while (current != null) {
         if (current.key.equals(addy)) {
            return current.value;
         }
         current = current.next;
      }
      return null;
   }

   public String toString() {
      String output = "";
      for (int i = 0; i < 10; i++) {
         if (Views[i] != null) {
            FlashEntry current = Views[i];
            output += (i + 1) + ": " + current.key + " -> " + current.value;
            while (current.next != null) {
               current = current.next;
               output += " C-> " + current.key + " -> " + current.value;
            }
            output += "\n";
         } else {
            output += (i + 1) + ": Null\n";
         }
      }
      output += "Total Collisions: " + collisionCount + "\n";
      output += "Load Factor (after rehashing if needed): " + loadFactor();
      return output;
   }

   public int size() {
      int count = 0;
      for (int i = 0; i < Views.length; i++) {
         FlashEntry current = Views[i];
         while (current != null) {
            count++;
            current = current.next;
         }
      }
      return count;
   }

   public WatchTime remove(Streamer addy) {
      int index = Math.abs(addy.hashCode() % Views.length);
      FlashEntry current = Views[index];
      FlashEntry previous = null;
      if (current == null)
         return null;
      while (current != null) {
         if (current.key.equals(addy)) {
            if (previous == null) {
               Views[index] = current.next;
            } else {
               previous.next = current.next;
            }
            collisionCount--;
            return current.value;
         }
         previous = current;
         current = current.next;
      }
      return null;
   }

   public double loadFactor() {
      return (double) size() / Views.length;
   }

   public void rehash() {
      FlashEntry[] oldViews = Views;
      Views = new FlashEntry[oldViews.length * 2];
      for (int i = 0; i < oldViews.length; i++) {
         FlashEntry current = oldViews[i];
         while (current != null) {
            FlashEntry next = current.next;
            int index = Math.abs(current.key.hashCode() % Views.length);
            if (Views[index] == null) {
               Views[index] = current;
            } else {
               FlashEntry runner = Views[index];
               while (runner.next != null) {
                  runner = runner.next;
               }
               runner.next = current;
            }
            current.next = null;
            current = next;
         }
      }
      collisionCount = 0;
   }
}
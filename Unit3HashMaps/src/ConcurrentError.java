import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConcurrentError {
    public static void main(String[] args) {
        ArrayList<Integer> arrL = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            arrL.add(i);
        }
        //THIS IS BAD CONCURRENT MODIFICATION
        // for(Integer i : arrL){
        //     if(i%2==0){
        //         arrL.remove(i);
        //     }
        // }
        Iterator<Integer> listIt = arrL.iterator();
        while(listIt.hasNext()){
            Integer n = listIt.next();
            if(n%2==0){
                listIt.remove();
            }
        }
        System.out.println(arrL);
        Set<String> fruits = new HashSet<String>();
        fruits.add("apple");fruits.add("banana");fruits.add("cantaloupe");
        System.out.println(fruits.add("apple"));

        HashMap<String, HashSet<String>> fruitColors = new HashMap<>();
        fruitColors.put("Yellow", new HashSet<>(Arrays.asList("Apple", "Banana", "Lemon")));
        fruitColors.put("Red", new HashSet<>(Arrays.asList("Apple", "Pomegranate", "Cherry"))); 
        System.out.println(fruitColors);
        
        for(String key : fruitColors.keySet()){
            fruitColors.get(key).remove("Apple");
        }
        for(HashSet<String> value : fruitColors.values()){
            value.remove("Apple");
        }
        System.out.println(fruitColors);
    }
}

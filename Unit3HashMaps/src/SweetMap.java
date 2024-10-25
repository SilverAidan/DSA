import java.util.ArrayList;
import java.util.Arrays;

public class SweetMap {
    SweetEntry[] Sweets; 
    private static final int defaultCapacity = 10;
    public SweetMap(){
        Sweets = new SweetEntry[defaultCapacity];
    }

    //We place the value based on the key!
    public void put(SweetEntry e){
        int index = e.key.hashCode()%Sweets.length;
        if (Sweets[index] == null) {
            Sweets[index] = e; return;
        }
        while (Sweets[index] != null) {
            if (Sweets[index].key.equals(e.key)){
                Sweets[index].value = e.value; return;
            }
            if (++index == Sweets.length) //Loop around to find any potentially empty spots
                index = 0;
        }
        Sweets[index] = e; //Rehash later
    }

    public Candies get(Address addy) {
        int index = addy.hashCode() % Sweets.length;
        if (Sweets[index] == null)
            return null;
        if (Sweets[index].key.equals(addy)) 
            return Sweets[index].value;
        while (Sweets[index].key != null) {
            if (Sweets[index].key.equals(addy))
                return Sweets[index].value;
            index = ++index % Sweets.length;
        }
        return null;
    }

    public String toString(){
        String output = "";
        for(int i = 0; i < Sweets.length; i++){
            if(Sweets[i] != null){
                output += i + ":" + Sweets[i].key + "->" + Sweets[i].value + "\n";
            } 
        }
        return output;
    }

    public int size(){
        int count = 0;
        for(SweetEntry s: Sweets){
            if(s != null){
                count++;
            }
        }
        return count;
    }

    public Candies remove(Address addy){
        if(addy == null){
            return null;
        }
        int index = addy.hashCode()%Sweets.length;
        while(Sweets[index]!=null){
            if(Sweets[index].key.equals(addy)){
                Candies temp = Sweets[index].value;
                Sweets[index].value = new Candies(new ArrayList<String>(Arrays.asList("Coffin Crisp")));
                return temp;
            }
            index = ++index%Sweets.length;
        }
        return null;
    } 

    public void rehash(){
        SweetEntry[] amongus = Sweets;
        Sweets = new SweetEntry[amongus.length*2];

        for(SweetEntry s:amongus){
            if(s != null&& s.value.types.get(0).equals("Coffin Crisp")){
                put(s);
            }
        }
    }
}

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
            if (Sweets[index].key.equals(e.key))
                Sweets[index].value = e.value;
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
    }
}

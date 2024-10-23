public class SweetMap {
    SweetEntry[] Sweets;
    public static final int defaultCapacity = 10;

    public SweetMap(){
        Sweets = new SweetEntry[defaultCapacity];
    }

    public void put(SweetEntry e){
        int index = e.key.hashCode()%Sweets.length;
        if(Sweets[index]==null){
            Sweets[index] = e; return;
        }
        while(Sweets[index]!=null){
            if(Sweets[index].key.equals(e.key)){
                Sweets[index] = e;
            }
            if(++index == Sweets.length){
                index = 0;
            }
            //index = (index+1)==Sweets.length?0:index+1;
            //index = ++index%Sweets.length;
        }
        Sweets[index] = e;//Check for rehashing
    }

    public void get(Address addy){
        int index = addy.hashCode()%Sweets.length;
    }
}

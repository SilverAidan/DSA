import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class Driver {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello".hashCode());
        for(char c:"Hello".toCharArray())
            System.out.println((int)c);

        HashMap<String, String> dict = new HashMap<String,String>();
        dict.put("apple", "fruit apple pie is made of");
        dict.put("pumpkin", "carved for Halloween");
        dict.put("corn", "used for cornbread");
        System.out.println(dict.get("apple"));
        dict.put("apple", "fruit that Glastonbarians grow");
        System.out.println(dict.get("apple"));
        dict.remove("apple");
        System.out.println(dict.get("apple"));
        System.out.println(dict.remove("corn","don't know"));
        for(String key:dict.keySet())
            System.out.println(dict.get(key));

        String s1 = "apple";
        String s2 = "apple";

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        SweetMap Glastonbury = new SweetMap();
        Address a1 = new Address("06033", "Hubbard", 330);
        Candies c1 = new Candies(new ArrayList<String>(Arrays.asList("KitKat", "Snickers")));
        SweetEntry e1 = new SweetEntry(a1, c1);
        Glastonbury.put(e1);
        Address a2 = new Address("06033", "Main", 100);
        Candies c2 = new Candies(new ArrayList<String>(Arrays.asList("Twix", "M&M", "Twizzlers")));
        SweetEntry e2 = new SweetEntry(a2, c2);
        Glastonbury.put(e2);
        Address a3 = new Address("06033", "Dummy", 100);
        System.out.println(Glastonbury.get(a3));
        System.out.println(Glastonbury);
        System.out.println(Glastonbury.size());
        Glastonbury.remove(a1);
        System.out.println(Glastonbury);
        SweetEntry e3 = new SweetEntry(a1, new Candies(new ArrayList<String>(Arrays.asList("Toblerone", "Nerd Gummi Clusters"))));
        Glastonbury.put(e3);
        System.out.println(Glastonbury);
    }
}

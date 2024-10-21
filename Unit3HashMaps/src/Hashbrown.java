import java.util.HashMap;

public class Hashbrown {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello".hashCode());
        for(char c:"Hello".toCharArray()){
            System.out.println((int)c);
        }

        HashMap<String, String> dict = new HashMap<String, String>();
        dict.put("apple", "fruit apple pie is made of");
        dict.put("pumpkin", "carved for halloween");
        dict.put("corn", "carved for cornbread");
        System.out.println(dict.get("apple"));
        dict.put("apple", "fruit that glastonbarians grow");
        System.out.println(dict.get("apple"));
        dict.remove("apple");
        System.out.println(dict.get("apple"));
        System.out.println(dict.remove("corn", "dont know"));
        for(String key : dict.keySet()){
            System.out.println(dict.get(key));
        }
    }
}

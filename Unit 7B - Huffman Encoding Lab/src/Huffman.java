import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman {
    private static HashMap<Character,Integer> frq;
    private static HashMap<CharNode,String> binary;
    private static PriorityQueue<Character> heap = new PriorityQueue<>();

    Huffman(String content){
        hashifyFile(content);
    }

    private static void hashifyFile(String content) {
        for(char c: content.toCharArray()){
            if(frq.containsKey(c)){
                frq.put(c, frq.get(c)+1);
            }
            else{
                frq.put(c, 1);
            }
        }
    }
    
    private static void heapifyHashMap(HashMap<Character,Integer>){

    }

    private static void encode(){

    }

    private static void decode(){

    }
}

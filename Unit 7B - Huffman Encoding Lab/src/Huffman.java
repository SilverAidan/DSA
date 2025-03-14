import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Huffman {
    private static HashMap<Character, Integer> frq = new HashMap<>();
    private static PriorityQueue<CharNode> tree = new PriorityQueue<>();
    private static HashMap<Character, String> binary = new HashMap<>();
    private static HashMap<String, Character> binaryReverse;
    private static String input;

    // Constructor to initialize Huffman with input content
    Huffman(String content) {
        input = content;
        hashifyFile(content);
    }

    // Method to create a frequency map from the input content
    private static void hashifyFile(String content) {
        for (char c : content.toCharArray()) {
            if (frq.containsKey(c)) {
                frq.put(c, frq.get(c) + 1);
            } else {
                frq.put(c, 1);
            }
        }
        treeifyHashMap();
    }

    // Method to create a priority queue (tree) from the frequency map
    private static void treeifyHashMap() {
        for (Character c : frq.keySet()) {
            tree.add(new CharNode(c, frq.get(c)));
        }
        while(tree.size() > 1){
            CharNode left = tree.poll();
            CharNode right = tree.poll();
            int freqTotal = left.getFrequency() + right.getFrequency();
            CharNode newParent = new CharNode((char)0, freqTotal);
            newParent.setLeft(left);
            newParent.setRight(right);
            tree.add(newParent);
        }
        makeBinaryHashMap();
    }

    // Method to create a binary map from the Huffman tree
    private static void makeBinaryHashMap() {
        CharNode root = tree.peek();
        if (root != null) {
            buildBinaryMap(root, "");
        }
        binaryReverse = reverseHash(binary);
    }

    // Recursive method to build the binary map
    private static void buildBinaryMap(CharNode node, String path) {
        if (node.getLeft() == null && node.getRight() == null) {
            binary.put(node.getValue(), path);
        } else {
            if (node.getLeft() != null) {
                buildBinaryMap(node.getLeft(), path + "0");
            }
            if (node.getRight() != null) {
                buildBinaryMap(node.getRight(), path + "1");
            }
        }
    }

    // Method to return a string representation of the binary map
    @Override
    public String toString() {
        return binary.toString();
    }

    // Method to encode the input content using the binary map
    public String encode() {
        String output = "";
        for(char c : input.toCharArray()){
            output += binary.get(c);
        }
        return output;
    }

    // Method to decode the encoded string using the reverse binary map
    public String decode(String output) {
        String last = "";
        String building = "";
        for(char c : output.toCharArray()){
            building += c;
            if(binaryReverse.containsKey(building)){
                last += binaryReverse.get(building);
                building = "";
            }
        }
        return last;
    }

    // Method to reverse the binary map
    private static HashMap<String, Character> reverseHash(HashMap<Character,String> bruh){
        HashMap<String, Character> reversed = new HashMap<>();
        for (Entry<Character, String> entry : bruh.entrySet()) {
            reversed.put(entry.getValue(), entry.getKey());
        }
        return reversed;
    }
}

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Huffman {
    private static HashMap<Character, Integer> frq = new HashMap<>();
    private static PriorityQueue<CharNode> tree = new PriorityQueue<>();
    private static HashMap<Character, String> binary = new HashMap<>();
    private static HashMap<String, Character> binaryReverse;
    private static String input;

    Huffman(String content) {
        input = content;
        hashifyFile(content);
    }

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
            System.out.println("PQ"+tree);
        }
        makeBinaryHashMap();
    }

    private static void makeBinaryHashMap() {
        CharNode root = tree.peek();
        if (root != null) {
            buildBinaryMap(root, "");
        }
        binaryReverse = reverseHash(binary);
    }
    
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

    @Override
    public String toString() {
        return binary.toString();
    }

    public String encode() {
        String output = "";
        for(char c : input.toCharArray()){
            output += binary.get(c);
        }
        return output;
        }

        public String decode(String output) {
            String last = "";
            String building = "";
            for(char c : output.toCharArray()){
                building += c;
                if(binaryReverse.containsKey(building)){
                last += binaryReverse.get(building);
                building = "";
            }
            return last;
        }

        private static HashMap<String, Character> reverseHash(HashMap<Character,String> bruh){
            HashMap<String, Character> reversed = new HashMap<>();
            for (Entry<Character, String> entry : bruh.entrySet()) {
                reversed.put(entry.getValue(), entry.getKey()); 
            }
            return reversed;
        }
    }
}

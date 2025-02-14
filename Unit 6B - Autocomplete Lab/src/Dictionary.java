import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private TNode root;

    public Dictionary(String[] words){
        root = new TNode("rooter", false);
        for(String word : words){
            addKey(word);
        }
    }

    public void addKey(String key) {
        TNode runner = root;
        for (char letter : key.toCharArray()) {
            if (!runner.children.containsKey("" + letter)) {
                runner.children.put("" + letter, new TNode("" + letter, false));
            }
            runner = runner.children.get("" + letter); 
        }
        runner.isKey = true; 
    }

    public void deleteKey(String key){
        TNode bruh = findNode(key);
        if(bruh != null){
            if(bruh.isKey){
                bruh.isKey = false;
            }
        }
    }

    public String toString() {
        return toStringHelper(root);
    }
    
    private String toStringHelper(TNode node) {
        String result = "";
        for (Map.Entry<String, TNode> entry : node.children.entrySet()) {
            result += entry.getKey() + "\n";
            result += toStringChildren(entry.getValue(), "");
        }
        return result;
    }
    
    private String toStringChildren(TNode node, String prefix) {
        String result = "";
        if (node.isKey) {
            result += prefix + "\n";
        }
        for (Map.Entry<String, TNode> entry : node.children.entrySet()) {
            result += toStringChildren(entry.getValue(), prefix + entry.getKey());
        }
        return result;
    }

    public TNode findNode(String key) {
        TNode runner = root;
        for (char letter : key.toCharArray()) {
            if (!runner.children.containsKey("" + letter)) {
                return null;
            }
            runner = runner.children.get("" + letter);
        }
        return runner; 
    }

    public HashMap<String, TNode> auto(String prefix) {
        HashMap<String, TNode> words = new HashMap<>();
        TNode node = findNode(prefix); 
        
        if (node != null) {
            autoHelper(node, prefix, words);
        }
        return words;
    }
    
    public void autoHelper(TNode node, String prefix, HashMap<String, TNode> words) {
        if (node.isKey) {
            words.put(prefix, node);
        }
        for (String key : node.children.keySet()) {
            autoHelper(node.children.get(key), prefix + key, words);
        }
    }

    public boolean isKey(String word) { 
        TNode node = findNode(word);
        return node != null && node.isKey;
    }

    public void compress(String s) {
        if (s == null || s.length() == 0) return;
        
        TNode parent = root;
        TNode current = root.children.get(s.substring(0, 1));
        if (current == null) return;
    
        String chain = s.substring(0, 1);
        int index = 1;
    
        while (index < s.length()) {
            String nextChar = s.substring(index, index + 1);
            if (current.children.size() != 1 || !current.children.containsKey(nextChar)) {
                return;
            }
            chain += nextChar;
            parent = current;
            current = current.children.get(nextChar);
            index++;
        }
    
        while (current.children.size() == 1 && !current.isKey) {
            Map.Entry<String, TNode> entry = current.children.entrySet().iterator().next();
            chain += entry.getKey();
            current = entry.getValue();
        }
    
        TNode newNode = new TNode(chain, current.isKey);
        newNode.children = current.children;
    
        // Update the parent's reference instead of root
        parent.children.remove(s.substring(0, 1));
        parent.children.put(chain, newNode);
    }
    
}

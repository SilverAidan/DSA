import java.util.List;

public class Dictionary {
    private TNode root;

    public Dictionary(){
        root = new TNode("rooter", false);
    }

    public void addKey(String key) {
        TNode runner = root;
        for(char letter : key.toCharArray()){
            if(!runner.children.containsKey("" + letter)){
                runner.children.put("" + letter, new TNode("" + letter, false));
            }
            runner = runner.children.get(key);
            runner.isKey = true;
        }
    }

    public void deleteKey(String key){

    }

    public String toString(){ 

    }

    public TNode findNode(String key){
        TNode runner = root;
        for(char letter : key.toCharArray()){
            if(!runner.children.containsKey("" + letter)){
                return null;
            }
            runner = runner.children.get(key);
        }
        return runner.isKey ? runner : null;
    }

    public List<String> auto(String prefix) { 

    }

    public boolean isKey(String word) { 
        
    }

    public void compress() { 

    }
}

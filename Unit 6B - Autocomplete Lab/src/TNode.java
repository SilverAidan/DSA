import java.util.HashMap;

public class TNode {
    private String value;
    private boolean isKey;
    HashMap<String,TNode> children;
    
    public TNode(String value, boolean isKey) { 
        
    }
    
    public String getValue() { 
        return value;
    }

    public HashMap<String, TNode> getNext() { 
        return children;
    }
    
    public boolean isKey() { 
        return isKey;
    }
}

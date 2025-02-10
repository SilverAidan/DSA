import java.util.HashMap;

public class TNode {
    String value;
    boolean isKey;
    HashMap<String,TNode> children;

    public TNode(String value, boolean isKey) {
        this.value = value;
        this.isKey = isKey;
    }

    public TNode(String value, boolean isKey, HashMap<String, TNode> children) {
        this.value = value;
        this.isKey = isKey;
        this.children = children;
    }
}

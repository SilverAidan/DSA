import java.util.ArrayList;

public class TNode {
    Integer value;
    TNode left, right;
    public TNode(Integer val){
        value = val;
    }
    public TNode(Integer value, TNode left, TNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void addNode(TNode tn){
        if(tn.value < this.value){
            if(this.left == null){
                this.left = tn;
            }
            else{
                this.left.addNode(tn);
            }
        }else if(tn.value > this.value){
            if(this.right == null){
                this.right = tn;
            }
            else{
                this.right.addNode(tn);
            }
        }
    }

    public String inOrder(){
        String output = "";
        if(left != null){
            output += left.inOrder();
        }
        output += this.value +":";
        if(right==null){
            return output;
        }else{
            return output + right.inOrder();
        }
    }

    public String preOrder(){
        String output = this.value +":";
        if(left != null){
            output += left.preOrder();
        }
        if(right==null){
            return output;
        }else{
            return output + right.preOrder();
        }
    }

    public String postOrder(){
        String output = "";
        if(left != null){
            output += left.postOrder();
        }
        if(right!=null){
            output += right.postOrder();
        }
        return output += this.value +":";
    }

    public int getHeight(){
        return 1 + Math.max(left == null ? -1 : left.getHeight(), right == null ? -1 : right.getHeight());
    }

    public TNode get(Integer i){
        if(value == i){
            return this;
        }
        if(i<value){
            return left == null ? null : left.get(i);
        }
        return right == null ? null : right.get(i);
    }

    public int size(){
        return ((left == null ? 0 : left.size()) + (right == null ? 0 : right.size()) + 1);
    }

    public TNode delete(Integer key){
        TNode parent = this.getParent(key);
        if(parent == null){
            return null;
        }
        TNode disowned = this.get(key);
        //case1 disowned doesnt have children
        if(disowned.left == null && disowned.right == null){
            if(parent.left == disowned){
                parent.left = null;
            }else{
                parent.right = null;
            }
            return disowned;
        }
        //case 2 has one child
        if(disowned.left==null||disowned.right == null){
            TNode grandchild = disowned.left == null ? disowned.right : disowned.left;
            if(parent.left == disowned){
                parent.left = grandchild;
            }else{
                parent.right = grandchild;
            }
            return disowned;
        }
        //case 3 has two children
        TNode biggestChild = disowned.left;
        while(biggestChild.right!=null){
            biggestChild = biggestChild.right;
        }
        disowned.value = biggestChild.value;
        if(biggestChild == disowned.left)
            disowned.left = biggestChild;
        else
            disowned.left.delete(disowned.value);
        return new TNode(key);
    }

    public TNode getParent(Integer key) {
        if(left!=null && left.value == key){
            return this;
        }
        if(right!=null && right.value == key){
            return this;
        }
        if(key<value){
            return left ==null?null:left.getParent(key);
        }
        return right == null?null:right.getParent(key);
    }

    public static void reverse(TNode node){
        if(node == null){
            return;
        }
        TNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        reverse(node.left);
        reverse(node.right);
    }

    public static String outerEdges(TNode root){
        if (root == null) {
            return "";
        }
        String right = "";
        String last = "";
        TNode runner = root;
        ArrayList<TNode> backwards = new ArrayList<>();
        while (runner.left != null) {
            backwards.add(runner.left);
            runner = runner.left;
        }
        runner = root;
        while (runner.right != null) {
            right += " " + runner.right.value;
            runner = runner.right;
        }
        for (int i = backwards.size() - 1; i >= 0; i--) {
            last += backwards.get(i).value + " ";
        }
        last += root.value + " " + right;
        return last.trim();
    }
}

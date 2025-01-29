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

    public TNode get (Integer i){
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
        if(parent.left == null){
            parent.value = 
        }
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
}

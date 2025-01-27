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
        }else if(tn.value > this.value()){
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
        if(left == null){
            output += this.value;
        }else{
            left.inOrder();
        }

        if(right==null){
            return output;
        }else{
            return output + right.inOrder();
        }
    }
}

public class Forrest {
    public static void main(String[] args) {
        TNode oak = new TNode(20);
        int[] arr = {18,19,12,30,21,1,15,16};
        for(int a : arr){
            oak.addNode(new TNode(a));
        }
        System.out.println(oak.inOrder());
        System.out.println(oak.preOrder());
        System.out.println(oak.postOrder());
        System.out.println(oak.getHeight());
        //System.out.println(oak.left.getHeight());
        //System.out.println(oak.left.right.getHeight());
        //System.out.println(oak.get(15).inOrder());
        System.out.println(oak.size());
        TNode ancestor = new TNode(-999);
        ancestor.right = oak;
        //System.out.println(ancestor.getParent(21).value);
        //System.out.println(ancestor.delete(18).value);
        System.out.println(ancestor.postOrder());
        TNode.reverse(oak);
        System.out.println(oak.inOrder());
        System.out.println(TNode.outerEdges(oak));
        int[] arr2 = {8,12,6,4};
        TNode AVL = new TNode(10);
        for(int a : arr2){
            AVL = AVL.addNode(new TNode(a));
        }
        System.out.println(AVL.postOrder());
        AVL = AVL.addNode(new TNode(20));
        AVL = AVL.addNode(new TNode(22));
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Node head = new Node(-99999);
        for(int i = 0; i < 10; i++){
            head.add((int)(Math.random() * 10));
        }
        System.out.println(head);
        mergeLinkSort(head);
        System.out.println(head);
        Quicky nemo = new Quicky(10);
        System.out.println(nemo);
        nemo.quickSort();
        System.out.println(nemo);
    }
    public static Node mergeLinkSort(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node middle = findMiddle(head, head);
        System.out.println(middle);
        Node secondHalf = middle.next;
        middle.next = null;
        head = mergeLinkSort(head); //first half
        Node right = mergeLinkSort(secondHalf); //second half
        return merge(head, right);
    }

    public static Node merge(Node left, Node right) {
        Node dummy = new Node(999); //dummy node
        Node runner = dummy;
        while(left != null && right != null) {
            if((Integer)left.data < (Integer)right.data) {
                runner.next = left;
                left = left.next;
            }else{
                runner.next = right;
                right = right.next;
            }
            runner = runner.next;
        }
        if(left == null)
            runner.next = right;
        else
            runner.next = left;
        return dummy.next;
    }
            
    private static Node findMiddle(Node fast, Node slow) {
        if(fast.next == null ||fast.next.next == null) {
            return slow;
        }
        return findMiddle(fast.next.next, slow.next);
    }
}

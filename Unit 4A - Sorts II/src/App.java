public class App {
    public static void main(String[] args) throws Exception {
        Node head = new Node("MYAHDaD");
        for (int i = 0; i < 8; i++) {
            head.add((int)(Math.random() * i));
        }
        Node head2 = new Node("MYEHDAD");
        for (int i = 0; i < 8; i++) {
            head2.add((int)(Math.random() * i * 2));
        }
        System.out.println("Original: " + head);
        System.out.println("H1: " + mergeLinkSort(head));
        System.out.println(head2);
        System.out.println("H2: " + mergeLinkSort(head2));
        System.out.println(merge(head, head2));

        Quicky nemo = new Quicky(10);
        System.out.println(nemo);
        //nemo.hoareSort();
        System.out.println(nemo);

        System.out.println(nemo.quickSelect(5));

        Chocula chocyMilk = new Chocula(20, 10);
        System.out.println(chocyMilk);
        chocyMilk.countSort();
        System.out.println(chocyMilk);

    }

    //Performs mergeSort Recursively in a LinkedList
    public static Node mergeLinkSort(Node head) {
        if (head == null||head.next == null) {
            return head;
        }
        Node middle = findMiddle(head, head);
        //System.out.println(middle);
        Node secondHalf = middle.next;
        middle.next = null;
        return merge(mergeLinkSort(head), mergeLinkSort(secondHalf));
    }

    //Merges two linklist via mergesort algorithm
    public static Node merge(Node left, Node right) {
        Node dummyHead = new Node("DUH");
        Node runner = dummyHead;
        while (left != null || right != null) {
        //     if (left != null && right != null)
        //     System.out.print(left.data + ", " + right.data + ", " + compareNodes(left,right) + "; ");
            if (compareNodes(left, right)) {
                runner.next = left;
                left = left.next;
            } else {
                runner.next = right;
                right = right.next;
            }
            runner = runner.next;
        }
        // System.out.println(dummyHead);
        // System.out.println("L" + left);
        // System.out.println(right);
        // System.out.println();
        return dummyHead.next;

    }

    //Compares two nodes' values for MergeSort
    public static boolean compareNodes(Node one, Node two) {
        if (two == null || two.data == null)
            return true;
        if (one == null || one.data == null)
            return false;
        if (one.data instanceof String)
            return true;
        else if (two.data instanceof String) {
            return false;
        } else if ((Integer) one.data >= (Integer) two.data){
            return false;
        } else {
            return true;
        }
    }

    //Finds one before middle
    public static Node findMiddle(Node slow, Node fast) {
        if (fast.next == null || fast.next.next == null) {
            return slow;
        }
        return findMiddle(slow.next, fast.next.next);
    }
}

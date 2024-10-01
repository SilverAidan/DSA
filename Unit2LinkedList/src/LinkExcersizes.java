@SuppressWarnings({ "rawtypes", "unchecked" })
public class LinkExcersizes {
    public static void main(String[] args){
        
        Node<Integer> one = new Node(1, null);
        one.next = new Node(3, null);
        one.next.next = new Node(5, null);

        Node two = new Node(2,null);
        Node runner = two;
        for(int i = 4; i < 12; i+=2){
            runner.next = new Node(i, null);
            runner = runner.next;
        }
        Node<Integer> zipped = zipperNodes(one, two);
        System.out.println(printChain(zipped));
        System.out.println(printChain(one));
        System.out.println(printChain(two));

        for(Node<Integer> n : zipped){
            System.out.print(n.data+", ");
        }
        
        one.next.next.next = one;
        //for(Node<Integer> n:one){
        //    System.out.print(n.data + ", ");
        //}
        System.out.println(hasCycle(one));
        System.out.println(hasCycle(two));
    }
    public static boolean hasCycle(Node head){
        if(head==null||head.next==null){
            return false;
        }
        Node slow = head;
        Node fast = head.next;
        while(fast!=null&&fast.next!=null){
            if(slow == fast || slow == fast.next){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }


    public static Node zipperNodes(Node n1, Node n2){
        //PRECONDITION BOTH NODES ARE NOT NULL
        boolean which = false;
        Node output = new Node(n1.data, null);
        n1 = n1.next;
        Node runner = output;
        while(n1!=null || n2!=null){
            if(which && n1!=null){
                runner.next = new Node(n1.data, null);
                n1 = n1.next;
                runner = runner.next;
            }else if(!which&&n2!=null){
                runner.next = new Node(n2.data, null);
                n2 = n2.next;
                runner = runner.next;
            }
            which = !which;
        }
        return output;
    }

    public static String printChain(Node n){
        String output = "";
        while(n!=null){
            output += n.data+", ";
            n = n.next;
        }
        return output;
    }
}

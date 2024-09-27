public class LinkExcersizes {
    public static void main(String[] args){
        Node one = new Node(1, null);
        one.next = new Node(3, null);
        one.next.next = new Node(5, null);

        Node two = new Node(2,null);
        Node runner = two;
        for(int i = 4; i < 12; i+=2){
            runner.next = new Node(i, null);
            runner = runner.next;
        }
        Node zipped = zipperNodes(one, two);
        System.out.println(printChain(zipped));
        System.out.println(printChain(one));
        System.out.println(printChain(two));
    }

    public static Node zipperNodes(Node n1, Node n2){
        //TEDIUS NULL CHECKING

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

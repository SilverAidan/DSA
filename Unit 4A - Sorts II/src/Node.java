public class Node {
    Object data;
    Node next;
    public Node(Object o) {
        data = o;
    }

    //Recursive add function
    public void add(Object d) {
        Node tagger = this;
        if (this.next == null) {
            this.next = new Node(d);
        } else {
            this.next.add(d);
        }
    }

    //Recursive remove function
    public Node remove(Object d) {
        if (this.data.equals(d)) {
            return this;
        } else {
            if (this.next == null)
                return null;
            else
                return this.next.remove(d);
        }
    }

    //Recursive toString function
    public String toString() {
        if (next == null) 
            return this.data.toString();
        return this.data + "->" + this.next.toString();
    }
}

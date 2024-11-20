public class Node {
    Object data;
    Node next;
    public Node(Object d){
        data = d;
    }
    public void add(Object d){
        if(this.next == null){
            this.next = new Node(d);
            return;
        }
        this.next.add(d);
    }
    public String toString(){
        if(next == null){
            return ""+data;
        }
        return data + "->" + this.next.toString();
    }
}

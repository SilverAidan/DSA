import java.util.Iterator;

public class Node implements Iterable<Node>{
    int data;
    Node next;
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
    @Override
    public Iterator<Node> iterator() {
        return new LinkIt();
    }

    private class LinkIt implements Iterator<Node>{    
        Node runner = new Node(data,next);
        public boolean hasNext() {
            return runner != null;
        }

        public Node next() {
            if(!hasNext()){
                return null;
            }
            Node output = runner;
            runner = runner.next;
            return output;
        }
    }
}

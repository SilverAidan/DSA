
public class LinkStack<T> {
	Node<T> top;//head of the linked list
	
	public LinkStack(Node<T> n) {
		top = n;
	}
	public LinkStack() {
		
	}
	public boolean isEmpty() {
		return top==null;
	}
	public int size() {
		if(isEmpty())
			return 0;
		int count = 1;
		Node<T> runner = top.next;
		while(runner!=null) {
			count++; runner = runner.next;
		}
		return count;
	}
	public Node<T> peek(){
		return top;
	}
	public Node<T> pop(){
		if(top==null)
			return null;
		Node<T> temp = top;
		top = top.next;
		temp.next = null;
		return temp;
	}
	public void push(Node<T> n) {
		if(n==null)
			return;
		n.next = top;
		top = n;
	}
	public String toString() {
		if(top==null)
			return "NULL";
		Node<T> runner = top;
		String output = "";
		while(runner!=null) {
			output+=runner.data+"->";
			runner = runner.next;
		}
		return output;
	}

}

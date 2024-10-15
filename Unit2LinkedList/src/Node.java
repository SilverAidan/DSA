import java.util.Iterator;

public class Node<T> implements Iterable<Node<T>>{
	T data;
	Node<T> next;
	public Node(T data, Node<T> next) {
		this.data = data; this.next = next;
	}
	@Override
	public Iterator<Node<T>> iterator() {
		return new LinkIt();
	}
	private class LinkIt implements Iterator<Node<T>>{
		Node<T> runner = new Node<T>(data,next);
		@Override
		public boolean hasNext() {
			return runner!=null;
		}

		@Override
		public Node<T> next() {
			if(!hasNext())
				return null;
			Node<T> output = runner;
			runner = runner.next;
			return output;	
		}
		
	}

}

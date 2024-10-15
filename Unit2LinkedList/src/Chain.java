public class Chain {
    Node<Integer> head, tail;
    public Chain(int start, int count, int step) {
        head = new Node<Integer>(start,null);
        Node<Integer> runner = head;
        for(int i = 1; i < count; i++){
            runner.next = new Node<Integer>(runner.data+step, null);
            runner = runner.next;
            tail = runner;
            tail.next = head;
        }
    }

    public Chain(){
        head = null;
        tail = null;
    }

    public String toString(){
        String output = "";
        if(head == null){
            return output;
        }
        Node<Integer> runner = head;
        do{
            output += runner.data + "->";
            runner = runner.next;
        } while(runner != head);
        output += "Back to head";
        return output;
    }

	public void removeNode(int number, int i){
        if(head == null){
            head = new Node<Integer>(number,null);
            head.next = head;
            tail = head;
            return;
        }
        Node<Integer> runner = head;
        
        for(;i>1;i--){
            runner = runner.next;
        }
        Node<Integer> addOn = new Node<Integer>(number, runner.next);
        runner.next = addOn;

        //CHECK IF HEAD == TAIL LATER
        if(addOn.next == tail){
            tail = addOn;
        }
    }

	public void cycle(){
        //TODO
    }

    public void addNode(int number, int i){
        if(head == null){
            head = new Node<Integer>(number,null);
            head.next = head;
            tail = head;
            return;
        }
        Node<Integer> runner = head;
        if(i == 0){
			tail.next = new Node<Integer>(number, null);
			head = tail.next;
		}
        for(;i>1;i--){
            runner = runner.next;
        }
        Node<Integer> addOn = new Node<Integer>(number, runner.next);
        runner.next = addOn;
    }
}

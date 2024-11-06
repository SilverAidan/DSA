package Help;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Kwiwi {
    public static void main(String[] args){
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(5); que.add(7); que.add(6); que.add(12); 
        System.out.println(que);
        System.out.println(que.remove());
        System.out.println(que);
        for(int i = 0; i < que.size(); i++){
            que.add(que.remove()+1);
        }
        System.out.println(que);
        stutter(que);
        System.out.println(que);

        mirror(que);
        System.out.println(que);
    }

    private static void stutter(Queue<Integer> que) {
        int size = que.size();
        for(int i = 0; i < size; i++){
            que.add(que.peek());
            que.add(que.remove());
        }
    }

    private static void mirror(Queue<Integer> que) {
        Stack<Integer> temp = new Stack<Integer>();
        for(Integer i:que){
            temp.push(i);
        }
        while(!temp.isEmpty()){
            que.add(temp.pop());
        }

    }
}
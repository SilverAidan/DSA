import java.util.HashMap;

public class Dynamo {
    static HashMap<Integer, Long> memo = new HashMap<Integer, Long>();
    public static void main(String[] args) throws Exception {
        System.out.println(fib(8));
    }

    public static long fib(int n){
        if (n<=2){
            return 1;
        }
        if(memo.containsKey(n)){
            return memo.get(n);
        }
        memo.put(n, fib(n-1) + fib(n-2));
        System.out.println(n);
        return memo.get(n);
    }

    public static long fibBad(int n){
        if (n<=2){
            return 1;
        }
        System.out.println(n);
        return fibBad(n-1) + fibBad(n-2);
    }
}

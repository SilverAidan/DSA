import java.util.HashMap;

public class Dynamo {
    static HashMap<Integer, Long> memo = new HashMap<Integer, Long>();
    static HashMap<Integer, Integer> totals = new HashMap<Integer, Integer>();
    public static void main(String[] args) throws Exception {
        System.out.println(fib(8));
        int[] denoms = {1, 5, 10, 25};
        System.out.println(cointCount(denoms, 34));
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

    public static int cointCount(int[] denom, int total){
        if(total < 0){
           return Integer.MAX_VALUE;
        }
        if(total == 0){
            return 0;
        }
        if(totals.containsKey(total)){
            return totals.get(total);
        }
        int count = Integer.MAX_VALUE;
        for(int coin: denom){
            int result = cointCount(denom, total - coin);
            if(result != Integer.MAX_VALUE) {
                count = Math.min(count, result + 1);
            }
        }
        totals.put(total, count);
        return count;
    }
}

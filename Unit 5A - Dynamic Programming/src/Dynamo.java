import java.util.HashMap;

public class Dynamo {
    static HashMap<Integer, Long> memo = new HashMap<Integer, Long>();
    static HashMap<Integer, Integer> totals = new HashMap<Integer, Integer>();
    public static int knapDumb = 0;
    public static void main(String[] args) throws Exception {
        System.out.println(fib(8));
        int[] denoms = {1, 5, 10, 25};
        System.out.println(cointCount(denoms, 34));
        System.out.println(newton(4, 1000));
        double[] values = {100, 200, 50, 300, 400, 25};
        double[] weights = {3, 5, 10, 1, 2, 1};
        System.out.println(knapsack(values, weights, 10, 0, 0));
        System.out.println(knapDumb);
    }
    public static double knapSmart(double[] values, double[] weights, double capacity, int i, double max){
        double[][] memo = new double[weights.length][(int)capacity+1];
        for(int j = 0; j < weights.length; j++){
            for(int k = 0; k < capacity+1; k++){
                memo[j][k] = -1;
            }
        }
        return knapsackSmartH(values, weights, capacity, i, 0, memo)
    }
    public static double knapsackSmartH(double[] values, double[] weights, double capacity, int i, double max, double[][] memo){
        knapDumb++;
        if(i == values.length)
            return max;
        double with = 0;
        if(weights[i] <= capacity)
            with = knapsack(values, weights, capacity-weights[i], i+1, max+values[i]);
        double without = knapsack(values, weights, capacity, i+1, max);
        return Math.max(with, without);
    }

    public static double knapsack(double[] values, double[] weights, double capacity, int i, double max){
        knapDumb++;
        if(i == values.length)
            return max;
        double with = 0;
        if(weights[i] <= capacity)
            with = knapsack(values, weights, capacity-weights[i], i+1, max+values[i]);
        double without = knapsack(values, weights, capacity, i+1, max);
        return Math.max(with, without);
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

    public static double newton(double guess, int trials){
        if(trials == 0){
            return Double.POSITIVE_INFINITY;
        }
        if(Math.abs(function(guess)) < 0.0000001){
            return guess;
        }
        return newton(guess - function(guess)/derivative(guess), trials - 1);
    }

    private static double function(double x) {
        return x*x-4;
    }

    private static double derivative(double x) {
        return 2*x;
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

import java.util.HashMap;

public class Dynamo {
    static HashMap<Integer, Long> memo = new HashMap<Integer, Long>();
    static HashMap<Integer, Integer> totals = new HashMap<Integer, Integer>();
    public static int MCount = 0;
    public static int dumbDiceCount = 0;
    public static void main(String[] args) throws Exception {
        System.out.println(fibBad(8));
        int[] denoms = {1, 5, 10, 25};
        System.out.println(cointCount(denoms, 34));
        System.out.println(diceCountDumb(8, 24));
        System.out.println(dumbDiceCount);
        System.out.println("Memo "+ diceCountMemo(8, 24));
        System.out.println(MCount);

        int[][] diceTable = new int[9][25];
        for(int i = 0; i <=6; i++){
            diceTable[1][i] = 1;
        }
    }

    public static int diceCountDumb(int dice, int target){
        dumbDiceCount++;
        if(dice == 0 && target == 0){
            return 1;
        }
        if(dice<=0||target <= 0){
            return 0;
        }
        int count = 0;
        for(int i = 1; i <= 6; i++){
            count += diceCountDumb(dice - 1, target - i);
        }
        return count;
    }

    public static int diceCountMemo(int dice, int target){
        int[][] memo = new int[dice + 1][target + 1];
        return diceCountMemoHelper(dice, target, memo);
        
    }

    private static int diceCountMemoHelper(int dice, int target, int[][] memo) {
        MCount++;
        if(dice == 0 && target == 0){
            return 1;
        }
        if(dice<=0||target <= 0){
            return 0;
        }
        if(memo[dice][target] != 0){
            return memo[dice][target];
        }
        int count = 0;
        for(int i = 1; i <= 6; i++){
            count += diceCountMemoHelper(dice-1, target-i, memo);
        }
        memo[dice][target] = count;
        return count;
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

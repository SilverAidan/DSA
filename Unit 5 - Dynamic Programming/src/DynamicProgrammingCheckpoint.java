import java.util.ArrayList;
import java.util.HashMap;

public class DynamicProgrammingCheckpoint {
    static ArrayList<Integer> primes = new ArrayList<Integer>();
    static HashMap<Integer, Integer> collMap = new HashMap<Integer,Integer>();
    public static void main(String[] args) {
        System.out.println(longestCollatz(100000));
        System.out.println(primeN(1049));
    }

    public static long primeN(int n){
        if (n < 1) {
            return 0;
        }
        primes.add(2);
        int number = 3;
        while (primes.size() < n) {
            boolean isPrime = true;
            for (int prime : primes) {
                if (number % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(number);
            }
            number += 2;
        }
        return primes.get(n - 1);
    }

    public static int longestCollatz(int n){
        int maxLength = 0;
        int number = 1;
        for (int i = 1; i <= n; i++) {
            int length = 0;
            int current = i;
            while (current != 1) {
                if (collMap.containsKey(current)) {
                    length += collMap.get(current);
                    break;
                }
                if (current % 2 == 0) {
                    current /= 2;
                } else {
                    current = 3 * current + 1;
                }
                length++;
            }
            collMap.put(i, length);
            if (length > maxLength) {
                maxLength = length;
                number = i;
            }
        }
        return number;
    }
}

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class Chocula {
    int[] numbers;
    int max;

    public Chocula(int size, int max) {
        numbers = new int[size];
        this.max = max;
        for (int i = 0; i < size; i++) {
            numbers[i] = (int) (Math.random() * (max + 1));
        }
    }

    public String toString() {
        return Arrays.toString(numbers);
    }

    public void countSort() {
        int[] counts = new int[max+1];

        //fills counts with # of values for each #
        for (int i = 0; i < numbers.length; i++) {
            counts[numbers[i]]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i-1];
        }

        int[] newNums = new int[numbers.length];

        for (int i = numbers.length - 1; i >= 0; i--) {
            newNums[--counts[numbers[i]]] = numbers[i];
        }

        numbers = newNums;
    }
}

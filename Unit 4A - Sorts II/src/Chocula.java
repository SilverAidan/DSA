import java.util.Arrays;

public class Chocula {
    int numbers[]; int max;
    public Chocula(int size, int max) {
        numbers = new int[size];
        for(int i = 0; i<size; i++){
            numbers[i] = (int)(Math.random()* (max+1));
        }
    }

    public String toString(){
        return Arrays.toString(numbers);
    }

    public void countSort(){
        int[] counts = new int[max+1];
        for(int i = 0; i<numbers.length;i++){
            counts[numbers[i]]++;
        }
    }
}

import java.util.Arrays;

public class Quicky {
    int[] numbers;

    public Quicky(int count) {
        numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = (int)(Math.random() * count);
        }
    }

    public String toString() {
        return Arrays.toString(numbers);
    }

    public void quickSort() {
        quickSortR(0, numbers.length - 1);
    }

    private void quickSortR(int start, int end) {
        if (start >= end){
            return;
        } 
        int pivot = numbers[end];
        int marlin = start;
        for (int dory = start; dory < end; dory++) {
            if (numbers[dory] < pivot) {
                int temp = numbers[dory];
                numbers[dory] = numbers[marlin];
                numbers[marlin++] = temp;
            }
        }
        numbers[end] = numbers[marlin];
        numbers[marlin] = pivot;
        quickSortR(start, marlin-1);
        quickSortR(marlin+1, end);
    }

    public void Hoare(){
        HoareR(0, numbers.length-1);
    }

    public void HoareR(int start, int end){
        if(start>=end){
            return;
        }
        int pivot = numbers[(int)(Math.random()*(end=start+1))+start];
        int crush = start; int squirt = end;
        while(crush<squirt){
            while(numbers[crush]<=pivot)crush++;
            while(numbers[squirt] >= pivot)squirt--;
            if(crush<squirt){
                int temp = numbers[crush];
                numbers[crush] = numbers[squirt];
                numbers[squirt] = temp;
            }
        }
        HoareR(start, squirt); HoareR(crush, end);
    }
}


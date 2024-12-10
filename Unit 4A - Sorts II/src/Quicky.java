import java.util.Arrays;
import java.util.Scanner;

public class Quicky {
    int[] numbers;
    
    Scanner s = new Scanner(System.in);

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
        partition(0, numbers.length - 1);
    }

    private void partition(int i, int j) {
        if (i >= j) return;
        int randSpot = (int)(Math.random() * (j-i));
        
        //For now, we'll do it with the pivot as the last in the range
        int pivot = numbers[j];
        int p2 = i; //Slower pointer
        for (int p1 = i; p1 < j; p1++) { //Normal pointer
            if (numbers[p1] < pivot) {
                int temp = numbers[p1];
                numbers[p1] = numbers[p2];
                numbers[p2++] = temp;
            }
        }

        //Swap the pivot in
        numbers[j] = numbers[p2];
        numbers[p2] = pivot;
        partition(i, p2-1);
        partition(p2+1, j);
    }


    //k representing the kth smallest number
    public int quickSelect(int k) {
        return selectPartition(0, numbers.length - 1, k-1);
    }

    private int selectPartition(int i, int j, int k) {
        if (i > j) return -1;
        
        //For now, we'll do it with the pivot as the last in the range
        int pivot = numbers[j];
        int p2 = i; //Slower pointer
        int p1;
        for (p1 = i; p1 < j; p1++) { //Normal pointer
            if (numbers[p1] < pivot) {
                int temp = numbers[p1];
                numbers[p1] = numbers[p2];
                numbers[p2++] = temp;
            }
        }

        //Swap the pivot in
        numbers[j] = numbers[p2];
        numbers[p2] = pivot;

        if (p1 == k) { //
            return numbers[p1];
        }
        else if (p1 > k) { //If our value is to the right of k
            return selectPartition(i, p1-1, k); //Partition to left
        } else { //Otherwise, do the opposite
            return selectPartition(p1+1, j, k); //Partition to left
        } //We skip over p1 in order to avoid checking it twice

    }


    public void hoareSort() {
        hoarePartition(0,numbers.length - 1);
    }

    private void hoarePartition(int i, int j) {
        if (i >= j) {
            return;
        }
        //Pivot is random spot in range [i, j].
        int pivot = numbers[(int)(Math.random() * (j - i + 1))+ i];
        
        System.out.println(i + " - " + j + "; " + pivot + ": " + Arrays.toString(numbers));
        int p1 = i; int p2 = j;
        while (p1 < p2) {
            while (numbers[p1] < pivot) p1++;
            while(numbers[p2] > pivot) p2--;
            //p1--; p2++; //We go one past, so now we stop.
            if (p1 <= p2) {
                int temp = numbers[p1];
                numbers[p1] = numbers[p2];
                numbers[p2] = temp;
                p1++; p2--;
            }
        }
        String dunky = s.nextLine();
        System.out.println("YO");
        hoarePartition(i, p1);
        System.out.println("SUP");
        hoarePartition(p1, j);
        System.out.println("DONE :(");
        return;
    }
}

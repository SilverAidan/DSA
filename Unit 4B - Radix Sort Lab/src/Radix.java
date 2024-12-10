public class Radix {
    int[] numbers;
    int max;
    
    public void countSort(int digit) {
        int[] counts = new int[max+1];
        for (int i = 0; i < numbers.length; i++) {
            counts[getDigit(numbers[i],digit)]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i-1];
        }
        int[] newNums = new int[numbers.length];
        for (int i = numbers.length - 1; i >= 0; i--) {
            newNums[--counts[numbers[i]%10]] = numbers[i];
        }
        numbers = newNums;
    }

    public int getDigit(int num, int digit){
        String s = Integer.toString(num);
        return Integer.parseInt(s.substring(digit, digit+1));
    }
}
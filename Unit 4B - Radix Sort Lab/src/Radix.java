public class Radix {
    int[] numbers;
    int max;

    public void lsdRadixSort() {
        int maxDigits = getMaxDigits(); // Get the maximum number of digits
        for (int digit = 0; digit < maxDigits; digit++) {
            countSort(digit); // Sort the array by the current digit
        }
    }

    public void msdRadixSort() {
        int maxDigits = getMaxDigits();  // Get the number of digits in the largest number
        msdSortHelper(0, numbers.length - 1, maxDigits - 1);
    }

    private void msdSortHelper(int start, int end, int digitPlace) {
        if (start >= end || digitPlace < 0) {
            return; // Base case: single element or no more digits
        }
    
        int[] counts = new int[10]; // Assumes decimal system (0-9)
        for (int i = start; i <= end; i++) {
            counts[getDigit(numbers[i], digitPlace)]++;
        }
        
        int[] cumulativeCounts = new int[10];
        cumulativeCounts[0] = counts[0];
        for (int i = 1; i < 10; i++) {
            cumulativeCounts[i] = cumulativeCounts[i - 1] + counts[i];
        }
        
        // Create new array for placing elements in the correct bucket order
        int[] newNums = new int[end - start + 1];
        for (int i = end; i >= start; i--) {
            int digitValue = getDigit(numbers[i], digitPlace);
            newNums[--cumulativeCounts[digitValue]] = numbers[i];
        }
    
        // Copy sorted elements back to the original array
        for (int i = 0; i < newNums.length; i++) {
            numbers[start + i] = newNums[i];
        }
    
        // Recurse on each bucket of the sorted digits
        for (int i = 0; i < 10; i++) {
            // Find the start and end indices for the current bucket
            int bucketStart = (i == 0) ? start : cumulativeCounts[i - 1];
            int bucketEnd = (i == 9) ? end : cumulativeCounts[i] - 1;
        
            // Recurse on the bucket if it has more than one element
            if (bucketStart < bucketEnd) {
                msdSortHelper(bucketStart, bucketEnd, digitPlace - 1);
            }
        }
    }

    public void countSort(int digit) {
        int[] counts = new int[max + 1];
        for (int i = 0; i < numbers.length; i++) {
            counts[getDigit(numbers[i], digit)]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        int[] newNums = new int[numbers.length];
        for (int i = numbers.length - 1; i >= 0; i--) {
            newNums[--counts[getDigit(numbers[i], digit)]] = numbers[i];
        }
        numbers = newNums;
    }

    // Utility method to find the number of digits in the largest number
    private int getMaxDigits() {
        int maxDigits = 0;
        for (int num : numbers) {
            maxDigits = Math.max(maxDigits, Integer.toString(num).length());
        }
        return maxDigits;
    }

    // Get digit method (already provided)
    public int getDigit(int num, int digit) {
        String s = Integer.toString(num);
        if (digit >= s.length()) {
            return 0; // Return 0 if digit index exceeds the number's length
        }
        return Integer.parseInt(s.substring(s.length() - digit - 1, s.length() - digit));
    }
}

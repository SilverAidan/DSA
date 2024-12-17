public class Radix {

    public static void lsdRadixSort(Piece[] pieces) {
        int maxDigits = getMaxDigits(pieces); // Get the maximum number of digits
        for (int digit = 0; digit < maxDigits; digit++) {
            countSort(pieces, digit); // Sort the array by the current digit
        }
    }

    public static void msdRadixSort(Piece[] pieces) {
        int maxDigits = getMaxDigits(pieces);  // Get the number of digits in the largest number
        msdSortHelper(pieces, 0, pieces.length - 1, maxDigits - 1);
    }

    private static void msdSortHelper(Piece[] pieces, int start, int end, int digitPlace) {
        if (start >= end || digitPlace < 0) {
            return; // Base case: single element or no more digits
        }
    
        int[] counts = new int[10]; // Assumes decimal system (0-9)
        for (int i = start; i <= end; i++) {
            counts[getDigit(pieces[i], digitPlace)]++;
        }
        
        int[] cumulativeCounts = new int[10];
        cumulativeCounts[0] = counts[0];
        for (int i = 1; i < 10; i++) {
            cumulativeCounts[i] = cumulativeCounts[i - 1] + counts[i];
        }
        
        // Create new array for placing elements in the correct bucket order
        Piece[] newPieces = new Piece[end - start + 1];
        for (int i = end; i >= start; i--) {
            int digitValue = getDigit(pieces[i], digitPlace);
            newPieces[--cumulativeCounts[digitValue]] = pieces[i];
        }
    
        // Copy sorted elements back to the original array
        for (int i = 0; i < newPieces.length; i++) {
            pieces[start + i] = newPieces[i];
        }
    
        // Recurse on each bucket of the sorted digits
        for (int i = 0; i < 10; i++) {
            // Find the start and end indices for the current bucket
            int bucketStart = (i == 0) ? start : cumulativeCounts[i - 1];
            int bucketEnd = (i == 9) ? end : cumulativeCounts[i] - 1;
        
            // Recurse on the bucket if it has more than one element
            if (bucketStart < bucketEnd) {
                msdSortHelper(pieces, bucketStart, bucketEnd, digitPlace - 1);
            }
        }
    }

    public static void countSort(Piece[] pieces, int digit) {
        int[] counts = new int[10];
        for (int i = 0; i < pieces.length; i++) {
            counts[getDigit(pieces[i], digit)]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        Piece[] newPieces = new Piece[pieces.length];
        for (int i = pieces.length - 1; i >= 0; i--) {
            newPieces[--counts[getDigit(pieces[i], digit)]] = pieces[i];
        }
        // Copy sorted pieces back to the original array
        System.arraycopy(newPieces, 0, pieces, 0, pieces.length);
    }

    // Utility method to find the number of digits in the largest number
    private static int getMaxDigits(Piece[] pieces) {
        int maxDigits = 0;
        for (Piece piece : pieces) {
            maxDigits = Math.max(maxDigits, Integer.toString(piece.pinkValue).length());
        }
        return maxDigits;
    }

    // Get digit method (already provided)
    private static int getDigit(Piece piece, int digit) {
        String s = Integer.toString(piece.pinkValue);
        if (digit >= s.length()) {
            return 0; // Return 0 if digit index exceeds the number's length
        }
        return Integer.parseInt(s.substring(s.length() - digit - 1, s.length() - digit));
    }
}

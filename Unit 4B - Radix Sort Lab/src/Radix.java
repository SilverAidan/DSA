public class Radix {
    public static void lsdRadixSort(Piece[] pieces, Panel p) throws InterruptedException {
        int maxDigits = getMaxDigits(pieces); // Get the maximum number of digits
        for (int digit = 0; digit < maxDigits; digit++) {
            countSort(pieces, digit, p); // Sort the array by the current digit
        }
        p.drawSortedKirbies(pieces);
    }

    public static void msdRadixSort(Piece[] pieces, Panel panel) throws InterruptedException {
        int maxDigits = getMaxDigits(pieces);
        msdSortHelper(pieces, 0, pieces.length - 1, maxDigits - 1, panel);
        panel.drawSortedKirbies(pieces);
    }

    private static void msdSortHelper(Piece[] pieces, int start, int end, int digitPlace, Panel panel) throws InterruptedException {
        if (start >= end || digitPlace < 0) {
            return;
        }
    
        int[] counts = new int[10];
        // Add animations for counting phase
        for (int i = start; i <= end; i++) {
            int digitValue = getDigit(pieces[i], digitPlace);
            panel.animatePieceToBox(pieces[i], digitValue);
            counts[digitValue]++;
            System.arraycopy(counts, 0, Panel.currentCounts, 0, counts.length);
            panel.repaint();
        }
        
        int[] cumulativeCounts = new int[10];
        cumulativeCounts[0] = counts[0];
        for (int i = 1; i < 10; i++) {
            cumulativeCounts[i] = cumulativeCounts[i - 1] + counts[i];
            System.arraycopy(cumulativeCounts, 0, Panel.currentCounts, 0, counts.length);
            panel.repaint();
            Thread.sleep(500/RadixDriver.slide);
        }
        
        Piece[] newPieces = new Piece[end - start + 1];
        for (int i = end; i >= start; i--) {
            int digitValue = getDigit(pieces[i], digitPlace);
            newPieces[--cumulativeCounts[digitValue]] = pieces[i];
            System.arraycopy(cumulativeCounts, 0, Panel.currentCounts, 0, counts.length);
            panel.repaint();
            Thread.sleep(500/RadixDriver.slide);
        }
    
        for (int i = 0; i < newPieces.length; i++) {
            pieces[start + i] = newPieces[i];
        }
        panel.repaint();
        Thread.sleep(500/RadixDriver.slide);
    
        for (int i = 0; i < 10; i++) {
            int bucketStart = (i == 0) ? start : cumulativeCounts[i - 1];
            int bucketEnd = (i == 9) ? end : cumulativeCounts[i] - 1;
            if (bucketStart < bucketEnd) {
                msdSortHelper(pieces, bucketStart, bucketEnd, digitPlace - 1, panel);
            }
        }
    }

    public static void countSort(Piece[] pieces, int digit, Panel panel) throws InterruptedException {
        int[] counts = new int[10];
        
        // Count occurrences with animation
        for (int i = 0; i < pieces.length; i++) {
            int digitValue = getDigit(pieces[i], digit);
            panel.animatePieceToBox(pieces[i], digitValue);
            counts[digitValue]++;
            System.arraycopy(counts, 0, Panel.currentCounts, 0, counts.length);
            panel.repaint();
        }
        
        // Create separate array for cumulative counts
        int[] cumulative = new int[10];
        cumulative[0] = counts[0];
        
        // Calculate and display cumulative counts
        for (int i = 1; i < counts.length; i++) {
            cumulative[i] = cumulative[i-1] + counts[i];
            System.arraycopy(cumulative, 0, Panel.currentCounts, 0, counts.length);
            panel.repaint();
            Thread.sleep(500/RadixDriver.slide);
        }

        Piece[] newPieces = new Piece[pieces.length];
        for (int i = pieces.length - 1; i >= 0; i--) {
            int digit_val = getDigit(pieces[i], digit);
            newPieces[--cumulative[digit_val]] = pieces[i];
            // Show the decremented cumulative counts
            System.arraycopy(cumulative, 0, Panel.currentCounts, 0, counts.length);
            panel.repaint();
            Thread.sleep(500/RadixDriver.slide);
        }
        
        System.arraycopy(newPieces, 0, pieces, 0, pieces.length);
        panel.repaint();
        Thread.sleep(500/RadixDriver.slide);
    }

    // Utility method to find the number of digits in the largest number
    private static int getMaxDigits(Piece[] pieces) {
        int maxDigits = 0;
        for (Piece piece : pieces) {
            maxDigits = Math.max(maxDigits, Integer.toString(piece.pinkValue).length());
        }
        return maxDigits;
    }

    // Get digit method 
    private static int getDigit(Piece piece, int digit) {
        String s = Integer.toString(piece.pinkValue);
        if (digit >= s.length()) {
            return 0; // Return 0 if digit index exceeds the number's length
        }
        return Integer.parseInt(s.substring(s.length() - digit - 1, s.length() - digit));
    }
}
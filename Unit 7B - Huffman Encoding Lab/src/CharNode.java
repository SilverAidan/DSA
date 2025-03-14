public class CharNode implements Comparable<CharNode> {
    private char value;
    private int frequency;
    private CharNode left, right;

    // Constructor to initialize CharNode with a character and its frequency
    public CharNode(char value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    // Method to compare two CharNode objects based on their frequency and value
    @Override
    public int compareTo(CharNode o) {
        if (this.frequency == o.frequency) {
            if (this.value == (char) 0 && o.value == (char) 0) {
                return 1;
            }
            if (this.value == (char) 0) {
                return 1;
            }
            if (o.value == (char) 0) {
                return -1;
            }
            return Character.compare(this.value, o.value);
        }
        return Integer.compare(this.frequency, o.frequency);
    }

    // Getter for value
    public char getValue() {
        return value;
    }

    // Setter for value
    public void setValue(char value) {
        this.value = value;
    }

    // Getter for frequency
    public int getFrequency() {
        return frequency;
    }

    // Setter for frequency
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    // Getter for left child node
    public CharNode getLeft() {
        return left;
    }

    // Setter for left child node
    public void setLeft(CharNode left) {
        this.left = left;
    }

    // Getter for right child node
    public CharNode getRight() {
        return right;
    }

    // Setter for right child node
    public void setRight(CharNode right) {
        this.right = right;
    }

    // Method to return a string representation of the CharNode
    @Override
    public String toString() {
        return value + " " + frequency;
    }
}

public class CharNode implements Comparable<CharNode>{
    private char value;
    private int frequency;
    private CharNode left, right;
    
    public CharNode(char value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

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

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public CharNode getLeft() {
        return left;
    }

    public void setLeft(CharNode left) {
        this.left = left;
    }

    public CharNode getRight() {
        return right;
    }

    public void setRight(CharNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return value + " " + frequency;
    }

    
}

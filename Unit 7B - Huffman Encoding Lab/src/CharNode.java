public class CharNode implements Comparable<CharNode>{
    private char value;
    private int frequency;
    private CharNode left, right;

    @Override
    public int compareTo(CharNode o) {
        if(this.frequency > o.frequency)
            return frequency;
        if(this.frequency < o.frequency)
            return o.frequency;
        if(this.frequency == o.frequency)
            //LETTER STUFF;

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
}

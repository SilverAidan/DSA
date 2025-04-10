import java.util.Objects;

public class Island {
    private int x, y, value; 

    public Island(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Island island = (Island) obj;
        return x == island.x && y == island.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString(){
        return String.valueOf(value) + " at " + x + "," + y; 
    }
}

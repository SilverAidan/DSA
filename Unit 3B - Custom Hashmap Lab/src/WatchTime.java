public class WatchTime {
    private long time;

    public WatchTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "" + time;
    }
}
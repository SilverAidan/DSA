public class FlashEntry{
    Streamer key;
    WatchTime value;
    FlashEntry next;
    
    public FlashEntry(Streamer key, WatchTime value, FlashEntry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

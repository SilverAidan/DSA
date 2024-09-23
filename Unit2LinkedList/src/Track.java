public class Track {
    String title;
    String Artist;
    int duration;
    Track next;
    
    public Track(String title, String artist, int duration, Track next) {
        this.title = title;
        Artist = artist;
        this.duration = duration;
        this.next = next;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Track)){
            return false;
        }
        Track t = (Track)(obj);
        return this.Artist.equals(t.Artist)&&this.title.equals(t.title);
    }

}

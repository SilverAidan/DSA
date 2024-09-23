import java.util.LinkedList;

public class Link {
    public static void main(String[] args){
        LinkedList<String> presidents = new LinkedList<>();
        //can you make a linkedlist out of a linked list? check later
        presidents.add("George Washington");
        presidents.add("John Adams");
        presidents.add("Thomas Jefferson");
        presidents.add("James Madison");
        for(String s:presidents){
                System.out.println(s);
        }
        presidents.add(4, "James Monroe");
        for(String s:presidents){
            System.out.println(s);
        }
        LinkedList<String> more = new LinkedList<String>(presidents);
        for(String s:more){
            System.out.println(s);
        }
        System.out.println(more == presidents);
        System.out.println(more.get(0) == presidents.get(0));

        Track t1 = new Track("Yellow Submarine", "Beatles", 160, null);
        Track t2 = new Track("Northern Song", "Beatles", 208, null);
        Track t3 = new Track("All Together Now", "Beatles", 134, null); 
        Track t4 = new Track("Hey Bulldog", "Beatles", 160, null);
        Playlist Yellow = new Playlist(t1);
        Yellow.addTrack(t3);
        System.out.println("blah");
        Yellow.addAfterTrack(t1, t2);
        Yellow.addAfterTrack(t3, t4);
        System.out.println(Yellow);
    }
}

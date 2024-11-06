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
        System.out.println("blah\n");
        Yellow.addAfterTrack(t1, t2);
        Yellow.addAfterTrack(t3, t4);

        System.out.println("Full Playlist");
        System.out.println(Yellow);

        System.out.println("Playing on repeat");
        for(int i = 0; i<10; i++){
            System.out.println(Yellow.playSong());
        }
        System.out.println("REMOVING TESTING");
        Yellow.remove(t3);
        System.out.println(Yellow);
        Yellow.remove(t4);
        System.out.println(Yellow.playSong());//SHOULD BE THE INTRO
        Yellow.remove(t1);//Remove the first song
        System.out.println();
        System.out.println(Yellow);
        Yellow.remove(t2);
        System.out.println(Yellow.playSong() + "\n");

        Yellow.addTrack(new Track("Yellow Submarine", "Beatles", 160, null));
        Yellow.addTrack(new Track("Northern Song", "Beatles", 208, null));
        Yellow.addTrack(new Track("All Together Now", "Beatles", 134, null));
        Yellow.addTrack(new Track("Hey Bulldog", "Beatles", 160, null));
        System.out.println(Yellow);
        //back to OG
        Yellow.shuffle();
        System.out.println(Yellow);
    }
}

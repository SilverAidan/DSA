public class Playlist {
    Track intro;
    Track current;

    public Playlist(){
        intro = new Track("Intro", "Link", 60, null);
    }

    public Playlist(Track i){
        intro = i; current = i;
    }

    public void addTrack(Track t){
        Track runner = current;
        if(current == null){
            intro = t; current = t;
            return;
        }

        while(runner.next != null){
            runner = runner.next;
        }
        runner.next = t;
    }

    public boolean addAfterTrack(Track target, Track newTrack){
        if(intro == null){
            return false;
        }
        Track runner = intro;
        while(runner != null && !runner.equals(target)){
            runner = runner.next;
        }
        if(runner == null){
            return false;
        }

        newTrack.next = runner.next;
        runner.next = newTrack;
        return true;
    }

    public String toString(){
        String output = "";
        if(intro == null){
            return "BUY SOME SONGS YOU CHEAP SON OF A...";
        }
        Track runner = intro;
        while(runner != null){
            output += runner.title + ", " + runner.Artist + ":" + runner.duration + "\n";
            runner = runner.next;
        }
        return output;
    }
}
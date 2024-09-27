public class Playlist {
    Track intro;
    Track current;

    public Playlist(){
        intro = new Track("Intro", "Link", 60, null);
    }

    public Playlist(Track i){
        this();
        if(i!=null){
            intro = i; current = i;
        }
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

    public Track remove(){
        return remove(current); //HOLD ON
    }

    public Track remove(Track t){
        if(t==null || intro == null)
            return null;      
        Track runner = intro;
        if(t.equals(runner)){
            intro = intro.next;
            if(current.equals(runner))
                current = current.next==null?intro:current.next;
            t.next = null;
            return t;
        }
        while(runner.next != null){
            if(runner.next.equals(t))
                break;
            runner = runner.next;
        }
        if(runner.next == null)
            return null;  
        runner.next = runner.next.next;
        if(current.equals(t))
            current = current.next==null?intro:current.next; 
        t.next = null;
        return t;
    }

    public String playSong(){
        if(current ==  null){
            return "BUY SOME SONGS YOU CHEAP SON OF A...";
        }
        String output = current.title + ", " + current.Artist + ": " + current.duration;
        current = current.next;
        if(current == null){
            current = intro;
        }
        return output;
    }

    public void shuffle(){
        if(intro == null){
            return;
        }
        Track runner = intro; 
        int size = 0;
        while(runner != null){
            runner = runner.next;
            size++;
        }
        for(int i = 0; i < size; i++){
            int rando = (int)(Math.random()*size);
            runner = intro;
            for(int j = 0; j < rando; j++){
                runner = runner.next;
            }
            addTrack(remove(runner));
        }
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

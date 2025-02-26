import java.util.ArrayList;

public class Triage {
    ArrayList<Injury> injuries;

    public Triage(ArrayList<Injury> inj){
        injuries = inj;
    }

    public Injury findMin(){
        return injuries.isEmpty() ? injuries.get(0) : null;
    }

    public Injury getLeft(int i){
        int spot = 2*i+1;
        return spot<injuries.size() ? injuries.get(spot) : null;
    }

    public Injury getLeft(Injury inj){
        int spot = injuries.indexOf(inj);
        return spot > -1 ? this.getLeft(spot) : null;
    }

    public Injury getRight(int i){
        int spot = i+2;
        return spot<injuries.size() ? injuries.get(spot) : null;
    }

    public Injury getRight(Injury inj){
        int spot = injuries.indexOf(inj);
        return spot > -1 ? this.getRight(spot) : null;
    }

    public Injury getParent(int i){
        int spot = (i-1)/2;
        return i>0 ? injuries.get(spot) : null;
    }

    public Injury getParent(Injury inj){
        int spot = injuries.indexOf(inj);
        return spot >-1 ? this.getParent(spot) : null;
    }
}

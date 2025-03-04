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
        return this.getParent(spot);
    }
    
    public void swap(int i, int j){
        if(i<0||j<0||i>=injuries.size()||j>=injuries.size()){
            return;
        }
        Injury temp = injuries.get(i);
        injuries.set(i, injuries.get(j));
        injuries.set(j, temp);
    }

    public void upHeap(int i){
        while(i>0&&injuries.get(i).compareTo(this.getParent(i))<0){
            swap(i,(i-1)/2);
        }
    }

    public void add(Injury inj){
        injuries.add(inj);
        this.upHeap(injuries.size()-1);
    }

    public Injury treat(){
        swap(0,injuries.size()-1);
    }

    @Override
    public String toString() {
        String output = "";
        int levels = (int) (Math.log(injuries.size())/Math.log(2));
        for(int i = 0; i <= levels; i++){
            int start = (int)(Math.pow(2, i)-1);
            int end = Math.min(injuries.size(),(int)(Math.pow(2,i+1)-1));
            for(int j = start; j < end; j++){
                output += injuries.get(j) + "|";
            }
            output += "\n";
        }
        return output;
    }

    public void downHeap(int i){
        if(this.getLeft(i)==null&&this.getRight(i)==null){
            return;
        }
        if(this.getRight(i)==null){
            if(this.getLeft(i).compareTo(injuries.get(i))<0){
                swap(i,2*i+1);
                downHeap(2*i+1);
            }
            return;
        }
        Injury left = this.getLeft(i);
        Injury right = this.getRight(i);
        int smaller = left.compareTo(right)<0?2*i+1:2*i+2;
        if(injuries.get(i).compareTo(injuries.get(smaller))>0){
            swap(i, smaller);
            downHeap(smaller);
        }
    }
}

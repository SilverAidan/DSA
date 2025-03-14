import java.util.ArrayList;
import java.util.Arrays;

public class Triage {
    ArrayList<Injury> injuries;

    public Triage(ArrayList<Injury> inj){
        injuries = inj;
    }

    public Triage(Injury[] unPQ, boolean isMin){
        injuries = new ArrayList<Injury>(Arrays.asList(unPQ));
        for(int i = injuries.size()/2-1; i >-1; i--){
            if(isMin){
                downHeap(i);
            }
            else{
                downHeapMax(i);
            }
            
        }
    }
    
    public void heapSort(){
        ArrayList<Injury> temp = new ArrayList<>();
        
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
        int spot = 2*i+2;
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

    public boolean setPriority(int i, int newP) {
        if (i < 0||i >= injuries.size()||injuries.get(i).priority == newP) 
            return false;
        if (newP<injuries.get(i).priority) {
            injuries.get(i).priority = newP;
            upHeap(i);
        } else {
            injuries.get(i).priority = newP;
            downHeap(i);
        }
        return true;
    }        

    public Injury treat() {
        swap(0, injuries.size() - 1);
        Injury treated = injuries.remove(injuries.size() - 1);
        downHeap(0);
        return treated;
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
        int smaller = left.compareTo(right) < 0 ? 2*i+1 : 2*i+2;
        if(injuries.get(i).compareTo(injuries.get(smaller))>0){
            if (injuries.get(i).compareTo(injuries.get(smaller)) > 0) {
                swap(i, smaller);
                downHeap(smaller);
            }
        }
    }
}

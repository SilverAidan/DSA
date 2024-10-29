import java.util.ArrayList;

public class ConcurrentError {
    public static void main(String[] args) {
        ArrayList<Integer> arrL = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            arrL.add(i);
        }
        for(Integer i : arrL){
            if(i%2==0){
                arrL.remove(i);
            }
        }

    }
}

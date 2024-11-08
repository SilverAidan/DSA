import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) throws Exception {
        FlashMap stream = new FlashMap();
        Scanner inputFile = new Scanner(new File("stats.csv"));
        inputFile.nextLine();

        Scanner singleLine = new Scanner(inputFile.nextLine());
        singleLine.useDelimiter(",");
        Streamer s;
        WatchTime w;
        FlashEntry f;
        while(singleLine.next() != null){
            s = new Streamer(singleLine.next());
            w = new WatchTime(Integer.valueOf(singleLine.next()).intValue());
            f = new FlashEntry(s, w, null);
            stream.put(f);
        }
        System.out.println(stream);
        singleLine.close();
        inputFile.close();
    }
}

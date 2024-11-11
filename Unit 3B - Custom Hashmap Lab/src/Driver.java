import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) throws Exception {
        FlashMap stream = new FlashMap();
        Scanner inputFile = new Scanner(new File("stats.csv"));
        inputFile.nextLine();
        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();
            Scanner singleLine = new Scanner(line);
            singleLine.useDelimiter(",");
            Streamer s;
            WatchTime w;
            FlashEntry f;
            if (singleLine.hasNext()) {
                s = new Streamer(singleLine.next());
                if (singleLine.hasNextLong()) {
                    w = new WatchTime(singleLine.nextLong());
                    f = new FlashEntry(s, w, null);
                    stream.put(f);
                }
            }
            singleLine.close();
        }
        System.out.println(stream);
        Streamer xqc = new Streamer("xQcOW");
        System.out.println(stream.get(xqc));
        inputFile.close();
    }
}

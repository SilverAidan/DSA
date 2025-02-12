import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AutoComplete {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("dota.txt"));
        Dictionary bruh = load_txt(new File("dota.txt"));
    }

    public static Dictionary load_txt(File file) throws FileNotFoundException{
        TNode self = new TNode();
        Scanner scanner = new Scanner(file);
        scanner.tokens().forEach((t)->{
            self.addKey(t);
        });
        scanner.close();
        return new Dictionary(self);
    }

    public static Dictionary load_csv(File file) throws FileNotFoundException{
        TNode self = new TNode();
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        scanner.tokens().forEach((t)->{
            self.addKey(t);
        });
        scanner.close();
        return new Dictionary(self);
    }
}
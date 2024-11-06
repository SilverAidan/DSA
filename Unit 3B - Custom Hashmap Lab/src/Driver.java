import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String[] args) throws Exception {
        Scanner inputFile = new Scanner(new File("stats.csv"));
        System.out.println(inputFile.nextLine());

        Scanner singleLine = new Scanner(inputFile.nextLine());
        singleLine.useDelimiter(",");
        System.out.println(singleLine.next());
        singleLine.close();
        inputFile.close();
    }
}

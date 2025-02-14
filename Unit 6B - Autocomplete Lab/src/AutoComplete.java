import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class AutoComplete {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputScanner = new Scanner(System.in);  
        System.out.print("Enter the name of the txt file to search for: ");
        String fileName = inputScanner.nextLine();
        File file = new File(fileName);
        
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            inputScanner.close();
            return;
        } else {
            System.out.println("File found: " + fileName);
        }

        Dictionary dictionary = load_txt(file);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1: Add key");
            System.out.println("2: Delete key");
            System.out.println("3: AutoComplete");
            System.out.println("4: Compress");
            System.out.println("5: Print tree");
            System.out.println("Else: Exit");

            int choice = inputScanner.nextInt();
            inputScanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter key to add: ");
                    String addKey = inputScanner.nextLine();
                    dictionary.addKey(addKey);
                    break;
                case 2:
                    System.out.print("Enter key to delete: ");
                    String deleteKey = inputScanner.nextLine();
                    dictionary.deleteKey(deleteKey);
                    break;
                case 3:
                    System.out.print("Enter prefix for autocomplete (Caps Sensitive): ");
                    String prefix = inputScanner.nextLine();
                    HashMap<String, TNode> completions = dictionary.auto(prefix);
                    if (completions == null || completions.isEmpty()) {
                        System.out.println("No such word");
                    } else {
                        System.out.println("Possible completions: " + completions.keySet());
                    }
                    inputScanner.close();
                    return;
                case 4:
                    System.out.print("Enter a word to compress to a single node: ");
                    String word = inputScanner.nextLine();
                    dictionary.compress(word);
                    System.out.println("Word compressed.");
                    break;
                case 5:
                    System.out.println(dictionary);
                    inputScanner.close();
                    return;
                default:
                    System.out.println("Exiting...");
                    inputScanner.close();
                    return;
            }
        }
    }

    //MASONS CODE
    public static Dictionary load_txt(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String[] words = scanner.tokens().toArray(String[]::new);
        scanner.close();
        return new Dictionary(words);
    }

    //MASONS CODE
    public static Dictionary load_csv(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        String[] words = scanner.tokens().toArray(String[]::new);
        scanner.close();
        return new Dictionary(words);
    }
}

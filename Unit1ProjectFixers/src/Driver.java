import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner n = new Scanner(System.in);
		System.out.println("Enter an infix expression:\n");
		String infix;
		infix = n.next();
		Fixers fix = new Fixers(infix);
	}

}

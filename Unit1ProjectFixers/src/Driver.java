import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner n = new Scanner(System.in);
		System.out.print("Enter an infix expression: ");
		String infix;
		infix = n.next();
		Fixers fix = new Fixers(infix);
		System.out.println(fix);
		n.close();
	}
}

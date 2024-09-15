import java.util.Scanner;
import java.util.Stack;

public class Fixers {

    String postfix = "";
    String prefix = "";

    double preEvaled = -1;
    double postEvaled = -1;

    public Fixers(String infix, Scanner n) {
        postfix = postConvert(infix);
        prefix = preConvert(postfix);
        System.out.print("Evaluate (1) Prefix or (2) Postfix: ");
		int answer = n.nextInt();
		if (answer == 1) {
			preEvaled = evalPre(prefix);
		} else if (answer == 2) {
			postEvaled = evalPost(postfix);
		}
    }

    public String postConvert(String infix) {
        String output = "";
        String operands = "1234567890";
        Stack<Character> temp = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (c == '(') {
                temp.push(c);
            } else if (operands.indexOf(c) != -1) {
                output += c;
            } else if (c == ')') {
                while (!temp.isEmpty() && temp.peek() != '(') {
                    output += temp.pop();
                }
                if (!temp.isEmpty() && temp.peek() == '(') {
                    temp.pop(); 
                }
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                while (!temp.isEmpty() && opOrder(temp.peek()) >= opOrder(c)) {
                    output += temp.pop();
                }
                temp.push(c);
            }
        }
        while (!temp.isEmpty()) {
            output += temp.pop();
        }
        return output;
    }

    public String preConvert(String postfix) {
        String operands = "1234567890";
        Stack<String> temp = new Stack<>();
        
        for (char c : postfix.toCharArray()) {
            if (operands.indexOf(c) != -1) {
                temp.push("" + c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                String right = temp.pop();
                String left = temp.pop();
                temp.push(c + left + right);
            }
        }
        return temp.pop();
    }

    public double evalPost(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c - '0');
                System.out.println("Pushed operand to stack: " + (c - '0'));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                System.out.println("Performing operation: " + num2 + " " + c + " " + num1);
                switch (c) {
                    case '+':
                        stack.push(num2 + num1);
                        break;
                    case '-':
                        stack.push(num2 - num1);
                        break;
                    case '/':
                        stack.push(num2 / num1);
                        break;
                    case '*':
                        stack.push(num2 * num1);
                        break;
                }
            }
            System.out.println("Current Stack (in evalPost): " + stack);
        }
        return stack.pop();
    }

    static double evalPre(String prefix) {
        Stack<Double> stack = new Stack<>();
        for (int j = prefix.length() - 1; j >= 0; j--) {
            if (isOperand(prefix.charAt(j))) {
                stack.push((double)(prefix.charAt(j) - 48));
                System.out.println("Pushed operand to stack: " + (prefix.charAt(j) - 48));
            } else {
                double o1 = stack.pop();
                double o2 = stack.pop();
                System.out.println("Performing operation: " + o1 + " " + prefix.charAt(j) + " " + o2);
                switch (prefix.charAt(j)) {
                    case '+':
                        stack.push(o1 + o2);
                        break;
                    case '-':
                        stack.push(o1 - o2);
                        break;
                    case '*':
                        stack.push(o1 * o2);
                        break;
                    case '/':
                        stack.push(o1 / o2);
                        break;
                }
            }
            System.out.println("Current Stack (in evalPre): " + stack);
        }
        return stack.peek();
    }

    static Boolean isOperand(char c) {
        return (c >= 48 && c <= 57);
    }

    private int opOrder(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3; 
            default:
                return -1;
        }
    }

    public String toString() {
        String output = "Postfix: " +  postfix + "\n" + "Prefix: " + prefix;
        if (preEvaled == -1) {
            output += "\nPostfix Solution: " + postEvaled;
            return output;
        }
        if (postEvaled == -1) {
            output += "\nPrefix Solution: " + preEvaled;
            return output;
        }
        return output;
    }
}

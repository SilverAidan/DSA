import java.util.Scanner;
import java.util.Stack;

public class Fixers {
    // Stack to store operations (though it's not used in the current implementation)
    Stack<String> operations = new Stack<String>();

    // Strings to hold postfix and prefix expressions
    String postfix = "";
    String prefix = "";

    // Variables to hold evaluated values of prefix and postfix expressions
    double preEvaled = -1;
    double postEvaled = -1;

    // Constructor that accepts an infix expression and a Scanner object
    public Fixers(String infix, Scanner n) {
        // Convert infix to postfix and prefix
        postfix = postConvert(infix);
        prefix = preConvert(postfix);
        
        // Ask user to choose between evaluating prefix or postfix
        System.out.print("Evaluate (1) Prefix or (2) Postfix: ");
        int answer = n.nextInt();
        
        // Evaluate based on the user's choice
        if (answer == 1) {
            preEvaled = evalPre(prefix);
        } else if (answer == 2) {
            postEvaled = evalPost(postfix);
        }
    }

    // Method to convert infix expression to postfix
    public String postConvert(String infix) {
        String output = "";
        String operands = "1234567890"; // Define valid operands (digits)
        Stack<Character> temp = new Stack<>(); // Temporary stack for operators

        // Loop through each character in the infix expression
        for (char c : infix.toCharArray()) {
            if (c == '(') { // If it's an opening parenthesis, push to stack
                temp.push(c);
            } else if (operands.indexOf(c) != -1) { // If it's an operand, add to output
                output += c;
            } else if (c == ')') { // If it's a closing parenthesis
                // Pop all operators until opening parenthesis is found
                while (!temp.isEmpty() && temp.peek() != '(') {
                    output += temp.pop();
                }
                if (!temp.isEmpty() && temp.peek() == '(') {
                    temp.pop(); // Pop the opening parenthesis
                }
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') { // If it's an operator
                // Pop operators with higher or equal precedence
                while (!temp.isEmpty() && opOrder(temp.peek()) >= opOrder(c)) {
                    output += temp.pop();
                }
                // Push the current operator to stack
                temp.push(c);
            }
        }

        // Pop any remaining operators in the stack
        while (!temp.isEmpty()) {
            output += temp.pop();
        }

        return output; // Return the postfix expression
    }

    // Method to convert postfix expression to prefix
    public String preConvert(String postfix) {
        String operands = "1234567890"; // Define valid operands (digits)
        Stack<String> temp = new Stack<>(); // Temporary stack for the conversion

        // Loop through each character in the postfix expression
        for (char c : postfix.toCharArray()) {
            if (operands.indexOf(c) != -1) { // If it's an operand, push to stack
                temp.push("" + c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') { // If it's an operator
                // Pop two operands from the stack
                String right = temp.pop();
                String left = temp.pop();
                // Push the operator followed by the operands to form a prefix expression
                temp.push(c + left + right);
            }
        }

        return temp.pop(); // Return the prefix expression
    }

    // Method to evaluate a postfix expression
    public double evalPost(String postfix) {
        Stack<Integer> stack = new Stack<>(); // Stack to hold operands
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (Character.isDigit(c)) { // If it's a digit, push to stack
                stack.push(c - '0');
                System.out.println("Pushed operand to stack: " + (c - '0'));
            } else { // If it's an operator, pop two operands and apply the operator
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
        return stack.pop(); // Return the final result
    }

    // Static method to evaluate a prefix expression
    static double evalPre(String prefix) {
        Stack<Double> stack = new Stack<>(); // Stack to hold operands
        // Loop through the prefix expression from right to left
        for (int j = prefix.length() - 1; j >= 0; j--) {
            if (isOperand(prefix.charAt(j))) { // If it's an operand, push to stack
                stack.push((double)(prefix.charAt(j) - 48));
                System.out.println("Pushed operand to stack: " + (prefix.charAt(j) - 48));
            } else { // If it's an operator, pop two operands and apply the operator
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
        return stack.peek(); // Return the final result
    }

    // Helper method to check if a character is an operand
    static Boolean isOperand(char c) {
        return (c >= 48 && c <= 57); // ASCII values of '0' to '9'
    }

    // Method to return the precedence of operators
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
                return -1; // Return -1 for any invalid operators
        }
    }

    // Method to return the string representation of the object
    public String toString() {
        // Prepare the output with postfix and prefix expressions
        String output = "Postfix: " + postfix + "\n" + "Prefix: " + prefix;
        // Check which evaluation was performed and append the result
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

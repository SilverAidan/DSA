import java.util.Stack;

public class Fixers {
    Stack<String> operations = new Stack<String>();

    String postfix = "";
    String prefix = "";

    public Fixers(String infix) {
        postfix = postConvert(infix);
        prefix = preConvert(postfix);
    }

    public String postConvert(String infix) {
        String output = "";
        String operands = "1234567890";
        Stack<Character> temp = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (c == '(') {
                temp.push(c);
            }
            else if (operands.indexOf(c) != -1) {
                output += c;
            }
            else if (c == ')') {
                while (!temp.isEmpty() && temp.peek() != '(') {
                    output += temp.pop();
                }
                if (!temp.isEmpty() && temp.peek() == '(') {
                    temp.pop(); 
                }
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
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
        String output = "";
        String operands = "1234567890";
        Stack<Character> temp = new Stack<>();
        
        for (char c : postfix.toCharArray()) {
            if (operands.indexOf(c) != -1) {
                temp.push(c);
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                int right = temp.pop() - '0';
                int left = temp.pop() - '0';
            }
        }

        return output;
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

    public String toString(){
        return postfix;
        return prefix;
    }
}

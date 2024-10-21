import java.util.Stack;

public class BraceChecker {

    public String checkBraces(String text) {
        Stack<Character> stack = new Stack<>();
        for (char ch : text.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return "Unpaired Brace Found!";
                }
            }
        }
        return stack.isEmpty() ? "OK" : "Unpaired Brace Found!";
    }

    private boolean isMatchingPair(char open, char close) {
        return (open == '{' && close == '}') ||
                (open == '[' && close == ']') ||
                (open == '(' && close == ')');
    }
}


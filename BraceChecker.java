import java.util.Stack;

public class BraceChecker {

    public String checkBraces(String text) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return "Unpaired or mismatched brace found at index " + i;
                }
            }
        }

        return stack.isEmpty() ? "Braces are balanced" : "Unpaired brace(s) found!";
    }

    private boolean isMatchingPair(char open, char close) {
        return (open == '{' && close == '}') ||
                (open == '[' && close == ']') ||
                (open == '(' && close == ')');
    }
}


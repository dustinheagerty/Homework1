import java.util.Stack;

public class ActionManager {

    // Action type constants
    private static final String INSERT = "INSERT";
    private static final String DELETE = "DELETE";

    // Stack to store actions for undo/redo
    private Stack<Action> undoStack;
    private Stack<Action> redoStack;
    private StringBuilder currentText;

    // Constructor
    public ActionManager() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        currentText = new StringBuilder();
    }

    // Add insert action
    public void addInsertAction(String insertedText, int position) {
        if (undoStack.size() == 10) {
            undoStack.remove(0); // Keep only the last 10 actions
        }
        undoStack.push(new Action(INSERT, insertedText, position));
        redoStack.clear(); // Clear redo stack on new action
        currentText.insert(position, insertedText); // Insert the new text
    }

    // Add delete action
    public void addDeleteAction(String deletedText, int position) {
        if (undoStack.size() == 10) {
            undoStack.remove(0); // Keep only the last 10 actions
        }
        undoStack.push(new Action(DELETE, deletedText, position));
        redoStack.clear(); // Clear redo stack on new action
        currentText.delete(position, position + deletedText.length()); // Remove the text
    }

    // Undo the last action
    public void undoAction() {
        if (!undoStack.isEmpty()) {
            Action lastAction = undoStack.pop();
            redoStack.push(lastAction);
            if (lastAction.getType().equals(INSERT)) {
                currentText.delete(lastAction.getPosition(), lastAction.getPosition() + lastAction.getText().length());
            } else if (lastAction.getType().equals(DELETE)) {
                currentText.insert(lastAction.getPosition(), lastAction.getText());
            }
        }
    }

    // Redo the last undone action
    public void redoAction() {
        if (!redoStack.isEmpty()) {
            Action lastAction = redoStack.pop();
            undoStack.push(lastAction);
            if (lastAction.getType().equals(INSERT)) {
                currentText.insert(lastAction.getPosition(), lastAction.getText());
            } else if (lastAction.getType().equals(DELETE)) {
                currentText.delete(lastAction.getPosition(), lastAction.getPosition() + lastAction.getText().length());
            }
        }
    }

    // Get current text as a string
    public String getText() {
        return currentText.toString();
    }

    // Batch undo using recursion
    public void batchUndoRecursive(int steps) {
        if (steps > 0 && !undoStack.isEmpty()) {
            undoAction();
            batchUndoRecursive(steps - 1);
        }
    }

    // Batch redo using recursion
    public void batchRedoRecursive(int steps) {
        if (steps > 0 && !redoStack.isEmpty()) {
            redoAction();
            batchRedoRecursive(steps - 1);
        }
    }

    // Action inner class to represent individual actions (insert/delete)
    private class Action {
        private String type;
        private String text;
        private int position;

        public Action(String type, String text, int position) {
            this.type = type;
            this.text = text;
            this.position = position;
        }

        public String getType() {
            return type;
        }

        public String getText() {
            return text;
        }

        public int getPosition() {
            return position;
        }
    }
}

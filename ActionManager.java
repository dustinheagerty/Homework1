import java.util.Stack;

public class ActionManager {

    private Stack<String> undoStack;
    private Stack<String> redoStack;
    private String currentText;

    public ActionManager() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        currentText = "";
    }

    public void addAction(String text) {
        if (undoStack.size() == 10) {
            undoStack.remove(0); // Keep only the last 10 actions
        }
        undoStack.push(currentText);
        currentText = text;
        redoStack.clear(); // Clear redo stack on new action
    }

    public void undoAction() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText);
            currentText = undoStack.pop();
        }
    }

    public void redoAction() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText);
            currentText = redoStack.pop();
        }
    }

    public void batchUndoRecursive(int steps) {
        if (steps > 0 && !undoStack.isEmpty()) {
            redoStack.push(currentText);
            currentText = undoStack.pop();
            batchUndoRecursive(steps - 1);
        }
    }

    public void batchRedoRecursive(int steps) {
        if (steps > 0 && !redoStack.isEmpty()) {
            undoStack.push(currentText);
            currentText = redoStack.pop();
            batchRedoRecursive(steps - 1);
        }
    }

    public String getText() {
        return currentText;
    }
}

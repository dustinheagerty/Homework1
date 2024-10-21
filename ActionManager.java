public class ActionManager {
    private Stack<String> undoStack;
    private Stack<String> redoStack;
    private TextArea codeArea;

    public ActionManager(TextArea codeArea) {
        this.codeArea = codeArea;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void trackAction(String action) {
        if (undoStack.size() == 10) {
            undoStack.remove(0); // Remove the oldest action
        }
        undoStack.push(action);
        redoStack.clear(); // Clear redo stack on new action
    }

    public void undoAction() {
        if (!undoStack.isEmpty()) {
            String lastAction = undoStack.pop();
            redoStack.push(codeArea.getText());
            codeArea.setText(lastAction);
        }
    }

    public void redoAction() {
        if (!redoStack.isEmpty()) {
            String redoAction = redoStack.pop();
            undoStack.push(codeArea.getText());
            codeArea.setText(redoAction);
        }
    }

    // Recursive batch undo
    public void batchUndoRecursive(int steps) {
        if (steps > 0 && !undoStack.isEmpty()) {
            undoAction();
            batchUndoRecursive(steps - 1);
        }
    }

    // Recursive batch redo
    public void batchRedoRecursive(int steps) {
        if (steps > 0 && !redoStack.isEmpty()) {
            redoAction();
            batchRedoRecursive(steps - 1);
        }
    }
}


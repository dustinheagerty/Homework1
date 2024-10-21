public class TextEditorState {
    private String currentText;

    public TextEditorState(String currentText) {
        this.currentText = currentText;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }
}


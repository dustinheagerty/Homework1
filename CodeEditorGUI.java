import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeEditorGUI extends Application {

    private TextArea codeArea;
    private ActionManager actionManager;
    private BraceChecker braceChecker;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Code Editor");

        // Initialize components
        codeArea = new TextArea();
        actionManager = new ActionManager();
        braceChecker = new BraceChecker();

        Button undoButton = new Button("Undo");
        Button redoButton = new Button("Redo");
        Button batchUndoButton = new Button("Batch Undo (Up to 10)");
        Button batchRedoButton = new Button("Batch Redo (Up to 10)");
        Button checkBracesButton = new Button("Check Braces");

        // Set up button actions
        undoButton.setOnAction(e -> undoAction());
        redoButton.setOnAction(e -> redoAction());
        batchUndoButton.setOnAction(e -> batchUndoAction());
        batchRedoButton.setOnAction(e -> batchRedoAction());
        checkBracesButton.setOnAction(e -> checkBraces());

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(codeArea, undoButton, redoButton, batchUndoButton, batchRedoButton, checkBracesButton);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void undoAction() {
        actionManager.undoAction();
        codeArea.setText(actionManager.getText());
    }

    private void redoAction() {
        actionManager.redoAction();
        codeArea.setText(actionManager.getText());
    }

    private void batchUndoAction() {
        actionManager.batchUndoRecursive(10);
        codeArea.setText(actionManager.getText());
    }

    private void batchRedoAction() {
        actionManager.batchRedoRecursive(10);
        codeArea.setText(actionManager.getText());
    }

    private void checkBraces() {
        String result = braceChecker.checkBraces(codeArea.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Brace Checker");
        alert.setHeaderText(result.equals("OK") ? "No Unpaired Braces" : "Unpaired Braces Found");
        alert.setContentText(result);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class EffortLogger extends Application {

    private boolean isClockRunning = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Effort Logger");
        primaryStage.setScene(createEffortLoggerScene());
        primaryStage.show();
    }

    private Scene createEffortLoggerScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        Text title = new Text("Effort Logger");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Text clockStatus = new Text("Clock is stopped");
        clockStatus.setFill(Color.RED);

        Text section1 = new Text("1. When you start an activity press the start activity button.");

        Button startButton = new Button("Start Activity");
        startButton.setOnAction(e -> {
            isClockRunning = true;
            clockStatus.setText("Clock is running");
            clockStatus.setFill(Color.GREEN);
        });

        Text section2 = new Text("2. Select the Project, Lifecycle Step, Effort Category from the following lists:");
        section2.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        ComboBox<String> projectDropdown = new ComboBox<>();
        projectDropdown.setPromptText("Project");

        ComboBox<String> lifecycleDropdown = new ComboBox<>();
        lifecycleDropdown.setPromptText("Life Cycle Step");

        ComboBox<String> effortCategoryDropdown = new ComboBox<>();
        effortCategoryDropdown.setPromptText("Effort Category");

        Button addProjectButton = createAddButton(projectDropdown);
        Button addLifecycleButton = createAddButton(lifecycleDropdown);
        Button addEffortCategoryButton = createAddButton(effortCategoryDropdown);

        HBox projectAndLifecycleBox = new HBox(20);
        projectAndLifecycleBox.setAlignment(Pos.CENTER);
        projectAndLifecycleBox.getChildren().addAll(
                createLabeledRow("Project:", projectDropdown, addProjectButton),
                createLabeledRow("Life Cycle Step:", lifecycleDropdown, addLifecycleButton)
        );

        VBox effortCategoryBox = createLabeledRow("Effort Category:", effortCategoryDropdown, addEffortCategoryButton);
        effortCategoryBox.setAlignment(Pos.CENTER);

        Text section3 = new Text("3. Press the 'Stop this Activity' button to generate an effort log entry using the attributes above.");
        Button stopButton = new Button("Stop this Activity");
        stopButton.setOnAction(e -> {
            isClockRunning = false;
            clockStatus.setText("Clock is stopped");
            clockStatus.setFill(Color.RED);
        });

        centerBox.getChildren().addAll(title, clockStatus, section1, startButton, section2,
                projectAndLifecycleBox,
                effortCategoryBox,
                section3,
                stopButton);

        root.setCenter(centerBox);

        return new Scene(root, 600, 600); // Larger window size for Effort Logger
    }

    private VBox createLabeledRow(String label, ComboBox<String> dropdown, Button addButton) {
        VBox row = new VBox(5);
        row.setAlignment(Pos.CENTER);

        Text labelText = new Text(label);
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(labelText, dropdown, addButton);
        row.getChildren().add(hbox);

        return row;
    }

    private Button createAddButton(ComboBox<String> dropdown) {
        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Item");
            dialog.setHeaderText("Enter a new item:");
            dialog.setContentText("Item:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(item -> {
                dropdown.getItems().add(item);
            });
        });
        return addButton;
    }
}
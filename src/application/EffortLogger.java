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
        // BorderPane to organize layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Main container for all elements
        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        // Title for the application
        Text title = new Text("Effort Logger");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Text to display the clock status
        Text clockStatus = new Text("Clock is stopped");
        clockStatus.setFill(Color.RED);

        // Section 1: Starting an activity
        Text section1 = new Text("1. When you start an activity press the start activity button.");

        // Button to start the activity
        Button startButton = new Button("Start Activity");
        startButton.setOnAction(e -> {
            isClockRunning = true;
            clockStatus.setText("Clock is running");
            clockStatus.setFill(Color.GREEN);
        });

        // Section 2: Selecting project, lifecycle step, effort category
        Text section2 = new Text("2. Select the Project, Lifecycle Step, Effort Category from the following lists:");
        section2.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        // Dropdowns for project, lifecycle, effort category with respective add buttons
        ComboBox<String> projectDropdown = new ComboBox<>();
        projectDropdown.setPromptText("Project");

        ComboBox<String> lifecycleDropdown = new ComboBox<>();
        lifecycleDropdown.setPromptText("Life Cycle Step");

        ComboBox<String> effortCategoryDropdown = new ComboBox<>();
        effortCategoryDropdown.setPromptText("Effort Category");

        Button addProjectButton = createAddButton(projectDropdown);
        Button addLifecycleButton = createAddButton(lifecycleDropdown);
        Button addEffortCategoryButton = createAddButton(effortCategoryDropdown);

        // Arranging project and lifecycle in a horizontal box
        HBox projectAndLifecycleBox = new HBox(20);
        projectAndLifecycleBox.setAlignment(Pos.CENTER);
        projectAndLifecycleBox.getChildren().addAll(
                createLabeledRow("Project:", projectDropdown, addProjectButton),
                createLabeledRow("Life Cycle Step:", lifecycleDropdown, addLifecycleButton)
        );

        // Section 3: Stopping the activity
        Text section3 = new Text("3. Press the 'Stop this Activity' button to generate an effort log entry using the attributes above.");
        Button stopButton = new Button("Stop this Activity");
        stopButton.setOnAction(e -> {
            isClockRunning = false;
            clockStatus.setText("Clock is stopped");
            clockStatus.setFill(Color.RED);
        });

        // Adding all sections and elements to the main container
        centerBox.getChildren().addAll(title, clockStatus, section1, startButton, section2,
                projectAndLifecycleBox,
                createLabeledRow("Effort Category:", effortCategoryDropdown, addEffortCategoryButton),
                section3,
                stopButton);

        // Setting the main container in the center of the BorderPane
        root.setCenter(centerBox);

        return new Scene(root, 600, 600); // Larger window size for Effort Logger
    }

    // Method to create a labeled row for dropdowns and buttons
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

    // Method to create the add button functionality
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
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class PlanningPoker extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Planning Poker");
        primaryStage.setScene(createPlanningPokerScene());
        primaryStage.show();
    }

    private Scene createPlanningPokerScene() {
        // BorderPane to organize layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Main container for all elements
        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        // Title for the application
        Text title = new Text("Planning Poker");
        title.setFont(javafx.scene.text.Font.font("Arial", FontWeight.BOLD, 24));

        // Section 2: Selecting project, lifecycle step, effort category
        Text section2 = new Text("2. Select the Project, Lifecycle Step, Effort Category from the following lists:");
        section2.setFont(javafx.scene.text.Font.font("Arial", FontWeight.NORMAL, 14));

        // Dropdowns for project, lifecycle, effort category
        ComboBox<String> projectDropdown = new ComboBox<>();
        projectDropdown.setPromptText("Project");

        ComboBox<String> lifecycleDropdown = new ComboBox<>();
        lifecycleDropdown.setPromptText("Life Cycle Step");

        ComboBox<String> effortCategoryDropdown = new ComboBox<>();
        effortCategoryDropdown.setPromptText("Effort Category");

        // Reusing the same lists as EffortLogger
        //projectDropdown.getItems().addAll(EffortLogger.projectList);
        //lifecycleDropdown.getItems().addAll(EffortLogger.lifeCycleStepList);
        //effortCategoryDropdown.getItems().addAll(EffortLogger.effortCategoryList);

        // Section 3: Pick a number 1-10
        Text section3 = new Text("3. Pick a number 1-10:");
        ChoiceBox<Integer> numberChoiceBox = new ChoiceBox<>();
        for (int i = 1; i <= 10; i++) {
            numberChoiceBox.getItems().add(i);
        }

        // Adding sections and elements to the main container
        centerBox.getChildren().addAll(title, section2,
                createLabeledRow("Project:", projectDropdown),
                createLabeledRow("Life Cycle Step:", lifecycleDropdown),
                createLabeledRow("Effort Category:", effortCategoryDropdown),
                section3,
                numberChoiceBox);

        // Setting the main container in the center of the BorderPane
        root.setCenter(centerBox);

        return new Scene(root, 600, 600); // Larger window size for Planning Poker
    }

    // Method to create a labeled row for dropdowns
    private VBox createLabeledRow(String label, ComboBox<String> dropdown) {
        VBox row = new VBox(5);
        row.setAlignment(Pos.CENTER);

        Text labelText = new Text(label);
        row.getChildren().addAll(labelText, dropdown);

        return row;
    }
}
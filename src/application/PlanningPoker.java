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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.util.Arrays;

public class PlanningPoker extends Application {
	
	private Runnable planningPokerCallback;

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
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        Text title = new Text("Planning Poker");
        title.setFont(javafx.scene.text.Font.font("Arial", FontWeight.BOLD, 24));

        Text section1 = new Text("1. Select the Project, Lifecycle Step, Effort Category from the following lists:");
        section1.setFont(javafx.scene.text.Font.font("Arial", FontWeight.NORMAL, 14));

        ComboBox<String> projectDropdown = new ComboBox<>();
        projectDropdown.setPromptText("Project");

        ComboBox<String> lifecycleDropdown = new ComboBox<>();
        lifecycleDropdown.setPromptText("Life Cycle Step");

        ComboBox<String> effortCategoryDropdown = new ComboBox<>();
        effortCategoryDropdown.setPromptText("Effort Category");


        Text section2 = new Text("2. Search up to 3 tags separated by a comma to find relative data.");
        section2.setFont(javafx.scene.text.Font.font("Arial", FontWeight.NORMAL, 14));

        TextField tagInput = new TextField();
        tagInput.setPromptText("Enter tags separated by commas");

        Text output = new Text();

        Button searchButton = new Button("Search");
        tagInput.setPromptText("Enter tags separated by commas");

        searchButton.setOnAction(e -> {
            String userInput = tagInput.getText().trim();
            if (userInput.isEmpty()) {
                output.setText("Please enter tags.");
                return;
            }

            String[] tags = userInput.split(",");
            if (tags.length > 3) {
                output.setText("Too many tags, please enter a max of 3.");
            } else {
                if (tags.length >= 1) {
                    String tag1 = tags[0].trim();
                }
                if (tags.length >= 2) {
                    String tag2 = tags[1].trim();
                }
                if (tags.length == 3) {
                    String tag3 = tags[2].trim();
                }
                output.setText("Tags: " + Arrays.toString(tags));
            }
        });

        Text section3 = new Text("3. Pick a number 1-10:");
        ChoiceBox<Integer> numberChoiceBox = new ChoiceBox<>();
        for (int i = 1; i <= 10; i++) {
            numberChoiceBox.getItems().add(i);
        }
        
        Button submitButton = new Button("Submit");
        
        submitButton.setOnAction(e -> {
        	
        	//Call on save function here
        	//save()
        	
            Stage successStage = new Stage();
            successStage.setTitle("Success");
            VBox successBox = new VBox(20);
            successBox.setAlignment(Pos.CENTER);
            successBox.getChildren().add(new Text("Estimate Submitted Successfully"));

            Scene successScene = new Scene(successBox, 300, 100);
            successStage.setScene(successScene);
            successStage.show();

            Stage currentStage = (Stage) submitButton.getScene().getWindow();
            currentStage.close();
        });
        
        

        centerBox.getChildren().addAll(title, section1, createLabeledRow("Project:", projectDropdown),
                createLabeledRow("Life Cycle Step:", lifecycleDropdown),
                createLabeledRow("Effort Category:", effortCategoryDropdown),
                section2, tagInput, searchButton, output, section3, numberChoiceBox, submitButton);

        root.setCenter(centerBox);

        return new Scene(root, 600, 600);
    }

    private VBox createLabeledRow(String label, ComboBox<String> dropdown) {
        VBox row = new VBox(5);
        row.setAlignment(Pos.CENTER);

        Text labelText = new Text(label);
        row.getChildren().addAll(labelText, dropdown);

        return row;
    }
    
    
    public void setPlanningPokerCallback(Runnable callback) {
        this.planningPokerCallback = callback;
    }
}
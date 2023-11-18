package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    	
    	String projectNameDefault = "Project";
    	String lifecycleDefault = "Life Cycle Step";
    	String effortDefault = "Effort Category";
    	
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        Text title = new Text("Planning Poker");
        title.setFont(javafx.scene.text.Font.font("Arial", FontWeight.BOLD, 24));

        Text section1 = new Text("1. Select the Project, Lifecycle Step, Effort Category from the following lists:");
        section1.setFont(javafx.scene.text.Font.font("Arial", FontWeight.NORMAL, 14));

        ComboBox<String> projectDropdown = new ComboBox<>();
        projectDropdown.setPromptText(projectNameDefault);
        
        String[] currentProjects = new String[Main.llm.getProjectCount()];
        
        for(int i = 0; i < Main.llm.getProjectCount(); i++) {
        	
        	currentProjects[i] = Main.llm.getProjectName(i);
        	
        }
        
        projectDropdown.getItems().addAll(currentProjects);

        ComboBox<String> lifecycleDropdown = new ComboBox<>();
        lifecycleDropdown.setPromptText(lifecycleDefault);
        lifecycleDropdown.getItems().addAll(
        		"Problem Understanding",
        		"Conceptual Design Plan",
        		"Requirements",
        		"Conceptual Design",
        		"Conceptual Design Review",
        		"Detailed Design Plan",
        		"Detailed Design/Prototype",
        		"Detailed Design Review",
        		"Implementation Plan",
        		"Test Case Generation",
        		"Solution Specification",
        		"Solution Review",
        		"Solution Implementation",
        		"Unit/System Test",
        		"Reflection",
        		"Repository Update",
        		"Planning",
        		"Information Gathering",
        		"Information Understanding",
        		"Verifying",
        		"Outlining",
        		"Drafting",
        		"Finalizing",
        		"Team Meeting",
        		"Coach Meeting",
        		"Stakeholder Meeting"
        		);

        ComboBox<String> effortCategoryDropdown = new ComboBox<>();
        effortCategoryDropdown.setPromptText(effortDefault);
        effortCategoryDropdown.getItems().addAll(
        		"Project Plan",
    			"Risk Management Plan",
    			"Conceptual Design Plan",
    			"Detailed Design Plan",
    			"Implementation Plan",
    			"Conceptual Design",
    			"Detailed Design",
    			"Test Cases",
    			"Solution",
    			"Reflection",
    			"Outline",
    			"Draft",
    			"Report",
    			"User Defined",
    			"Break",
    			"Phone",
    			"Teammate",
    			"Visitor",
    			"Not specified",
    			"10 Documentation",
    			"20 Syntax",
    			"30 Build, Package",
    			"40 Assignment",
    			"50 Interface",
    			"60 Checking",
    			"70 Data",
    			"80 Function",
    			"90 System",
    			"100 Environment",
        		"Plans",
        		"Deliverables",
        		"Interruptions",
        		"Defects",
        		"Others"
        		);
        
        //TextField tagInput = new TextField();
        //tagInput.setPromptText("Enter tags separated by commas");
        
        Button nextButton = new Button("Next");
        Button prevButton = new Button("Previous");
        
        HBox nextAndPrevButton = new HBox(20);
        nextAndPrevButton.setAlignment(Pos.CENTER);
        nextAndPrevButton.getChildren().addAll(
                nextButton, prevButton
        );

        Text output = new Text();

        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            
        	Main.llm.searchUserData(projectDropdown.getValue(), lifecycleDropdown.getValue(), effortCategoryDropdown.getValue(),
        			projectNameDefault, lifecycleDefault, effortDefault);
            
        });
        
        nextButton.setOnAction(e -> {
        	
        	
        	
        });
        
        prevButton.setOnAction(e -> {
        	
        	
        	
        });


        /*Text section2 = new Text("2. Search up to 3 tags separated by a comma to find relative data.");
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
        });*/

        Text section3 = new Text("3. Pick a number 1-10:");
        ChoiceBox<Integer> numberChoiceBox = new ChoiceBox<>();
        for (int i = 1; i <= 10; i++) {
            numberChoiceBox.getItems().add(i);
        }
        
        Button submitButton = new Button("Submit");
        
        submitButton.setOnAction(e -> {
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
                /*section2,*/ /*tagInput,*/ searchButton, output, nextAndPrevButton, section3, numberChoiceBox, submitButton);

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
}
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
	
	DataNode searching = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Planning Poker");
        primaryStage.setScene(createPlanningPokerScene(primaryStage));
        primaryStage.show();
    }

    private Scene createPlanningPokerScene(Stage primaryStage) {
    	
    	//Andrew's Work Zone
    	String projectNameDefault = "Project";
    	String lifecycleDefault = "Life Cycle Step";
    	String effortDefault = "Effort Category";
    	
        String[] currentProjects = new String[Main.llm.getProjectCount()];
        
        Text output = new Text();
    	
    	Button exitButton = new Button("EXIT");
    	Button backButton = new Button("Back");
    	Button clearButton = new Button("Clear search terms");
        Button nextButton = new Button("Next");
        Button prevButton = new Button("Previous");
        Button searchButton = new Button("Search");
        
        HBox nextAndPrevButton = new HBox(20);
        nextAndPrevButton.setAlignment(Pos.CENTER);
        nextAndPrevButton.getChildren().addAll(
                prevButton, nextButton
        );
        
        nextButton.setDisable(true);
        prevButton.setDisable(true);
    	//End Andrew's Work Zone
    	
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
        
        //Andrew's Work Zone
        for(int i = 0; i < (Main.llm.getProjectCount()); i++) {
        	
        	currentProjects[i] = Main.llm.getProjectName(i+1); //adding "project" somewhere by accident...
        	
        }
        
        projectDropdown.getItems().addAll(currentProjects);
        //Andrew's Work Zone

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

        //Andrew's Work Zone
        searchButton.setOnAction(e -> {
        	
        	nextButton.setDisable(true);
        	prevButton.setDisable(true);
            
        	searching = Main.llm.searchUserData(projectDropdown.getValue(), lifecycleDropdown.getValue(), 
        			effortCategoryDropdown.getValue());
        	
        	if(searching != null) {
        		
        		output.setText(searching.display());
        		
        		if(searching.getNext() != null) {
        			
        			nextButton.setDisable(false);
        			
        		}
        		
        	}
        	else
        		output.setText("No matches!");
            
        });
        
        nextButton.setOnAction(e -> {
        	
        	if(searching.getNext() != null) {
        		
        		searching = searching.getNext();
        		output.setText(searching.display());
        		
        		if(searching.getNext() == null) {
        			
        			nextButton.setDisable(true);
        			
        		}
        		
        		if(searching.getPrevious() != null) {
        			
        			prevButton.setDisable(false);
        			
        		}
        		
        	}
        	else {
        		
        		output.setText("No data entries this direction.");
        		
        	}
        	
        });
        
        prevButton.setOnAction(e -> {
        	
        	if(searching.getPrevious() != null) {
        		
        		searching = searching.getPrevious();
        		output.setText(searching.display());
        		
        		if(searching.getPrevious() == null) {
        			
        			prevButton.setDisable(true);
        			
        		}
        		
        		if(searching.getNext() != null) {
        			
        			nextButton.setDisable(false);
        			
        		}
        		
        	}
        	else {
        		
        		output.setText("No data entries this direction.");
        		
        	}
        	
        });
        
        clearButton.setOnAction(e -> {
        	
        	projectDropdown.setValue(null);
        	lifecycleDropdown.setValue(null);
        	effortCategoryDropdown.setValue(null);
        	
        });
        
        exitButton.setOnAction(e -> {
        	
        	Main.llm.save();
        	System.exit(0);
        	
        });
        
        backButton.setOnAction(e -> {
        	
        	primaryStage.close();
        	
        });
        //End Andrew's Work Zone

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

        centerBox.getChildren().addAll(exitButton, backButton, title, section1, createLabeledRow("Project:", projectDropdown),
                createLabeledRow("Life Cycle Step:", lifecycleDropdown),
                createLabeledRow("Effort Category:", effortCategoryDropdown),
                searchButton, clearButton, output, nextAndPrevButton, section3, numberChoiceBox, submitButton);

        root.setCenter(centerBox);

        return new Scene(root, 600, 900);
    }

    private VBox createLabeledRow(String label, ComboBox<String> dropdown) {
        VBox row = new VBox(5);
        row.setAlignment(Pos.CENTER);

        Text labelText = new Text(label);
        row.getChildren().addAll(labelText, dropdown);

        return row;
    }
}
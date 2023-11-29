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

	private Runnable planningPokerCallback;

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
    	
    	//Array to hold the names of the current projects. Initialized length is equal to the number of current projects
        String[] currentProjects = new String[Main.llm.getProjectCount()];
        
        //To display found data
        Text output = new Text();
    	
        //Buttons to manipulate the program
    	Button exitButton = new Button("EXIT"); //Exit the program
    	Button backButton = new Button("Back"); //Go back to the previous screen
    	Button clearButton = new Button("Clear search terms"); //Reset search terms
        Button nextButton = new Button("Next"); //View next item found in search
        Button prevButton = new Button("Previous"); //View previous item found in search
        Button searchButton = new Button("Search"); //Search based on given parameters
        
        //Formatting
        HBox nextAndPrevButton = new HBox(20);
        nextAndPrevButton.setAlignment(Pos.CENTER);
        nextAndPrevButton.getChildren().addAll(
                prevButton, nextButton
        );
        
        //Next and previous disabled by default to avoid bad inputs
        nextButton.setDisable(true);
        prevButton.setDisable(true);
    	
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
        
        //Get the Strings to populate the ComboBox
        for(int i = 0; i < (Main.llm.getProjectCount()); i++) {
        	
        	//i+1 is used because "Project" is being added somewhere in the code.
        	currentProjects[i] = Main.llm.getProjectName(i+1); //adding "Project" somewhere by accident...
        	
        }
        
        //Populate the ComboBox
        projectDropdown.getItems().addAll(currentProjects);

        ComboBox<String> lifecycleDropdown = new ComboBox<>();
        lifecycleDropdown.setPromptText("Life Cycle Step");
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
        effortCategoryDropdown.setPromptText("Effort Category");
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

        //Search button code
        searchButton.setOnAction(e -> {
        	
        	//Disable next and previous buttons to prevent bad inputs
        	nextButton.setDisable(true);
        	prevButton.setDisable(true);
            
        	//Search based on the given parameters
        	searching = Main.llm.searchUserData(projectDropdown.getValue(), lifecycleDropdown.getValue(), 
        			effortCategoryDropdown.getValue());
        	
        	if(searching != null) { //Make sure something was returned
        		
        		output.setText(searching.display()); //Display first result
        		
        		if(searching.getNext() != null) { //If there is at least one additional result, enable the next button
        			
        			nextButton.setDisable(false);
        			
        		}
        		
        	}
        	else //If no results, display an error
        		output.setText("No matches!");
            
        });
        
        //Next button behavior
        nextButton.setOnAction(e -> {
        	
        	if(searching.getNext() != null) { //Make sure we don't run off the edge of the linked list
        		
        		searching = searching.getNext();
        		output.setText(searching.display()); //Display the next item
        		
        		if(searching.getNext() == null) { //Disable the next button if there is no next item
        			
        			nextButton.setDisable(true);
        			
        		}
        		
        		if(searching.getPrevious() != null) { //Enable the previous button if there is at least one previous item
        			
        			prevButton.setDisable(false);
        			
        		}
        		
        	}
        	else { //Fail safe message. Should not occur under normal operation. Indicates something with the next button broke.
        		
        		output.setText("No data entries this direction.");
        		
        	}
        	
        });
        
        //Previous button behavior
        prevButton.setOnAction(e -> {
        	
        	if(searching.getPrevious() != null) { //Make sure we don't run off the edge of the linked list
        		
        		searching = searching.getPrevious();
        		output.setText(searching.display()); //Display the previous item
        		
        		if(searching.getPrevious() == null) {//Disable the previous button if there is no previous item
        			
        			prevButton.setDisable(true);
        			
        		}
        		
        		if(searching.getNext() != null) { //Enable the next button if there is at least one next item
        			
        			nextButton.setDisable(false);
        			
        		}
        		
        	}
        	else { //Fail safe message. Should not occur under normal operation. Indicates something with the previous button broke.
        		
        		output.setText("No data entries this direction.");
        		
        	}
        	
        });
        
        //Clear search terms button functionality
        clearButton.setOnAction(e -> {
        	
        	//Reset the ComboBox values
        	projectDropdown.setValue(null);
        	lifecycleDropdown.setValue(null);
        	effortCategoryDropdown.setValue(null);
        	
        });
        
        //Exit button
        exitButton.setOnAction(e -> {
        	
        	Main.llm.save(); //Save data
        	System.exit(0); //Exit program
        	
        });
        
        //Back button
        backButton.setOnAction(e -> {
        	
        	primaryStage.close(); //Close Planning Poker stage to see MainApp stage
        	
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
    
    
    public void setPlanningPokerCallback(Runnable callback) {
        this.planningPokerCallback = callback;
    }
}
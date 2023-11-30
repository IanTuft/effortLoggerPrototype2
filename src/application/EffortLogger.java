//GUI: Ian Tuft
//Functions: Gino Damerchi
//Data management: Andrew Thomas

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
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * The EffortLogger class is responsible for tracking and logging user activities.
 * It provides a graphical interface for users to start and stop logging, select project details,
 * and log the time spent on various lifecycle steps and effort categories.
 */

public class EffortLogger extends Application {

	private boolean isClockRunning = false;
	private int logCounter = 0; //Log counter
	private DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Format dates
	private DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm:ss"); //Format time
	private String startTime;
	private String endTime;
	private String date;
	private Instant start;
	private Instant end;
	
	//private ProcessInput processInput = new ProcessInput();
	
	//Gets the local time
	public String getTime() {
		LocalDateTime now = LocalDateTime.now();  
		return timeformat.format(now);  
	}
	
	//Gets the local date
	public String getDate() {
		LocalDateTime date = LocalDateTime.now();
		return dateformat.format(date);  
	}
	
    private Runnable effortLoggerCallback;

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Overrides the start method in Application, setting up the initial stage for the EffortLogger.
     *
     * @param primaryStage The primary stage for the EffortLogger.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Effort Logger");
        primaryStage.setScene(createEffortLoggerScene(primaryStage));
        primaryStage.show();
    }
    
    /**
     * Creates the scene for the EffortLogger, including UI elements and functionality.
     *
     * @param primaryStage The primary stage for the EffortLogger.
     * @return The Scene object representing the EffortLogger interface.
     */
    private Scene createEffortLoggerScene(Stage primaryStage) {
        
        Button stopButton = new Button("Stop this Activity");
        stopButton.setDisable(true);
        
        //Buttons for closing the program with save functionality and going back a screen
        Button exitButton = new Button("EXIT");
        Button backButton = new Button("Back");
        
        //For populating the project ComboBox
        String[] currentProjects = new String[Main.llm.getProjectCount()];
  
        root.setPadding(new Insets(20));

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        Text title = new Text("Effort Logger");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Text clockStatus = new Text("Clock is stopped");
        clockStatus.setFill(Color.RED);

        Text section1 = new Text("1. When you start an activity press the start activity button.");

        //Start the clock
        Button startButton = new Button("Start Activity");
        startButton.setOnAction(e -> {
        	stopButton.setDisable(false);
        	isClockRunning = true;
            startTime = getTime();
            start = Instant.now();
            clockStatus.setText("Clock is running");
            clockStatus.setFill(Color.GREEN);
            startButton.setDisable(true);
        });

        Text section2 = new Text("2. Select the Project, Lifecycle Step, Effort Category from the following lists:");
        section2.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        ComboBox<String> projectDropdown = new ComboBox<>();
        projectDropdown.setPromptText("Project");

        //Get the Strings to populate the ComboBox
        for(int i = 0; i < (Main.llm.getProjectCount()); i++) {
        	
        	//i+1 is used because "Project" is being added somewhere in the code.
        	currentProjects[i] = Main.llm.getProjectName(i+1); //adding "Project" somewhere by accident...
        	
        }
        //Populate the ComboBox
        projectDropdown.getItems().addAll(currentProjects);

        //Life Cycle Dropdown with lists from Definitions page on Effort Logger User Guide

        ComboBox<String> lifecycleDropdown = new ComboBox<>();
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
        lifecycleDropdown.setPromptText("Life Cycle Step");

        //Effory Category Dropdown with lists from Definitions page on Effort Logger User Guide
        ComboBox<String> effortCategoryDropdown = new ComboBox<>();
        effortCategoryDropdown.getItems().addAll(

        		"1. Plans",
    			"2. Deliverables",
    			"3. Interruptions",
    			"4. Defects",
        		"5. Others"
     		
        		);
        effortCategoryDropdown.setPromptText("Effort Category");
        
        //Deliverables/Interruptions/Etc. Dropdown with lists from Definitions page on Effort Logger User Guide
        ComboBox<String> miscDropdown = new ComboBox<>();
        miscDropdown.getItems().addAll(
        		"1. Project Plan",
    			"1. Risk Management Plan",
    			"1. Conceptual Design Plan",
    			"1. Detailed Design Plan",
    			"1. Implementation Plan",
    			"2. Conceptual Design",
    			"2. Detailed Design",
    			"2. Test Cases",
    			"2. Solution",
    			"2. Reflection",
    			"2. Outline",
    			"2. Draft",
    			"2. Report",
    			"2. User Defined",
    			"3. Break",
    			"3. Phone",
    			"3. Teammate",
    			"3. Visitor",
    			"4. Not specified",
    			"4. 10 Documentation",
    			"4. 20 Syntax",
    			"4. 30 Build, Package",
    			"4. 40 Assignment",
    			"4. 50 Interface",
    			"4. 60 Checking",
    			"4. 70 Data",
    			"4. 80 Function",
    			"4. 90 System",
    			"4. 100 Environment",
        		"5. Other"
        		);
        miscDropdown.setPromptText("Deliverable / Interruption / etc.");
        
        Button addProjectButton = createAddButton(projectDropdown);
        
        HBox projectAndLifecycleBox = new HBox(20);
        projectAndLifecycleBox.setAlignment(Pos.CENTER);
        projectAndLifecycleBox.getChildren().addAll(
                createLabeledRow("Project:", projectDropdown, addProjectButton),
                createLabeledRow("Life Cycle Step:", lifecycleDropdown)
        );

        /**
         * Creates a VBox for the bottom-right corner with the given button.
         *
         * @param exitButton The button to be placed in the bottom-right corner.
         * @return A VBox containing the exit button.
         */
        VBox effortCategoryBox = createLabeledRow("Effort Category:", effortCategoryDropdown);
        effortCategoryBox.setAlignment(Pos.CENTER);
        
        //Load the number of logs that exist for the selected project
        projectDropdown.setOnAction(e -> {
        	
        	logCounter = Main.llm.getLogCount(projectDropdown.getValue());
        	
        });
      
        HBox effortAndMiscBox = new HBox(20);
        effortAndMiscBox.setAlignment(Pos.CENTER);
        effortAndMiscBox.getChildren().addAll(
                createLabeledRow("Effort Category:", effortCategoryDropdown),
                createLabeledRow("", miscDropdown)
        );

        //Stop the clock button
        Text section3 = new Text("3. Press the 'Stop this Activity' button to generate an effort log entry using the attributes above.");
        stopButton.setOnAction(e -> {

            startButton.setDisable(false);
        	isClockRunning = false;
            date = getDate();
            endTime = getTime();
            end = Instant.now();
            logCounter++;
            String[] log = new String[8];
            log[0] = "" + logCounter;
            log[1] = date;
            log[2] = startTime;
            log[3] = endTime;
            log[4] = "" + Duration.between(start, end).toMinutes();
            log[5] = projectDropdown.getValue();
            log[6] = lifecycleDropdown.getValue();
            log[7] = effortCategoryDropdown.getValue();

            //Create new data and store it
            if(log[5] != null) { //Ensure a project name was selected
            	
	            if(!Main.llm.checkDuplicateProject(log[5])) { //Check if project already exists
	            	
	                Main.llm.addNewProject(log[5]); //If project does not already exist, add it as a new project
	            	
	            }
	
	            //Add the new data
	            Main.llm.addNewData(log[5], logCounter, Integer.parseInt(log[4]), date, startTime, endTime, 
	            		log[6], log[7]);
	            
            }
            
            clockStatus.setText("Clock is stopped");
            clockStatus.setFill(Color.RED);
            stopButton.setDisable(true);
        });
        
        //Exit button
        exitButton.setOnAction(e -> {
        	
        	Main.llm.save(); //Save data
        	System.exit(0); //Close program
        	
        });
        
        //Back button
        backButton.setOnAction(e -> {
        	
        	primaryStage.close(); //Close effortlogger stage to show MainApp stage

        });
        
        VBox bottomRightBox = createBottomRightBox(exitButton);

        centerBox.getChildren().addAll(exitButton, backButton, title, clockStatus, section1, startButton, section2,
                projectAndLifecycleBox,
                effortAndMiscBox,
                section3,
                stopButton);
        centerBox.getChildren().add(bottomRightBox);

        root.setCenter(centerBox);

        return new Scene(root, 600, 600); // Larger window size for Effort Logger
    }
    
    
    
    private VBox createBottomRightBox(Button exitButton) {
    	VBox bottomRightBox = new VBox(5);
        bottomRightBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRightBox.getChildren().add(exitButton);
        return bottomRightBox;
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
    
    private VBox createLabeledRow(String label, ComboBox<String> dropdown) {
        VBox row = new VBox(5);
        row.setAlignment(Pos.CENTER);

        Text labelText = new Text(label);
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(labelText, dropdown);
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
    
    public void setEffortLoggerCallback(Runnable callback) {
        this.effortLoggerCallback = callback;
    }
}
//GUI: Ian Tuft
//Functions: Gino Damerchi

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
import java.util.ArrayList;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Instant;

public class EffortLogger extends Application {

    private boolean isClockRunning = false;
    private ArrayList<String[]> database = new ArrayList<String[]>(); //ArrayList to hold each log entry
	private int logCounter = 0; //Log counter
	private DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Format dates
	private DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm:ss"); //Format time
	private String startTime;
	private String endTime;
	private String date;
	private Instant start;
	private Instant end;
	
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

        //Start the clock
        Button startButton = new Button("Start Activity");
        startButton.setOnAction(e -> {
            isClockRunning = true;
            startTime = getTime();
            start = Instant.now();
            clockStatus.setText("Clock is running");
            clockStatus.setFill(Color.GREEN);
        });

        Text section2 = new Text("2. Select the Project, Lifecycle Step, Effort Category from the following lists:");
        section2.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        ComboBox<String> projectDropdown = new ComboBox<>();
        projectDropdown.setPromptText("Project");

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
        Button addLifecycleButton = createAddButton(lifecycleDropdown);
        Button addEffortCategoryButton = createAddButton(effortCategoryDropdown);
        Button addMiscButton = createAddButton(miscDropdown);
        
        HBox projectAndLifecycleBox = new HBox(20);
        projectAndLifecycleBox.setAlignment(Pos.CENTER);
        projectAndLifecycleBox.getChildren().addAll(
                createLabeledRow("Project:", projectDropdown, addProjectButton),
                createLabeledRow("Life Cycle Step:", lifecycleDropdown, addLifecycleButton)
        );

        HBox effortAndMiscBox = new HBox(20);
        effortAndMiscBox.setAlignment(Pos.CENTER);
        effortAndMiscBox.getChildren().addAll(
                createLabeledRow("Effort Category:", effortCategoryDropdown, addEffortCategoryButton),
                createLabeledRow("", miscDropdown, addMiscButton)
        );
        
        //VBox effortCategoryBox = createLabeledRow("Effort Category:", effortCategoryDropdown, addEffortCategoryButton);
        //effortCategoryBox.setAlignment(Pos.CENTER);

        //Stop the clock button
        Text section3 = new Text("3. Press the 'Stop this Activity' button to generate an effort log entry using the attributes above.");
        Button stopButton = new Button("Stop this Activity");
        stopButton.setOnAction(e -> {
        	if(effortCategoryDropdown.getValue().charAt(0) == miscDropdown.getValue().charAt(0)){
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
                log[7] = "" + effortCategoryDropdown.getValue() + ": " + miscDropdown.getValue().substring(2);
                database.add(log);
                System.out.println(Arrays.toString(database.get(logCounter-1)));
                clockStatus.setText("Clock is stopped");
                clockStatus.setFill(Color.RED);
        	} else {
        		System.out.println("Error: Effort Category and Deliverables/Interruptions/etc. do not match.");
        	}
        });

        centerBox.getChildren().addAll(title, clockStatus, section1, startButton, section2,
                projectAndLifecycleBox,
                effortAndMiscBox,
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
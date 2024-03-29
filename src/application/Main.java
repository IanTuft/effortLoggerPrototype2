package application;
//LinkedListManager: Andrew Thomas
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static LinkedListManager llm; //Static for access across the program.

    private Stage primaryStage;

    public static void main(String[] args) {
        
    	//Double check that the llm object hasn't already been created before creating it to avoid overwriting
    	//data stored in the object.
    	if(llm == null) {
    		
    		llm = new LinkedListManager();
    		ReadData readData = new ReadData(llm); //This object is not used, but by created it we load the saved
    												//data into the LinkedListManager object
    		
    	}
    	
    	launch(args);
    }
    
    
    // Sets up the primary stage and opens the login page when the application starts.
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openLoginPage();
    }

    // Creates a new login object, starts the login window, and sets the callback for a successful login.
    private void openLoginPage() {
        LogIn login = new LogIn();
        login.start(new Stage());
        
     // Set the callback function for a successful login to open the main application
        login.setLoginSuccessCallback(this::openMainApp);
    }

    // Creates the MainApp object, starts its window, and sets callbacks for EffortLogger and PlanningPoker.
    private void openMainApp() {
        MainApp mainApp = new MainApp();
        mainApp.start(primaryStage);

     // Set the callback functions for opening EffortLogger and PlanningPoker
        mainApp.setEffortLoggerCallback(this::openEffortLogger);
        mainApp.setPlanningPokerCallback(this::openPlanningPoker);
    }

    // Creates EffortLogger, starts its window, ensures MainApp window remains open, and sets a callback for reopening EffortLogger.
    private void openEffortLogger() {
    	EffortLogger effortLogger = new EffortLogger();
        Stage effortLoggerStage = new Stage();
        effortLogger.start(effortLoggerStage);

        // Ensure MainApp window remains open
        primaryStage.show();

        // Set the EffortLogger callback to re-open EffortLogger
        effortLogger.setEffortLoggerCallback(this::openEffortLogger);
    }

    // Creates PlanningPoker, starts its window, ensures MainApp window remains open, and sets a callback for reopening EffortLogger (for this example).
    private void openPlanningPoker() {

    	PlanningPoker planningPoker = new PlanningPoker();
        Stage planningPokerStage = new Stage();
        planningPoker.start(planningPokerStage);
        
     // Ensure MainApp window remains open
        primaryStage.show();
        
     // Set the EffortLogger callback to re-open EffortLogger
        planningPoker.setPlanningPokerCallback(this::openEffortLogger);   
    }
}
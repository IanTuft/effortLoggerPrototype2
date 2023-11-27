package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openLoginPage();
    }

    private void openLoginPage() {
        LogIn login = new LogIn();
        login.start(new Stage());

        login.setLoginSuccessCallback(this::openMainApp);
    }

    private void openMainApp() {
        MainApp mainApp = new MainApp();
        mainApp.start(primaryStage);

        mainApp.setEffortLoggerCallback(this::openEffortLogger);
        mainApp.setPlanningPokerCallback(this::openPlanningPoker);
    }

    private void openEffortLogger() {
    	EffortLogger effortLogger = new EffortLogger();
        Stage effortLoggerStage = new Stage();
        effortLogger.start(effortLoggerStage);

        // Ensure MainApp window remains open
        primaryStage.show();

        // Set the EffortLogger callback to re-open EffortLogger
        effortLogger.setEffortLoggerCallback(this::openEffortLogger);
    }

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
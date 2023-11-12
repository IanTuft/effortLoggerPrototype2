package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.util.Scanner;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//prompts user to access different sections of the prototype
        System.out.println("Welcome to the Planning Poker / Effort Logger App!");
        System.out.println("Press 1 to access the login screen.");
        System.out.println("Press 2 to exit.");

        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();

            switch (input) {
                case "1": 
                	MainApp.launchApp(args);
                    break;
                case "2":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again."); //ensure only valid inputs are accepted
            }
        }

        System.out.println("Goodbye!");
        System.exit(0);
	}
}

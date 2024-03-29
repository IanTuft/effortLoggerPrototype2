package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainApp extends Application {
	
	private Stage primaryStage;
    private Runnable effortLoggerSuccessCallback;
    private Runnable planningPokerSuccessCallback;

    public static void launchApp() {
        launch();
    }
    
    
    /**
     * Overrides the start method in Application, setting up the initial stage for the Main App.
     *
     * @param primaryStage The primary stage for the Main App.
     */
    @Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
        primaryStage.setTitle("Planning Poker & Effort Logger");
        
        //Button to close the program
        Button exitButton = new Button("EXIT");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20)); // Adding padding around the entire layout

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        // Create box for Planning Poker (traditional poker colors)
        VBox planningPokerBox = createClickableBox("Planning Poker", Color.DARKGREEN, Color.LIGHTGREEN, Color.WHITE);
        planningPokerBox.setOnMouseClicked(e -> {
        	planningPokerSuccessCallback.run();
        });

        // Create box for Effort Logger (complementary colors)
        VBox effortLoggerBox = createClickableBox("Effort Logger", Color.DARKORANGE, Color.LIGHTCORAL, Color.WHITE);
        effortLoggerBox.setOnMouseClicked(e -> {
        	effortLoggerSuccessCallback.run();
        });
        
        exitButton.setOnAction(e -> {
        	
        	Main.llm.save(); //Save data
        	System.exit(0); //Exit
        	
        });

        centerBox.getChildren().addAll(exitButton, planningPokerBox, effortLoggerBox);

        root.setCenter(centerBox);

        primaryStage.setScene(new Scene(root, 600, 400)); // Larger window size
        primaryStage.show();
    }
  
    // Function to set the callback for opening the Effort Logger
    public void setEffortLoggerCallback(Runnable callback) {
        this.effortLoggerSuccessCallback = callback;
    }
    
    // Function to set the callback for opening the Planning Poker
    public void setPlanningPokerCallback(Runnable callback) {
        this.planningPokerSuccessCallback = callback;
    }
    
    // Create the boxes for planning poker and effort logger
    private VBox createClickableBox(String text, Color bgColor, Color borderColor, Color textColor) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(250, 150);
        box.setStyle(
                "-fx-background-color: " + toRGBCode(bgColor) + "; " +
                "-fx-border-color: " + toRGBCode(Color.BLACK) + "; " +
                "-fx-border-width: 2px; " +
                "-fx-background-radius: 10; -fx-border-radius: 10;");
        Text label = new Text(text);
        label.setFill(textColor);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        box.getChildren().add(label);
        return box;
    }
    
    // Convert JavaFX Color to RGB code
    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
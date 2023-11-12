package application;

import javafx.application.Application;
import javafx.scene.Scene;
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

    public static void launchApp() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Planning Poker & Effort Logger");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20)); // Adding padding around the entire layout

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        // Create box for Planning Poker (traditional poker colors)
        VBox planningPokerBox = createClickableBox("Planning Poker", Color.DARKGREEN, Color.LIGHTGREEN, Color.WHITE);
        planningPokerBox.setOnMouseClicked(e -> {
            PlanningPoker planningPoker = new PlanningPoker();
            planningPoker.start(new Stage());
        });

        // Create box for Effort Logger (complementary colors)
        VBox effortLoggerBox = createClickableBox("Effort Logger", Color.DARKORANGE, Color.LIGHTCORAL, Color.WHITE);
        effortLoggerBox.setOnMouseClicked(e -> {
            EffortLogger effortLogger = new EffortLogger();
            effortLogger.start(new Stage());
        });

        centerBox.getChildren().addAll(planningPokerBox, effortLoggerBox);

        root.setCenter(centerBox);

        primaryStage.setScene(new Scene(root, 600, 400)); // Larger window size
        primaryStage.show();
    }

    private VBox createClickableBox(String text, Color bgColor, Color borderColor, Color textColor) {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(250, 150); // Increase box size
        box.setStyle(
                "-fx-background-color: " + toRGBCode(bgColor) + "; " +
                "-fx-border-color: " + toRGBCode(Color.BLACK) + "; " +
                "-fx-border-width: 2px; " +
                "-fx-background-radius: 10; -fx-border-radius: 10;"); // Rounded corners
        Text label = new Text(text);
        label.setFill(textColor);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Larger and bold text
        box.getChildren().add(label);
        return box;
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
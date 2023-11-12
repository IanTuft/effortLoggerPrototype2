package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Page");

        // Create a GridPane Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create some text fields for user input
        Label userName = new Label("User Name:");
        TextField userTextField = new TextField();
        Label pw = new Label("Password:");
        PasswordField pwBox = new PasswordField();

        // Place nodes in the pane
        grid.add(userName, 0, 0);
        grid.add(userTextField, 1, 0);
        grid.add(pw, 0, 1);
        grid.add(pwBox, 1, 1);

        // Create a sign in button
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 2);

        // Create a action handler for the button
        btn.setOnAction(e -> {
            System.out.println("Sign in button pressed");
            // Here you would call your authentication logic
        });

        // Add everything to a scene and then to the stage
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

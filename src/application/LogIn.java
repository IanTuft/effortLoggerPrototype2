package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends Application {

    private List<User> userAccounts = new ArrayList<>();

    public static void logInPage(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Page");

        VBox loginBox = new VBox(10);
        loginBox.setMinSize(300, 200);
        loginBox.setStyle("-fx-background-color: #f0f0f0;");
        loginBox.setSpacing(10);

        TextField employeeIdField = new TextField();
        PasswordField passwordField = new PasswordField();
        Text errorText = new Text("");
        errorText.setFill(Color.RED);

        Button loginButton = new Button("Log In");
        loginButton.setOnAction(e -> {
            String employeeId = employeeIdField.getText();
            String password = passwordField.getText();
            if (employeeId.isEmpty() || password.isEmpty()) {
                errorText.setText("Please enter your information.");
            } else {
                if (isLoginValid(employeeId, password)) {
                    openMainApp();
                    primaryStage.close();
                } else {
                    errorText.setText("Invalid credentials. Please try again.");
                }
            }
        });

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> openSignUpPage());

        loginBox.getChildren().addAll(new Label("Employee ID:"), employeeIdField,
                new Label("Password:"), passwordField, loginButton, errorText, signUpButton);

        Scene scene = new Scene(loginBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openMainApp() {
        // Logic to open the MainApp
    	MainApp.launchApp();
    }

    private void openSignUpPage() {
        Stage signUpStage = new Stage();
        signUpStage.setTitle("Sign Up");

        VBox signUpBox = new VBox(10);
        signUpBox.setMinSize(300, 200);
        signUpBox.setStyle("-fx-background-color: #f0f0f0;");
        signUpBox.setSpacing(10);

        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField employeeIdField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField verifyPasswordField = new PasswordField();
        Text signUpErrorText = new Text("");
        signUpErrorText.setFill(Color.RED);

        Button signUpConfirmButton = new Button("Sign Up");
        signUpConfirmButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String employeeId = employeeIdField.getText();
            String password = passwordField.getText();
            String verifyPassword = verifyPasswordField.getText();

            if (firstName.isEmpty() || lastName.isEmpty() || employeeId.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()) {
                signUpErrorText.setText("Please fill in all fields.");
            } else if (!password.equals(verifyPassword)) {
                signUpErrorText.setText("Passwords do not match.");
            } else {
                try {
                    int empId = Integer.parseInt(employeeId);
                    
                    // Add the new account to the object here vvv
                    // something like this:
                    // userAccounts.add(new User(firstName, lastName, empId, password));
                    signUpErrorText.setFill(Color.GREEN);
                    signUpErrorText.setText("Account created successfully!");
                    signUpStage.close(); // Close the Sign Up window on successful account creation
                } catch (NumberFormatException ex) {
                    signUpErrorText.setText("Employee ID must be a number.");
                }
            }
        });

        // Text formatter to allow only integer input for Employee ID
        TextFormatter<Integer> intFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        });

        employeeIdField.setTextFormatter(intFormatter);

        signUpBox.getChildren().addAll(
                new Label("First Name:"), firstNameField,
                new Label("Last Name:"), lastNameField,
                new Label("Employee ID:"), employeeIdField,
                new Label("Password:"), passwordField,
                new Label("Verify Password:"), verifyPasswordField,
                signUpConfirmButton,
                signUpErrorText);

        Scene signUpScene = new Scene(signUpBox);
        signUpStage.setScene(signUpScene);
        signUpStage.show();
    }

    private boolean isLoginValid(String employeeId, String password) {
        // Check if the provided login credentials are valid here vvv
    	
        return false;
    }
}

class User {
    private String employeeId;
    private String password;

    // Constructor, getters, and setters for user details (employeeId, password)
}

package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LogIn extends Application {
    private Stage primaryStage;

    private Runnable loginSuccessCallback;
    
    private ProcessInput processInput = new ProcessInput();

    private LinkedListManager lm;

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Overrides the start method in Application, setting up the initial stage for the log in.
     *
     * @param primaryStage The primary stage for the Log in.
     */

    @Override
    public void start(Stage primaryStage) {
    	loginPage(primaryStage, lm);
    }
    
 // Method to set up the login page
    public void loginPage(Stage primaryStage, LinkedListManager lm) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Login Page");

        // Button to close the program
        Button exitButton = new Button("EXIT");

        VBox loginBox = new VBox(10);
        loginBox.setMinSize(300, 200);
        loginBox.setStyle("-fx-background-color: #f0f0f0;");
        loginBox.setSpacing(10);

        TextField employeeIdField = new TextField();
        PasswordField passwordField = new PasswordField();
        Text errorText = new Text("");
        errorText.setFill(Color.RED);

        Button loginButton = new Button("Log In");
        Button signUpButton = new Button("Sign Up");
        
        exitButton.setOnAction(e -> {
        	
        	Main.llm.save(); //Save data
        	System.exit(0); //Exit
        	
        });

        // Add elements to the login box
        loginBox.getChildren().addAll(
                exitButton,
        		new Label("Employee ID:"), employeeIdField,
                new Label("Password:"), passwordField,
                loginButton,
                signUpButton,
                errorText);

        Scene scene = new Scene(loginBox);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Action for the login button
        loginButton.setOnAction(e -> handleLogin(employeeIdField, passwordField, errorText));

        // Action for the sign-up button. Calls openSignUpPage function
        signUpButton.setOnAction(e -> openSignUpPage());
    }

    private void handleLogin(TextField employeeIdField, PasswordField passwordField, Text errorText) {
        String employeeId = employeeIdField.getText();
        String password = passwordField.getText();
        if (employeeId.isEmpty() || password.isEmpty()) {
            errorText.setText("Please enter your information.");
        } else {
            if (isLoginValid(employeeId, password)) {
                System.out.println("Login successful!"); // Debug print
                if (loginSuccessCallback != null) {
                    loginSuccessCallback.run();
                }
                primaryStage.close();
            } else {
                System.out.println("Login failed."); // Debug print
                errorText.setText("Invalid credentials. Please try again.");
               
            }
        }
    }
    
    // Method to open the sign-up page
    private void openSignUpPage() {
    	
    	Text duplicateEmployee = new Text();
    	duplicateEmployee.setFill(Color.RED);
    	
        Stage signUpStage = new Stage();
        signUpStage.setTitle("Sign Up");

        VBox signUpBox = new VBox(10);
        signUpBox.setMinSize(300, 200);
        signUpBox.setStyle("-fx-background-color: #f0f0f0;");
        signUpBox.setSpacing(10);

    	// Fields for sign-up information
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField employeeIdField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField verifyPasswordField = new PasswordField();

        Button signUpConfirmButton = new Button("Sign Up");
        signUpConfirmButton.setOnAction(e -> {

        	//Check that the employee ID is unique to avoid duplicate employees
        	if(!Main.llm.checkDuplicateEmployee(processInput.processInt(employeeIdField.getText(), 9))) {
        		
        		//Add the new employee
            	Main.llm.addNewEmployeeLogin(firstNameField.getText(), lastNameField.getText(), 
            			processInput.processInt(employeeIdField.getText(), 9), passwordField.getText());
            	
            	signUpStage.close(); //Close the sign up screen
        		
        	}
        	else { //Error if employee already exists
        		
        		duplicateEmployee.setText("Employee already exists.");
        		
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
        // Add elements to the sign-up box
        signUpBox.getChildren().addAll(
                new Label("First Name:"), firstNameField,
                new Label("Last Name:"), lastNameField,
                new Label("Employee ID:"), employeeIdField,
                new Label("Password:"), passwordField,
                new Label("Verify Password:"), verifyPasswordField,
                signUpConfirmButton,
                duplicateEmployee);

        Scene signUpScene = new Scene(signUpBox);
        signUpStage.setScene(signUpScene);
        signUpStage.show();
    }

    // Method to set the login success callback
    public void setLoginSuccessCallback(Runnable callback) {
        this.loginSuccessCallback = callback;
    }

    /**
     * Checks that the given employee login credentials are valid
     * @param employeeId ID to check
     * @param password Password associated with the given ID to check
     * @return Returns true if the login is valid. Returns false otherwise.
     */
    private boolean isLoginValid(String employeeId, String password) {
    	
    	boolean credentials = false;
    	
    	//Check credentials and log the user in
    	credentials = Main.llm.lockUser(processInput.processInt(employeeId, 9), password);
    	
    	return credentials;
    	
    }
}
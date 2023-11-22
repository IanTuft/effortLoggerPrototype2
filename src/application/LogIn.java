package application;

import javafx.application.Application;
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
    
    //Andrew's Work Zone
    private ProcessInput processInput = new ProcessInput();
    //End Andrew's Work Zone

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Login Page");
        
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
        
        //Andrew's Work Zone
        exitButton.setOnAction(e -> {
        	
        	Main.llm.save();
        	System.exit(0);
        	
        });
        //End Andrew's Work Zone

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

        loginButton.setOnAction(e -> {
            String employeeId = employeeIdField.getText();
            String password = passwordField.getText();
            if (employeeId.isEmpty() || password.isEmpty()) {
                errorText.setText("Please enter your information.");
            } else {
                if (isLoginValid(employeeId, password)) {
                    if (loginSuccessCallback != null) {
                        loginSuccessCallback.run();
                    }
                    primaryStage.close();
                } else {
                    errorText.setText("Invalid credentials. Please try again.");
                }
            }
        });

        signUpButton.setOnAction(e -> openSignUpPage());
    }

    private void openSignUpPage() {
    	
    	Text duplicateEmployee = new Text();
    	duplicateEmployee.setFill(Color.RED);
    	
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

        Button signUpConfirmButton = new Button("Sign Up");
        signUpConfirmButton.setOnAction(e -> {

        	//Andrew's Work Zone
        	if(!Main.llm.checkDuplicateEmployee(processInput.processInt(employeeIdField.getText(), 9))) {
        		
            	Main.llm.addNewEmployeeLogin(firstNameField.getText(), lastNameField.getText(), 
            			processInput.processInt(employeeIdField.getText(), 9), passwordField.getText());
            	
            	signUpStage.close();
        		
        	}
        	else {
        		
        		duplicateEmployee.setText("Employee already exists.");
        		
        	}
        	//End Andrew's Work Zone
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
                duplicateEmployee);

        Scene signUpScene = new Scene(signUpBox);
        signUpStage.setScene(signUpScene);
        signUpStage.show();
    }

    public void setLoginSuccessCallback(Runnable callback) {
        this.loginSuccessCallback = callback;
    }

    private boolean isLoginValid(String employeeId, String password) {
    	
    	//Andrew's Work Zone
    	boolean credentials = false;
    	
    	credentials = Main.llm.lockUser(processInput.processInt(employeeId, 9), password);
    	
    	return credentials;
    	//End Andrew's Work Zone
    }
}
package application;

import java.util.Scanner; //import

public class Example {

	public static void main(String[] args) {
		
		//This is for setup for the demonstration
		UserNode firstEmployee = new UserNode("Employee Test 1", 1);
		ProjectNode firstProject = new ProjectNode("Project Test 1");
		UserNode secondEmployee = new UserNode("Employee Test 2", 2);
		ProjectNode secondProject = new ProjectNode("Project Test 2");
		
		firstEmployee.setNext(secondEmployee);
		firstProject.setNext(secondProject);
		
		//Initialize the linked list manager here
		LinkedListManager llm = new LinkedListManager(firstEmployee, firstProject);
		
		llm.addNewData("Project Test 1", 10, 1, "Test1");
		llm.lockUser(1);
		System.out.println("Loged in");
		llm.addNewData("Project Test 1", 10, 1, "Test1");
		System.out.println("test 1 added");
		llm.addNewData("Project Test 1", 11, 2, "Test1");
		llm.addNewData("Project Test 1", 12, 3, "Test1");
		llm.addNewData("Project Test 1", 13, 4, "Test1");
		llm.addNewData("Project Test 1", 14, 5, "Test1");
		//llm.addNewEmployee("Employee Test 3", 3);
		//llm.addNewProject("Project Test 3");
		System.out.println("block complete");
		
		llm.addNewData("Project Test 1", 15, 6, "Test2");
		llm.addNewData("Project Test 1", 15, 6, "Test2");
		System.out.println("there");
		llm.addNewData("Project Test 1", 16, 7, "Test3");
		System.out.println("here");
		llm.unlockUser();
		
		System.out.println("Setup Complete.");
		//Setup ends here
		
		//Variables for controlling the main menu
		int loopControl = 0;
		int userInput = 0;
		
		//Objects used in the main menu
		Scanner scan = new Scanner(System.in);
		ProcessInput processInput = new ProcessInput();
		
		//Main menu loop
		while(loopControl == 0) {
		
			System.out.println("\nMain menu. \nPlease enter 1 to sign in, 2 to sign out, 3 to view project data, \n"
					+ "4 to add project data, 5 to create a new project, 6 to create a new employee, \n"
					+ "7 to view employee data, or 8 to exit.");
			userInput = processInput.processInt(scan.nextLine(), 1);
			
			switch(userInput) {
			
			case 1:
				System.out.println("Login in process not currently configured. Please enter employee ID to sign in.");
				llm.lockUser(userInput = processInput.processInt(scan.nextLine(), 9));
				break;
			case 2:
				System.out.println("Logout successful.");
				llm.unlockUser();
				break;
			case 3:
				System.out.println("Viewing project data. Please enter the name of a project to view.");
				llm.accessProjectData(processInput.processString(scan.nextLine(), 30));
				break;
			case 4:
				llm.addNewProjectData();
				break;
			case 5:
				System.out.println("Please enter a name for the new project that is up to 30 characters long and contains"
						+ "only English alphanumeric characters.");
				llm.addNewProject(processInput.processString(scan.nextLine(), 30));
				break;
			case 6:
				int employeeID = 0;
				String employeeName = null;
				System.out.println("Please enter a name for the new employee that is up to 30 characters long and contains"
						+ "only English alphanumeric characters.");
				employeeName = processInput.processString(scan.nextLine(), 30);
				System.out.println("Please enter the new employee ID that is up to 9 characters long and an integer.");
				employeeID = processInput.processInt(scan.nextLine(), 9);
				llm.addNewEmployee(employeeName, employeeID);
				break;
			case 7:
				llm.accessUserData();
				break;
			case 8:
				System.out.println("Exit successful.");
				loopControl = 1;
				break;
			case 9:
				llm.searchUserData();
				break;
			case 0:
				System.out.println("Invalid input. Please input a listed menu input.");
				break;
			default:
				System.out.println("Error in processing your request. Program will now exit.");
				loopControl = 1;
				break;
			
			}
		
		}
		
		scan.close();

	}

}

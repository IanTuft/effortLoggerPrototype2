package application;

import java.util.Scanner;

public class LinkedListManager {
	
	private UserNode currentUser; //for who is logged in presently
	private UserNode userNodeHead; //for managing the linked list of employees
	private ProjectNode projectNodeHead; //for managing the linked list of projects
	private int locked; //for if someone is currently logged in
	private ProcessInput processInput = new ProcessInput(); //to process input for bad input
	private Scanner scan = new Scanner(System.in); //scanner to receive input
	private SearchData searchData = new SearchData();
	private StoreData save;
	private int projectCount;
	private int employeeCount;
	
	//Default constructor. Use this one.
	public LinkedListManager() {
		
		currentUser = null;
		userNodeHead = null;
		projectNodeHead = null;
		locked = 0;
		
	}
	
	//Additional Constructors
	//N/A

	
	//Public Methods
	
	public void save() {
		
		save = new StoreData();
		
		save.saveUsers(userNodeHead);
		save.saveProject(projectNodeHead);
		
	}
	
	/**
	 * To add a new employee. Added to front of linked list.
	 * @param employeeNameIn Name of employee. Has built in input checking.
	 * @param employeeIDIn ID number of employee. No input checking.
	 * @param passwordIn Password of employee. Has built in input checking.
	 */
	public void addNewEmployee(String employeeNameIn, int employeeIDIn, String passwordIn) {
		
		String employeeName = processInput.processString(employeeNameIn, 30);
		int employeeID = employeeIDIn;	
		String password = processInput.processString(passwordIn, 30);
		
		if(userNodeHead == null) {
			
			UserNode firstUser = new UserNode(employeeName, employeeID, password);
			userNodeHead = firstUser;
			
			employeeCount++;
			
		}
		else {
		
			UserNode newEmployee = new UserNode(employeeName, employeeID, password);
			userNodeHead.setPrevious(newEmployee);
			newEmployee.setNext(userNodeHead);
			userNodeHead = newEmployee;
			
			employeeCount++;
		
		}
		
	}
	
	/**
	 * To add a new employee for the login screen. Combines first name and last name into one string.
	 * @param firstName
	 * @param lastName
	 * @param employeeIDIn
	 * @param passwordIn
	 */
	public void addNewEmployeeLogin(String firstName, String lastName, int employeeIDIn, String passwordIn) {
		
		String employeeName = processInput.processString(firstName, 30) + " " + processInput.processString(lastName, 30);
		int employeeID = employeeIDIn;	
		String password = processInput.processString(passwordIn, 30);
		
		if(userNodeHead == null) {
			
			UserNode firstUser = new UserNode(employeeName, employeeID, password);
			userNodeHead = firstUser;
			
			employeeCount++;
			
		}
		else {
		
			UserNode newEmployee = new UserNode(employeeName, employeeID, password);
			userNodeHead.setPrevious(newEmployee);
			newEmployee.setNext(userNodeHead);
			userNodeHead = newEmployee;
			
			employeeCount++;
		
		}
		
	}
	
	public void addNewStory(String projectNameIn, String storyTitleIn, String storyIn) {
		
		String projectName = processInput.processString(projectNameIn, 30);
		ProjectNode foundProject = findProject(projectName);
		
		UserStory newStory = new UserStory(projectNameIn, storyTitleIn, storyIn);
		
		foundProject.addUserStory(newStory);
		
	}
	
	/**
	 * To add a new project. Added to front of linked list.
	 * Used by ReadData to load the saved project data.
	 * @param projectName Name of project. Has built in input checking.
	 * @param employeeCount Number of employees on project. No input checking.
	 * @param storyCount Number of user stories in project. No input checking.
	 */
	public void addNewProject(String projectNameIn, int employeeCountIn, int storyCountIn) {
		
		String projectName = processInput.processString(projectNameIn, 30);
		int employeeCount = employeeCountIn;
		int storyCount = storyCountIn;
		
		
		if(projectNodeHead == null) {
			
			projectCount++;
			
			ProjectNode firstProject = new ProjectNode(projectName);
			firstProject.setEmployeeCount(employeeCount);
			firstProject.setStoryCount(storyCount);
			firstProject.setProjectID(projectCount);
			projectNodeHead = firstProject;
			
		}
		else {
		
			projectCount++;
			
			ProjectNode loadProject = new ProjectNode(projectName);
			loadProject.setEmployeeCount(employeeCount);
			loadProject.setStoryCount(storyCount);
			loadProject.setProjectID(projectCount);
			projectNodeHead.setPrevious(loadProject);
			loadProject.setNext(projectNodeHead);
			projectNodeHead = loadProject;
		
		}
		
	}
	
	public String getProjectName(int projectID) {
		
		ProjectNode temp = projectNodeHead;
		
		if(temp != null) {
			
			if(temp.getNext() == null && temp.getProjectID() == projectID) {
				
				return temp.getProjectName();
				
			}
			
			while(temp.getNext() != null) {
				
				if(temp.getProjectID() == projectID) {
					
					return temp.getProjectName();
					
				}
				else {
					
					temp = temp.getNext();
					
				}
				
			}
			
			if(temp.getNext() == null && temp.getProjectID() == projectID) {
				
				return temp.getProjectName();
				
			}
			
		}
		
		return null;
		
	}
	
	public boolean checkDuplicateProject(String nameToCheck) {
		
		ProjectNode findProject = projectNodeHead;
		
		if(findProject != null) {
			
			if(findProject.getNext() == null && findProject.getProjectName().equals(nameToCheck)) {
				
				return true;
				
			}
			
			while(findProject.getNext() != null) {
				
				if(findProject.getProjectName().equals(nameToCheck)) {
					
					return true;
					
				}
				else {
					
					findProject = findProject.getNext();
					
				}
				
			}
			
			if(findProject.getNext() == null && findProject.getProjectName().equals(nameToCheck)) {
				
				return true;
				
			}
		
		}
		
		return false;
		
	}
	
	/**
	 * To add data to a project. Can only be done while logged into any user as this also adds
	 * the data to the user who is logged in.
	 * No input checking as all input should be coming from a safe source.
	 * Asks for Project Name to know which project to add to.
	 * @param name Name of project.
	 * @param logNumber Log number.
	 * @param duration Duration to be logged.
	 * @param date Date of log.
	 * @param startTime Start time of log.
	 * @param endTime End time of log.
	 * @param lifeCycleStep Life cycle step of log.
	 * @param effortCategory Effort category of log.
	 * @param etc Various.
	 * @param primaryTag First tag.
	 * @param secondaryTag Second tag.
	 * @param additionalTag Third tag.
	 * @return returns 1 for success, 0 otherwise.
	 */
	public int addNewData(String name, int logNumber, int duration, String date, String startTime, 
			String endTime, String lifeCycleStep, String effortCategory, String etc, String primaryTag,
			String secondaryTag, String additionalTag) {
		
		ProjectNode findProject = projectNodeHead;
		
		if(locked == 0) {
			
			System.out.println("Error. Please login to an account first.");
			
			return 0;
			
		}
		else {
			
			if(findProject.getNext() == null && findProject.getProjectName().equals(name)) {
				
				currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
						lifeCycleStep, effortCategory, etc);
				findProject.addNewData(name, logNumber, duration, date, startTime, endTime,
						lifeCycleStep, effortCategory, etc);
				
				return 1;
				
			}
			
			while(findProject.getNext() != null) {
				
				if(findProject.getProjectName().equals(name)) {
					
					currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
							lifeCycleStep, effortCategory, etc);
					findProject.addNewData(name, logNumber, duration, date, startTime, endTime,
							lifeCycleStep, effortCategory, etc);
					
					return 1;
					
				}
				else
					findProject.getNext();
			
			}
			
			if(findProject.getNext() == null && findProject.getProjectName().equals(name)) {
				
				currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
						lifeCycleStep, effortCategory, etc);
				findProject.addNewData(name, logNumber, duration, date, startTime, endTime,
						lifeCycleStep, effortCategory, etc);
				
				return 1;
				
			}
			
			System.out.println("No project with name: " + name + " found. Please try a new name or create a project with that name.\n");
			
			return 0;
			
		}			
		
	}
	
	/**
	 * Log in as a user. Prevents logging in as other users until logged out.
	 * Has no input scrub. Assumes that the eventual log-in mechanic will do the input scrub.
	 * @param employeeID ID to log in as.
	 */
	public boolean lockUser(int employeeID, String password) {
		
		return lockUserPrivate(employeeID, password);
		
	}
	
	/**
	 * Logout. Does nothing if not logged in.
	 */
	public void unlockUser() {
		
		unlockUserPrivate();
		
	}
	
	/**
	 * Access data of a project by name. Displays one data node at a time.
	 * @param projectName Name to access.
	 */
	public void accessProjectData(String projectName) {
		
		ProjectNode foundProject = findProject(projectName);
		
		if(foundProject == null)
			System.out.println("Invalid project name. Please try a different name.");
		else
			viewProjectData(foundProject);
		
	}
	
	/**
	 * Access data of a user. Only works if logged in. Displays one data node at a time.
	 */
	public void accessUserData() {
		
		if(locked == 0) {
			
			System.out.println("Error. Please login to an account first.");
			
		}
		else {
			
			if(currentUser == null)
				System.out.println("Invalid user. Please try a different user.");
			else
				viewUserData(currentUser);
			
		}
		
	}
	
	/**
	 * Starts the search functionality.
	 * Use instead of calling SearchData directly.
	 * Serves as the way to call the search and the logic handler.
	 * Has no actual functionality.
	 */
	public void searchUserData(String projectName, String lifecycle, String effort, 
			String projectNameDefault, String lifecycleDefault, String effortDefault) {
		
		DataNode searchedData = null;
		
		if(locked == 0) {
			
			System.out.println("Error. Please login to an account first.");
			
		}
		else {
			
			if(currentUser == null)
				System.out.println("Invalid user. Please try a different user.");
			else {
				
				if(projectName != null) {
					
					searchData.setDataNode(currentUser.getDataHead());
					searchedData = searchData.findLifecycles(lifecycle);
					
				}
				if(lifecycle != null) {
					
					searchData.setDataNode(searchedData);
					searchedData = searchData.findLifecycles(lifecycle);
					
				}
				if(effort != null) {
					
					searchData.setDataNode(searchedData);
					searchedData = searchData.findLifecycles(lifecycle);
					
				}
				
				viewData(searchedData);
				
			}
			
		}
		
	}
	
	
	//Support Methods
	
	/**
	 * Log in as a user.
	 * Support method. Has no input scrub.
	 * Does the password check.
	 * @param employeeID User to log in as.
	 */
	private boolean lockUserPrivate(int employeeID, String password) {
		
		if(locked == 0) {
			
			currentUser = findUser(employeeID);
			
			if(currentUser != null) {
			
				if(!(currentUser.passwordCheck(password))) {
					
					currentUser = null;
					System.out.println("Invalid password.");
					
					return false;
					
				}
				
				return true;
				
			}
			
			return false;
		}
		else {
			
			System.out.println("Already logged into user " + currentUser.getEmployeeName() + ". Please logout"
					+ "before attempting to access a different user.");
			
			return false;
			
		}
		
	}
		
	
	/**
	 * Given an employee ID, sets the current user to the user associated with the ID.
	 * The back-end log-in mechanic.
	 * @param employeeID ID to look for.
	 */
	private UserNode findUser(int employeeID) {
		
		UserNode foundUser = userNodeHead;
		int findSuccess = 0;
		
		if(foundUser != null) {
			
			if(foundUser.getNext() == null && foundUser.getEmployeeID() == employeeID) {
				
				findSuccess = 1;
				locked = 1;
				System.out.println("Log in with user ID: " + foundUser.getEmployeeID() + " successful.");
				
				return foundUser;
				
			}

			while(foundUser.getNext() != null && findSuccess == 0) { //Search for user in the linked list.
				
				if(foundUser.getEmployeeID() == employeeID){
					
					findSuccess = 1;
					locked = 1;
					System.out.println("Log in with user ID: " + foundUser.getEmployeeID() + " successful.");
					
					return foundUser;
					
				}
				else
					foundUser = foundUser.getNext();
				
			}
			
			if(foundUser.getEmployeeID() == employeeID) {
				
				findSuccess = 1;
				locked = 1;
				System.out.println("Log in with user ID: " + foundUser.getEmployeeID() + " successful.");
				
				return foundUser;
				
			}
		
		//If user doesn't exist.
		if(foundUser.getNext() == null && findSuccess == 0)
			System.out.println("The searched for user does not exist.");
		
		}
		
		return null;
		
	}
	
	/**
	 * Finds a project in the linked list of projects.
	 * Support method. No input scrub.
	 * @param projectName Project to look for.
	 * @return Returns the projectNode of interest.
	 */
	private ProjectNode findProject(String projectName) {
		
		ProjectNode foundProject = projectNodeHead;	
		
		while(foundProject.getNext() != null) {
			
			if(foundProject.getProjectName().compareTo(projectName) == 0)
				return foundProject;
			else
				foundProject = foundProject.getNext();
			
		}
		
		return null;
		
	}
	
	/**
	 * The logout support method.
	 */
	private void unlockUserPrivate() {
		
		currentUser = null;
		
		locked = 0;
		
	}
	
	/**
	 * Support method to view the data in a specified projectNode.
	 * No data scrub. A transfer method to convert from projectNode to dataNode.
	 * @param projectNodeTarget The project to access data from.
	 */
	private void viewProjectData(ProjectNode projectNodeTarget) {
			
			DataNode accessedData = projectNodeTarget.getDataHead();
			viewData(accessedData);
	}
	
	/**
	 * Support method to view the data in a specified userNode.
	 * No data scrub. A transfer method to convert from userNode to dataNode.
	 * @param userNodeTarget The project to access data from.
	 */
	private void viewUserData(UserNode userNodeTarget) {
			
			DataNode accessedData = userNodeTarget.getDataHead();
			viewData(accessedData);
		
	}
	
	/**
	 * The support method to view data in the target data chain.
	 * Has built in menu functions and input scrub.
	 * Views only the data chain of the passed in node.
	 * @param dataNodeTarget Beginning of linked list of dataNodes to view.
	 */
	private void viewData(DataNode dataNodeTarget) {
		
		DataNode accessedData = dataNodeTarget;
		int loopControl = 0;
		int userInput = 0;
		
		while(loopControl == 0) { //Menu loop
			
			if(accessedData == null)
				{System.out.println("No current data."); loopControl = 1;}
			else {
				System.out.println(accessedData.toString());
				System.out.println("Enter 1 for previous, 2 for next, 0 for exit.");
				//data to process, first read in, number of characters to return
				userInput = processInput.processInt(scan.nextLine(), 1);
				if(userInput == 0) {
					
					loopControl = 1;
					System.out.println("Exit successful.");
					
				}
				else if(userInput == 1) {
					
					if(accessedData.getPrevious() == null)
						System.out.println("No data entries this direction.");
					else
						accessedData = accessedData.getPrevious();
					
				}
				else if(userInput == 2) {
					
					if(accessedData.getNext() == null)
						System.out.println("No data entries this direction.");
					else
						accessedData = accessedData.getNext();
					
				}
				
			}
			
		}
		
	}
	
	
	//Getters and Setters
	//There are intentionally few of these to preserve the data security of this class.
	public UserNode getCurrentUser() {return currentUser;} //check who is currently logged in.
	public int getProjectCount() {return projectCount;}
	public int getEmployeeCount() {return employeeCount;}

}

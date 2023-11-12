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
	
	//Default constructor. Do not use.
	public LinkedListManager() {
		
		currentUser = null;
		userNodeHead = null;
		projectNodeHead = null;
		locked = 0;
		
		System.out.println("WARNING: Not connected to a Linked List. Most functions will not work normally.");
		
	}
	
	//Additional Constructors
	
	//Use this one always
	public LinkedListManager(UserNode userNodeIn, ProjectNode projectNodeIn) {
		
		currentUser = null;
		userNodeHead = userNodeIn;
		projectNodeHead = projectNodeIn;
		locked = 0;
		
	}
	
	
	
	
	//Public Methods
	
	/**
	 * In the event that the linked list manager is not connected to a list of employees, this can be used to connect it.
	 * @param listIn The list to connect to.
	 */
	public void connectUserList(UserNode listIn) {
		
		userNodeHead = listIn;
		
	}
	
	/**
	 * In the event that the linked list manager is not connected to a list of projects, this can be used to connect it.
	 * @param listIn The list to connect to.
	 */
	public void connectProjectList(ProjectNode listIn) {
		
		projectNodeHead = listIn;
		
	}
	
	/**
	 * To add a new employee. Added to front of linked list.
	 * @param employeeName Name of employee.
	 * @param employeeID ID number of employee.
	 */
	public void addNewEmployee(String employeeName, int employeeID) {
		
		UserNode newEmployee = new UserNode (employeeName, employeeID);
		userNodeHead.setPrevious(newEmployee);
		newEmployee.setNext(userNodeHead);
		userNodeHead = newEmployee;
		
	}
	
	/**
	 * To add a new project. Added to front of linked list.
	 * @param projectName Name of project.
	 */
	public void addNewProject(String projectName) {
		
		ProjectNode newProject = new ProjectNode (projectName);
		projectNodeHead.setPrevious(newProject);
		newProject.setNext(projectNodeHead);
		projectNodeHead = newProject;
		
	}
	
	public void loadNewProject(String projectName, int employeeCount, int storyCount) {
		
		ProjectNode loadProject = new ProjectNode(projectName);
		loadProject.setEmployeeCount(employeeCount);
		loadProject.setStoryCount(storyCount);
		projectNodeHead.setPrevious(loadProject);
		loadProject.setNext(projectNodeHead);
		projectNodeHead = loadProject;
		
	}
	
	/**
	 * To add data to a project. Can only be done while logged into any user as this also adds
	 * the data to the user who is logged in.
	 * Valid inputs are checked for with processInput.
	 * Asks for Project Name to know which project to add to, time, and defect count.
	 * @return returns 1 for success, 0 otherwise.
	 */
	public int addNewProjectData() {
		
		ProjectNode foundProject = null;
		String projectName = null;
		int logNumber = 0;
		int duration = 0;
		String date = null;
		String startTime = null;
		String endTime = null;
		String lifeCycleStep = null;
		String effortCategory = null;
		String etc = null;
		
		if(locked == 0) { //Not logged in.
			
			System.out.println("Error. Please login to an account first.");
			
			return 0;
			
		}
		else { //Find project to add data to.
			
			System.out.println("Please input the project name to add data to.");
			projectName = processInput.processString(scan.nextLine(), 30);
			foundProject = findProject(projectName);
			
			if(foundProject == null) {
				
				System.out.println("Error, please input an existing project name or create a new project with that name.");
				
			}
			else { //Add new information.
				
				s
				
				//Actually create the new data nodes.
				addNewProjectDataPrivate(projectName, logNumber, duration, date, startTime, endTime,
						lifeCycleStep, effortCategory, etc, foundProject);
				
				return 1;
				
			}
			
		}
		return 0;
	}
	
	/**
	 * Only used to initialize for the demo. Not for normal functional use. 
	 * Will be deleted later when not needed for demo.
	 * @param name Name of project
	 * @param time Time
	 * @param defect Defect count
	 * @return
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
			
			System.out.println("No project with name: " + name + " found. Please try a new name or create a project with that name.\n");
			
			return 0;
			
		}			
		
	}
	
	/**
	 * Log in as a user. Prevents logging in as other users until logged out.
	 * Has no input scrub. Assumes that the eventual log in mechanic will do the input scrubbing.
	 * @param employeeID ID to log in as.
	 */
	public void lockUser(int employeeID) {
		
		lockUserPrivate(employeeID);
		
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
	
	public void searchUserData() {
		
		String searchTarget = "";
		
		if(locked == 0) {
			
			System.out.println("Error. Please login to an account first.");
			
		}
		else {
			
			if(currentUser == null)
				System.out.println("Invalid user. Please try a different user.");
			else {
				
				System.out.println("Please enter tag to search for.");
				
				searchTarget = processInput.processString(scan.nextLine(), 30);
				
				search(currentUser, searchTarget);
				
			}
			
		}
		
	}
	
	
	//Support Methods
	
	private void search(UserNode currentUser, String searchTarget) {
		
		DataNode searchedData = null;
		
		searchData.setDataNode(currentUser.getDataHead());
		
		searchedData = searchData.pullTags(searchTarget);
		
		viewData(searchedData);
		
	}
	
	/**
	 * Adds new data to the given project and current user.
	 * Support method. Has no input scrub.
	 * @param projectName Name of project to add to.
	 * @param foundProject Project object to add to.
	 * @param time Time to add.
	 * @param defect Defect count to add.
	 */
	private void addNewProjectDataPrivate(String name, int logNumber, int duration, String date, String startTime, 
			String endTime, String lifeCycleStep, String effortCategory, String etc, ProjectNode foundProject) {
		
		currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
				lifeCycleStep, effortCategory, etc);
		foundProject.addNewData(name, logNumber, duration, date, startTime, endTime,
				lifeCycleStep, effortCategory, etc);
		
	}
	
	/**
	 * Log in as a user.
	 * Support method. Has no input scrub.
	 * @param employeeID //User to log in as.
	 */
	private void lockUserPrivate(int employeeID) {
		
		if(locked == 0)
			findUser(employeeID);
		else
			System.out.println("Already logged into user " + currentUser.getEmployeeName() + ". Please logout"
					+ "before attempting to access a different user.");
		
	}
	
	/**
	 * Given an employee ID, sets the current user to the user associated with the ID.
	 * The log-in mechanic at present.
	 * @param employeeID ID to look for.
	 */
	private void findUser(int employeeID) {
		
		UserNode foundUser = userNodeHead;
		int findSuccess = 0;

		while(foundUser.getNext() != null && findSuccess == 0) { //Search for user in the linked list.
			
			if(foundUser.getEmployeeID() == employeeID){
				
				findSuccess = 1;
				locked = 1;
				System.out.println("Log in with user ID: " + foundUser.getEmployeeID() + " successful.");
				
			}
			else
				foundUser = foundUser.getNext();
			
		}
		
		//If user doesn't exist.
		if(foundUser.getNext() == null && findSuccess == 0)
			System.out.println("The searched for user does not exist.");
		
		currentUser = foundUser;
		
	}
	
	/**
	 * Finds a project in the linked list of projects.
	 * Support method. No input scrub.
	 * @param projectName Project to look for.
	 * @return
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

}

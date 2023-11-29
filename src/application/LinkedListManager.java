package application;

public class LinkedListManager {
	
	private UserNode currentUser; //for who is logged in presently
	private UserNode userNodeHead; //for managing the linked list of employees
	private ProjectNode projectNodeHead; //for managing the linked list of projects
	private int locked; //for if someone is currently logged in
	private ProcessInput processInput = new ProcessInput(); //to process input for bad input
	private SearchData searchData = new SearchData(); //To search data
	private StoreData save; //To save data. DO NOT INITIALIZE UNTIL READY TO SAVE DATA. OVERWRITES FILES.
	private int projectCount; //Number of projects.
	private int employeeCount; //Number of employees.
	
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
	
	/**
	 * Saves all the data managed by the LinkedListManager in preparation of program shutdown.
	 * Overwrites previously written data to prevent duplication of data.
	 */
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
		
		if(userNodeHead == null) { //If no employees were in the list previously
			
			UserNode firstUser = new UserNode(employeeName, employeeID, password);
			userNodeHead = firstUser;
			
			employeeCount++;
			
		}
		else { //If at least one employee was in the list previously
		
			UserNode newEmployee = new UserNode(employeeName, employeeID, password);
			userNodeHead.setPrevious(newEmployee);
			newEmployee.setNext(userNodeHead);
			userNodeHead = newEmployee;
			
			employeeCount++;
		
		}
		
	}
	
	/**
	 * To add a new employee for the login screen. Combines first name and last name into one string.
	 * Otherwise functions identically to addNewEmployee.
	 * @param firstName First name of employee.
	 * @param lastName Last name of employee.
	 * @param employeeIDIn ID of employee.
	 * @param passwordIn Password of employee.
	 */
	public void addNewEmployeeLogin(String firstName, String lastName, int employeeIDIn, String passwordIn) {
		
		String employeeName = processInput.processString(firstName, 30) + " " + processInput.processString(lastName, 30);
			//Combines the first and last name into a single String
		
		int employeeID = employeeIDIn;	
		String password = processInput.processString(passwordIn, 30);
		
		if(userNodeHead == null) { //If no employees were in the list previously
			
			UserNode firstUser = new UserNode(employeeName, employeeID, password);
			userNodeHead = firstUser;
			
			employeeCount++;
			
		}
		else { //If at least one employee was in the list previously
		
			UserNode newEmployee = new UserNode(employeeName, employeeID, password);
			userNodeHead.setPrevious(newEmployee);
			newEmployee.setNext(userNodeHead);
			userNodeHead = newEmployee;
			
			employeeCount++;
		
		}
		
	}
	
	/**
	 * Checks by employee ID for if the given ID already exists.
	 * @param idCheck ID to check for.
	 * @return Returns true if there is a duplicate. Returns false if there is no duplicate.
	 */
	public boolean checkDuplicateEmployee(int idCheck) {
		
		UserNode findUser = userNodeHead;
		
		if(findUser != null) { //Ensure there is at least one employee. There can be no duplicates if there are no
								//employees so returns false if there are no employees.
			
			//Edge Case: There is only one employee and it is a match.
			if(findUser.getNext() == null && findUser.getEmployeeID() == idCheck) {
				
				return true;
				
			}
			
			//Edge Case: There are multiple employees.
			while(findUser.getNext() != null) {
				
				if(findUser.getEmployeeID() == idCheck) { //If there is a match, return true.
					
					return true;
					
				}
				else {
					
					findUser = findUser.getNext();
					
				}
				
			}
			
			//Edge Case: There are multiple employees and we need to check the last one in the list.
			if(findUser.getNext() == null && findUser.getEmployeeID() == idCheck) {
				
				return true;
				
			}
		
		}
		
		return false;
		
	}
	
	/**
	 * To add a new project. Added to the front of the linked list.
	 * Each project is auto-assigned a project ID based on the project count.
	 * This ID is used when pulling project names for populating the project ComboBoxes.
	 * @param projectNameIn Name of project to be added.
	 */
	public void addNewProject(String projectNameIn) {
		
		String projectName = processInput.processString(projectNameIn, 30);		
		
		if(projectNodeHead == null) { //If there are no other projects in the list yet
			
			projectCount++;
			
			ProjectNode firstProject = new ProjectNode(projectName);
			firstProject.setProjectID(projectCount);
			projectNodeHead = firstProject;
			
		}
		else { //If there is at least one project in the list
		
			projectCount++;
			
			ProjectNode loadProject = new ProjectNode(projectName);
			loadProject.setProjectID(projectCount);
			projectNodeHead.setPrevious(loadProject);
			loadProject.setNext(projectNodeHead);
			projectNodeHead = loadProject;
		
		}
		
	}
	
	/**
	 * Gets how many logs the currently logged in user has generated under the given project name.
	 * @param projectName The project name to check under.
	 * @return Returns an integer value of the number of previous logs.
	 */
	public int getLogCount(String projectName) {
		
		DataNode findData = userNodeHead.getDataHead();
		
		int out = 0;
		
		if(findData != null) { //If there is no previous data, then there are no previous logs so return 0.
			
			//Edge Case: There is one previous data entry and it matches the project of interest.
			if(findData.getNext() == null && findData.getProjectName().equals(projectName)) {
				
				out = 1;
				return out;
				
			}
			
			//Edge Case: There are multiple previous data entries and we need to find which ones match the project of interest.
			while(findData.getNext() != null) {
				
				if(findData.getProjectName().equals(projectName)) {
					
					out++;
					
				}
				
				findData = findData.getNext();
				
			}
			
			//Edge Case: There are multiple previous data entries and we need to check the last one in the list.
			if(findData.getNext() == null && findData.getProjectName().equals(projectName)) {
				
				out++;
				return out;
				
			}
			
		}
		
		return out;
		
	}
	
	/**
	 * Returns the project name of the matching project ID.
	 * Used to populate Project ComboBoxes.
	 * @param projectID Project ID to find the name of.
	 * @return The name of the project.
	 */
	public String getProjectName(int projectID) {
		
		ProjectNode temp = projectNodeHead;
		
		if(temp != null) { //Ensure there is at least one project.
			
			//Edge Case: There is only one project and it has the correct project ID.
			if(temp.getNext() == null && temp.getProjectID() == projectID) {
				
				return temp.getProjectName();
				
			}
			
			//Edge Case: There are multiple projects and we need to find the project with the correct ID.
			while(temp.getNext() != null) {
				
				if(temp.getProjectID() == projectID) {
					
					return temp.getProjectName();
					
				}
				else {
					
					temp = temp.getNext();
					
				}
				
			}
			
			//Edge Case: There are multiple projects and we need to check the last project in the list.
			if(temp.getNext() == null && temp.getProjectID() == projectID) {
				
				return temp.getProjectName();
				
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * Checks for duplicate project names.
	 * Returns true if there is a duplicate.
	 * @param nameToCheck Project name to check.
	 * @return Returns true if there is a duplicate. Returns false if there is no duplicate.
	 */
	public boolean checkDuplicateProject(String nameToCheck) {
		
		ProjectNode findProject = projectNodeHead;
		
		if(findProject != null) { //Ensure there is at least one project to check for.
									//If there is not, return false because there cannot be a duplicate.
			
			//Edge Case: There is one project and it has the matching name.
			if(findProject.getNext() == null && findProject.getProjectName().equals(nameToCheck)) {
				
				return true;
				
			}
			
			//Edge Case: There are multiple projects and one might have the matching name.
			while(findProject.getNext() != null) {
				
				if(findProject.getProjectName().equals(nameToCheck)) {
					
					return true;
					
				}
				else {
					
					findProject = findProject.getNext();
					
				}
				
			}
			
			//Edge Case: There are multiple projects and the last one in the list might have the matching name.
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
	 * @return returns 1 for success, 0 otherwise.
	 */
	public int addNewData(String name, int logNumber, int duration, String date, String startTime, 
			String endTime, String lifeCycleStep, String effortCategory) {
		
		ProjectNode findProject = projectNodeHead;
		
		if(locked == 0) { //Ensure we are logged in.
			
			System.out.println("Error. Please login to an account first.");
			
			return 0;
			
		}
		else {
			
			if(findProject == null) { //If there is no project with the matching name, nothing is saved.
				
				return 0;
				
			}
			
			//Edge Case: There is one project and it has the matching name.
			if(findProject.getNext() == null && findProject.getProjectName().equals(name)) {
				
				currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
						lifeCycleStep, effortCategory);
				
				return 1;
				
			}
			
			//Edge Case: There are multiple projects and one might have the matching name.
			while(findProject.getNext() != null) {
				
				if(findProject.getProjectName().equals(name)) {
					
					currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
							lifeCycleStep, effortCategory);
					
					return 1;
					
				}
				else
					findProject = findProject.getNext();
			
			}
			
			//Edge Case: There are multiple projects and the last one might have the matching name.
			if(findProject.getNext() == null && findProject.getProjectName().equals(name)) {
				
				currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
						lifeCycleStep, effortCategory);
				
				return 1;
				
			}
			
			System.out.println("No project with name: " + name + " found. "
					+ "Please try a new name or create a project with that name.\n");
			
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
	 * Given up to three parameters, searches for DataNodes with data matching.
	 * @param projectName Name of project to search for.
	 * @param lifecycle Life cycle to search for.
	 * @param effort Effort category to search for.
	 * @return Returns a linked list of DataNodes that all match all given filters.
	 */
	public DataNode searchUserData(String projectName, String lifecycle, String effort) {
		
		DataNode searchedData = null;
		
		if(locked == 0) { //Will not run if a user is not logged in.
			
			return null;
			
		}
		else {
			
			if(currentUser == null) { //Will not run if not logged into a real user.
				
				return null;
				
			}
			else {
				
				if(projectName != null) { //If there is a project name parameter
					
					searchData.setDataNode(currentUser.getDataHead());
					
					//searchedData now holds all DataNodes that match the project name given
					searchedData = searchData.findProjects(projectName);
					
				}
				if(lifecycle != null) { //If there is a life cycle parameter
					
					if(searchedData == null) { //This will trigger if there was no project name parameter given (could happen)
												//Or if no data matched the project name parameter (should not happen)
						
						searchData.setDataNode(currentUser.getDataHead());
						
						//searchedData now holds all DataNodes that match the life cycle given
						searchedData = searchData.findLifecycles(lifecycle);
						
					}
					else { //This will trigger if there was a project name parameter given and some data matched the project name
						
						//We search from the already reduced list created by searching by project name
						searchData.setDataNode(searchedData);
						
						//searchedData now holds all DataNodes that match the project name and the life cycle given
						searchedData = searchData.findLifecycles(lifecycle);
					
					}
					
				}
				if(effort != null) { //If there is an effort category parameter
					
					if(searchedData == null) { //This will trigger if there is no project name parameter given
												//and no life cycle parameter given or if no data matched the
												//combination of project name and life cycle parameters given
						
						searchData.setDataNode(currentUser.getDataHead());
						
						//searchedData now holds all DataNodes that match the effort category given.
						//If searchedData was passed into the previous line as null, searchedData is still null.
						searchedData = searchData.findEfforts(effort);
						
					}
					else { //This will trigger if there was a project name parameter given and some data matched the project name
							//Or if there was no project name parameter but there was a life cycle parameter and some data
							//matched the life cycle, or if there was both a project name parameter and a life cycle parameter
							//and some data matched both parameters
						
						//We search from the already reduced list created by searching by project name and/or life cycle
						searchData.setDataNode(searchedData);
						
						//searchedData now holds all DataNodes that match the project name and/or the life cycle given
						//and the effort category given
						searchedData = searchData.findEfforts(effort);
					
					}
					
				}

				return searchedData;
				
			}
			
		}
		
	}
	
	
	//Support Methods
	
	/**
	 * Log in as a user.
	 * Support method. Has no input scrub.
	 * Does the password check.
	 * @param employeeID User to log in as.
	 * @param password Password to check.
	 * @return Returns true if the password matches the password stored for the given employee ID. Returns false otherwise.
	 */
	private boolean lockUserPrivate(int employeeID, String password) {
		
		if(locked == 0) { //Can only login if not already logged in
			
			currentUser = findUser(employeeID); //Find the user that matches the given ID.
			
			if(currentUser != null) { //If the user exists
			
				if(!(currentUser.passwordCheck(password))) { //Check the password. If the password does not match:
					
					currentUser = null; //we do not log in.
					
					return false; //Return true if the user exists but the password does not match.
					
				}
				
				return true; //Return true if the password matches and the user exists.
				
			}
			
			return false; //Return false if the user does not exist.
		}
		else {
			
			return false; //Return false if the program is already logged into a user.
			
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
		
		if(foundUser != null) { //Checks there is at least one user in the linked list
			
			//Edge Case: There is one user in the list and it is the employee we are looking for.
			if(foundUser.getNext() == null && foundUser.getEmployeeID() == employeeID) {
				
				findSuccess = 1;
				locked = 1;
				
				return foundUser;
				
			}
			
			//Edge Case: There are multiple users in the list and one of them might be the employee we are looking for.
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
			
			//Edge Case: There are multiple users in the list and the last one might be the employee we are looking for.
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
	 * The logout support method.
	 */
	private void unlockUserPrivate() {
		
		currentUser = null;
		
		locked = 0;
		
	}
	
	
	//Getters and Setters
	//There are intentionally few of these to preserve the data security of this class.
	public UserNode getCurrentUser() {return currentUser;} //Check who is currently logged in.
	public int getProjectCount() {return projectCount;} //Get the number of projects.
	public int getEmployeeCount() {return employeeCount;} //Get the number of users.

}

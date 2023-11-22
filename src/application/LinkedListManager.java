package application;

public class LinkedListManager {
	
	private UserNode currentUser; //for who is logged in presently
	private UserNode userNodeHead; //for managing the linked list of employees
	private ProjectNode projectNodeHead; //for managing the linked list of projects
	private int locked; //for if someone is currently logged in
	private ProcessInput processInput = new ProcessInput(); //to process input for bad input
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
	
	public boolean checkDuplicateEmployee(int idCheck) {
		
		UserNode findUser = userNodeHead;
		
		if(findUser != null) {
			
			if(findUser.getNext() == null && findUser.getEmployeeID() == idCheck) {
				
				return true;
				
			}
			
			while(findUser.getNext() != null) {
				
				if(findUser.getEmployeeID() == idCheck) {
					
					return true;
					
				}
				else {
					
					findUser = findUser.getNext();
					
				}
				
			}
			
			if(findUser.getNext() == null && findUser.getEmployeeID() == idCheck) {
				
				return true;
				
			}
		
		}
		
		return false;
		
	}
	
	/**
	 * To add a new project. Added to front of linked list.
	 * Used by ReadData to load the saved project data.
	 * @param projectName Name of project. Has built in input checking.
	 * @param employeeCount Number of employees on project. No input checking.
	 * @param storyCount Number of user stories in project. No input checking.
	 */
	public void addNewProject(String projectNameIn) {
		
		String projectName = processInput.processString(projectNameIn, 30);		
		
		if(projectNodeHead == null) {
			
			projectCount++;
			
			ProjectNode firstProject = new ProjectNode(projectName);
			firstProject.setProjectID(projectCount);
			projectNodeHead = firstProject;
			
		}
		else {
		
			projectCount++;
			
			ProjectNode loadProject = new ProjectNode(projectName);
			loadProject.setProjectID(projectCount);
			projectNodeHead.setPrevious(loadProject);
			loadProject.setNext(projectNodeHead);
			projectNodeHead = loadProject;
		
		}
		
	}
	
	public int getLogCount(String projectName) {
		
		DataNode findData = userNodeHead.getDataHead();
		
		int out = 0;
		
		if(findData != null) {
			
			if(findData.getNext() == null && findData.getProjectName().equals(projectName)) {
				
				out = 1;
				return out;
				
			}
			
			while(findData.getNext() != null) {
				
				if(findData.getProjectName().equals(projectName)) {
					
					out++;
					
				}
				
				findData = findData.getNext();
				
			}
			
			if(findData.getNext() == null && findData.getProjectName().equals(projectName)) {
				
				out++;
				return out;
				
			}
			
		}
		
		return out;
		
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
	 * @return returns 1 for success, 0 otherwise.
	 */
	public int addNewData(String name, int logNumber, int duration, String date, String startTime, 
			String endTime, String lifeCycleStep, String effortCategory) {
		
		ProjectNode findProject = projectNodeHead;
		
		if(locked == 0) {
			
			System.out.println("Error. Please login to an account first.");
			
			return 0;
			
		}
		else {
			
			if(findProject == null) {
				
				return 0;
				
			}
			
			if(findProject.getNext() == null && findProject.getProjectName().equals(name)) {
				
				currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
						lifeCycleStep, effortCategory);
				
				return 1;
				
			}
			
			while(findProject.getNext() != null) {
				
				if(findProject.getProjectName().equals(name)) {
					
					currentUser.addNewData(name, logNumber, duration, date, startTime, endTime,
							lifeCycleStep, effortCategory);
					
					return 1;
					
				}
				else
					findProject = findProject.getNext();
			
			}
			
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
	 * Starts the search functionality.
	 * Use instead of calling SearchData directly.
	 * Serves as the way to call the search and the logic handler.
	 * Has no actual functionality.
	 */
	public DataNode searchUserData(String projectName, String lifecycle, String effort) {
		
		DataNode searchedData = null;
		System.out.println("Passed in: projectName: " + projectName + " lifecycle: " + lifecycle + " effort: " +effort);
		
		if(locked == 0) {
			
			return null;
			
		}
		else {
			
			if(currentUser == null) {
				
				return null;
				
			}
			else {
				
				if(projectName != null) {
					
					searchData.setDataNode(currentUser.getDataHead());
					searchedData = searchData.findProjects(projectName);
					
				}
				if(lifecycle != null) {
					
					if(searchedData == null) {
						
						searchData.setDataNode(currentUser.getDataHead());
						searchedData = searchData.findLifecycles(lifecycle);
						
					}
					else {
						
						searchData.setDataNode(searchedData);
						searchedData = searchData.findLifecycles(lifecycle);
					
					}
					
				}
				if(effort != null) {
					
					if(searchedData == null) {
						
						searchData.setDataNode(currentUser.getDataHead());
						searchedData = searchData.findEfforts(effort);
						
					}
					else {
						
						searchData.setDataNode(searchedData);
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
		
		if(foundProject != null) {
		
			if(foundProject.getNext() == null && foundProject.getProjectName().equals(projectName)) {
				
				return foundProject;
				
			}
			
			while(foundProject.getNext() != null) {
				
				if(foundProject.getProjectName().compareTo(projectName) == 0)
					return foundProject;
				else
					foundProject = foundProject.getNext();
				
			}
			
			if(foundProject.getNext() == null && foundProject.getProjectName().equals(projectName)) {
				
				return foundProject;
				
			}
		
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
	public UserNode getCurrentUser() {return currentUser;} //check who is currently logged in.
	public int getProjectCount() {return projectCount;}
	public int getEmployeeCount() {return employeeCount;}

}

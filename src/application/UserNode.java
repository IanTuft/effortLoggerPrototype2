package application;
//All functionality: Andrew Thomas
public class UserNode {
	
	private String employeeName; //Name of user.
	private int employeeID; //ID of user.
	private DataNode dataHead; //The head of the data linked list the user will be attached to.
	private UserNode next; //The next user in the linked list of users.
	private UserNode previous; //The previous user in the linked list of users.
	private String password; //The password of the user.
	
	//Default constructor. Never use.
	public UserNode() {
		
		employeeName = "NULL";
		employeeID = 0;
		dataHead = null;
		next = null;
		previous = null;
		password = null;
		
	}
	
	//Additional Constructors
	
	//Primary constructor. Always use.
	/**
	 * Always use this constructor.
	 * @param nameIn Name of employee. Combine first and last name into a single string.
	 * @param idIn ID of employee.
	 * @param passwordIn Password of employee.
	 */
	public UserNode(String nameIn, int idIn, String passwordIn) {
		
		employeeName = nameIn;
		employeeID = idIn;
		dataHead = null;
		next = null;
		previous = null;
		password = passwordIn;
		
	}
	
	
	//Getters and Setters
	public void setEmployeeName(String nameIn) {this.employeeName = nameIn;}
	public void setEmployeeID(int idIn) {this.employeeID = idIn;}
	public void setDataHead(DataNode dataNodeIn) {this.dataHead = dataNodeIn;}
	
	public String getEmployeeName() {return employeeName;}
	public int getEmployeeID() {return employeeID;}
	public DataNode getDataHead() {return dataHead;}
	
	public void setNext(UserNode nodeInNext) {this.next = nodeInNext;}
	public void setPrevious(UserNode nodeInPrevious) {this.previous = nodeInPrevious;}
	
	public UserNode getNext() {return next;}
	public UserNode getPrevious() {return previous;}
	
	
	//Additional methods
	
	/**
	 * Creates a new DataNode and adds it to this employee's linked list of data.
	 * @param projectName Name of project associated with the data.
	 * @param logNumber Log number of the data.
	 * @param duration How long the activity took to complete (in minutes).
	 * @param date Date of activity.
	 * @param startTime Start time of activity.
	 * @param endTime End time of activity.
	 * @param lifeCycleStep The life cycle step of the activity.
	 * @param effortCategory The effort category of the activity.
	 */
	public void addNewData(String projectName, int logNumber, int duration, String date, String startTime, 
			String endTime, String lifeCycleStep, String effortCategory) {
		
		if(dataHead == null) { //Only used if the user has no existing data
			
			DataNode projectData = new DataNode(projectName, logNumber, duration, date, startTime, endTime,
					lifeCycleStep, effortCategory); //Create a new DataNode
			dataHead = projectData; //Make it the head of the user's data linked list
			
		}
		else { //Used if the user has existing data
			
			DataNode projectData = new DataNode(projectName, logNumber, duration, date, startTime, endTime,
					lifeCycleStep, effortCategory); //Create a new DataNode
			dataHead.setPrevious(projectData); //Add it to the front of the data linked list
			projectData.setNext(dataHead); //Set it to point at the previous front of the linked list
			dataHead = projectData; //Reestablish the head of the linked list
		
		}
		
	}
	
	/**
	 * Checks if a given String matches the employee's password.
	 * @param passwordCheck Password to check.
	 * @return Returns true if the String matches. Returns false otherwise.
	 */
	public boolean passwordCheck(String passwordCheck) {
		
		boolean check = false;
		
		if(this.password.equals(passwordCheck))
			check = true;
		
		
		return check;
		
	}
	
	/**
	 *Saves the data stored in the node by outputting it as a String formatted to match what StoreData expects.
	 * @return Returns a String of specific format.
	 */
	public String save() {
		
		String out = "";
		
		out = this.employeeName + "\n" + this.employeeID + "\n" + this.password + "\n";
		
		return out;
		
	}

}

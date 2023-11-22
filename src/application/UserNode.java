package application;

public class UserNode {
	
	private String employeeName; //Name of user.
	private int employeeID; //ID of user.
	private DataNode dataHead; //The head of the data linked list the user will be attached to.
	private UserNode next; //The next user in the linked list of users.
	private UserNode previous; //The previous user in the linked list of users.
	private String password;
	
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
	 * Creates a new DataNode and adds it to the front of the linked list of dataNodes
	 * then adjusts the links.
	 * @param name Name of associated project.
	 * @param time Time to report.
	 * @param defect Defect count to report.
	 */
	public void addNewData(String projectName, int logNumber, int duration, String date, String startTime, 
			String endTime, String lifeCycleStep, String effortCategory) {
		
		if(dataHead == null) {
			
			DataNode projectData = new DataNode(projectName, logNumber, duration, date, startTime, endTime,
					lifeCycleStep, effortCategory);
			dataHead = projectData;
			
		}
		else {
			
			DataNode projectData = new DataNode(projectName, logNumber, duration, date, startTime, endTime,
					lifeCycleStep, effortCategory);
			dataHead.setPrevious(projectData);
			projectData.setNext(dataHead);
			dataHead = projectData;
		
		}
		
	}
	
	public boolean passwordCheck(String passwordCheck) {
		
		boolean check = false;
		
		if(this.password.equals(passwordCheck))
			check = true;
		
		
		return check;
		
	}
	
	public String save() {
		
		String out = "";
		
		out = this.employeeName + "\n" + this.employeeID + "\n" + this.password + "\n";
		
		return out;
		
	}

}

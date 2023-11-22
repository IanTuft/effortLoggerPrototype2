package application;

public class ProjectNode {
	
	private DataNode dataHead; //The head of the data linked list the project will be attached to.
	private ProjectNode next; //The next project in the linked list.
	private ProjectNode previous; //The previous project in the linked list.
	private String projectName; //The name of the project.
	private UserStory userStoryHead;
	private UserNode userNodeHead;
	private int employeeCount;
	private int storyCount;
	private int projectID;
	
	//Default constructor. Will not be used.
	public ProjectNode() {
		
		projectName = null;
		dataHead = null;
		userStoryHead = null;
		userNodeHead = null;
		employeeCount = 0;
		storyCount = 0;
		projectID = 0;
		
		next = null;
		previous = null;
		
	}
	
	//Additional Constructors
	//Always use this one.
	public ProjectNode(String nameIn) {
		
		projectName = nameIn;
		dataHead = null;
		userStoryHead = null;
		userNodeHead = null;
		employeeCount = 0;
		storyCount = 0;
		projectID = 0;
		
		next = null;
		previous = null;
		
	}
	
	
	
	//Getters and Setters
	public void setDataHead(DataNode dataNodeIn) {this.dataHead = dataNodeIn;}
	public DataNode getDataHead() {return dataHead;}
		
	public void setNext(ProjectNode nodeInNext) {this.next = nodeInNext;}
	public void setPrevious(ProjectNode nodeInPrevious) {this.previous = nodeInPrevious;}
	public void setProjectName(String projectNameIn) {this.projectName = projectNameIn;}
	public void setUserStoryHead(UserStory userStoryIn) {this.userStoryHead = userStoryIn;}
	public void setUserNodeHead(UserNode userNodeIn) {this.userNodeHead = userNodeIn;}
	public void setEmployeeCount(int employeeCountIn) {this.employeeCount = employeeCountIn;}
	public void setStoryCount(int storyCountIn) {this.storyCount = storyCountIn;}
	public void setProjectID(int projectIDIn) {this.projectID = projectIDIn;}
	
	public ProjectNode getNext() {return next;}
	public ProjectNode getPrevious() {return previous;}
	public String getProjectName() {return projectName;}
	public UserStory getUserStory() {return userStoryHead;}
	public UserNode getUserNodeHead() {return userNodeHead;}
	public int getEmployeeCount() {return employeeCount;}
	public int getStoryCount() {return storyCount;}
	public int getProjectID() {return projectID;}
	
	
	public String save() {
		
		String out = "";
		
		out = this.projectName + "\n" + this.employeeCount + "\n" + this.storyCount + "\n";
		
		return out;
		
	}
	
	
	//Additional Methods
	
	/**
	 * Creates a new DataNode and adds it to the front of the linked list of dataNodes
	 * then adjusts the links.
	 * @param name Name of new dataNode.
	 * @param time Time to add.
	 * @param defect Defect count to add.
	 */
	public void addNewData(String name, int logNumber, int duration, String date, String startTime, 
			String endTime, String lifeCycleStep, String effortCategory) {
		
		if(dataHead == null) {
			
			DataNode projectData = new DataNode(name, logNumber, duration, date, startTime, endTime,
					lifeCycleStep, effortCategory);
			dataHead = projectData;
			
		}
		else {
			
			DataNode projectData = new DataNode(name, logNumber, duration, date, startTime, endTime,
					lifeCycleStep, effortCategory);
			dataHead.setPrevious(projectData);
			projectData.setNext(dataHead);
			dataHead = projectData;
		
		}
		
	}
	
	public void addNewEmployee(UserNode employeeIn) {
		
		UserNode temp = employeeIn;
		
		if(userNodeHead == null) {
			
			userNodeHead = employeeIn;
			employeeCount++;
			
		}
		else {
			
			userNodeHead.setPrevious(temp);
			temp.setNext(userNodeHead);
			userNodeHead = temp;
			
			employeeCount++;
			
		}
		
	}
	
	public void addUserStory(UserStory storyIn) {
		
		UserStory temp = storyIn;
		
		if(userStoryHead == null) {
			
			userStoryHead = storyIn;
			storyCount++;
			
		}
		else {
			
			userStoryHead.setPrevious(temp);
			temp.setNext(userStoryHead);
			userStoryHead = temp;
			
			storyCount++;
			
		}
		
	}
	
	
	@Override
	public String toString() {
		
		return "DataNode: " + dataHead + "/n";
		
	}

}

package application;
//All functionality: Andrew Thomas
public class ProjectNode {
	
	private ProjectNode next; //The next project in the linked list.
	private ProjectNode previous; //The previous project in the linked list.
	private String projectName; //The name of the project.
	private int projectID;
	
	//Default constructor. Will not be used.
	public ProjectNode() {
		
		projectName = null;
		projectID = 0;
		
		next = null;
		previous = null;
		
	}
	
	//Additional Constructors
	//Always use this one.
	public ProjectNode(String nameIn) {
		
		projectName = nameIn;
		projectID = 0;
		
		next = null;
		previous = null;
		
	}
	
	
	
	//Getters and Setters
	public void setNext(ProjectNode nodeInNext) {this.next = nodeInNext;}
	public void setPrevious(ProjectNode nodeInPrevious) {this.previous = nodeInPrevious;}
	public void setProjectName(String projectNameIn) {this.projectName = projectNameIn;}
	public void setProjectID(int projectIDIn) {this.projectID = projectIDIn;}
	
	public ProjectNode getNext() {return next;}
	public ProjectNode getPrevious() {return previous;}
	public String getProjectName() {return projectName;}
	public int getProjectID() {return projectID;}
	
	/**
	 * Saves the data stored in the node by outputting it as a String formatted to match what StoreData expects.
	 * @return Returns a String of specific format.
	 */
	public String save() {
		
		String out = "";
		
		out = this.projectName + "\n";
		
		return out;
		
	}

}

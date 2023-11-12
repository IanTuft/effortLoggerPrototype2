package application;
//change
public class DataNode {

	private String projectName; //Name of project associated with.
	
	//Data variables: can change.
	private int timeCount;
	private int defectCount;	
	
	//Tags for searching in Planning Poker functionality.
	private String primaryTag;
	private String secondaryTag;
	private String additionalTag;
	
	//Linked List management variables.
	private DataNode next;
	private DataNode previous;
	
	//Default constructor. Do not use.
	public DataNode() {
		
		projectName = "NULL";
		timeCount = 0;
		defectCount = 0;
		
		primaryTag = "NULL";
		secondaryTag = "NULL";
		additionalTag = "NULL";
		
		next = null;
		previous = null;
		
	}
	
	//Additional Constructors
	
	//Primary constructor. Always use.
	public DataNode(String nameIn, int timeIn, int defectIn) {
		
		projectName = nameIn;
		timeCount = timeIn;
		defectCount = defectIn;
		
		primaryTag = "NULL";
		secondaryTag = "NULL";
		additionalTag = "NULL";
		
		next = null;
		previous = null;
		
	}
	
	public DataNode(String nameIn, int timeIn, int defectIn, String primaryTagIn) {
		
		projectName = nameIn;
		timeCount = timeIn;
		defectCount = defectIn;
		
		primaryTag = primaryTagIn;
		secondaryTag = "NULL";
		additionalTag = "NULL";
		
		next = null;
		previous = null;
		
	}
	
	
	//Getters and Setters
	public void setProjectName(String projectNameIn) {this.projectName = projectNameIn;}
	public void setTimeCount(int timeIn) {this.timeCount = timeIn;}
	public void setDefectCount(int defectCountIn) {this.defectCount = defectCountIn;}
	
	public void setPrimaryTag(String primaryTagIn) {this.primaryTag = primaryTagIn;}
	public void setSecondaryTag(String secondaryTagIn) {this.secondaryTag = secondaryTagIn;}
	public void setAdditionalTag(String additionalTagIn) {this.additionalTag = additionalTagIn;}
	
	public String getProjectName() {return projectName;}
	public int getTimeCount() {return timeCount;}
	public int getDefectCount() {return defectCount;}
	
	public String getPrimaryTag() {return primaryTag;}
	public String getSecondaryTag() {return secondaryTag;}
	public String getAdditionalTag() {return additionalTag;}
	
	public void setNext(DataNode nodeInNext) {this.next = nodeInNext;}
	public void setPrevious(DataNode nodeInPrevious) {this.previous = nodeInPrevious;}
	
	public DataNode getNext() {return next;}
	public DataNode getPrevious() {return previous;}
	
	
	public String save() {
		
		String out = "";
		
		out = this.projectName + "\n" + this.timeCount + "\n" + this.defectCount + "\n" + this.primaryTag + "\n";
		
		return out;
		
	}
	
	public void copy(DataNode nodeIn) {
		
		this.projectName = nodeIn.getProjectName();
		this.timeCount = nodeIn.getTimeCount();
		this.defectCount = nodeIn.getDefectCount();
		this.primaryTag = nodeIn.getPrimaryTag();
		
	}
	
	
	@Override
	public String toString() {//Modified for output for display of contained data in one command.
		
		return "Project Name: " + projectName + "\n"
				+ "Time Count: " + timeCount + "\n"
				+ "Defect Count: " + defectCount + "\n"
				+ "Primary Tag: " + primaryTag + "\n"
				+ "Secondary Tag: " + secondaryTag + "\n"
				+ "Additional Tag: " + additionalTag + "\n";
		
	}
	
}

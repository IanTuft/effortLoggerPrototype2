package application;
//change
public class DataNode {

	private String projectName; //Name of project associated with.
	
	//Data variables: can change.
	private int logNumber;
	private int duration;	
	
	private String date;
	private String startTime;
	private String endTime;
	private String lifeCycleStep;
	private String effortCategory;
	
	//Linked List management variables.
	private DataNode next;
	private DataNode previous;
	
	//Default constructor. Do not use.
	public DataNode() {
		
		projectName = "NULL";
		
		logNumber = 0;
		duration = 0;	
		
		date = "NULL";
		startTime = "NULL";
		endTime = "NULL";
		lifeCycleStep = "NULL";
		effortCategory = "NULL";
		
		next = null;
		previous = null;
		
	}
	
	//Additional Constructors
	
	//Primary constructor. Always use.
	public DataNode(String nameIn, int logNumberIn, int durationIn, 
			String dateIn, String startTimeIn, String endTimeIn, String lifeCycleStepIn, String effortCategoryIn) {
		
		projectName = nameIn;
		
		logNumber = logNumberIn;
		duration = durationIn;	
		
		date = dateIn;
		startTime = startTimeIn;
		endTime = endTimeIn;
		lifeCycleStep = lifeCycleStepIn;
		effortCategory = effortCategoryIn;
		
		next = null;
		previous = null;
		
	}
	
	//Getters and Setters
	public void setProjectName(String projectNameIn) {this.projectName = projectNameIn;}
	public void setLogNumber(int logNumberIn) {this.logNumber = logNumberIn;}
	public void setDuration(int durationIn) {this.duration = durationIn;}
	public void setDate(String dateIn) {this.date = dateIn;}
	public void setStartTime(String startIn) {this.startTime = startIn;}
	public void setEndTime(String endIn) {this.endTime = endIn;}
	public void setLifeCycleStep(String cycleIn) {this.lifeCycleStep = cycleIn;}
	public void setEffortCategory(String effortIn) {this.effortCategory = effortIn;}
	
	public String getProjectName() {return projectName;}
	public int getLogNumber() {return logNumber;}
	public int getDuration() {return duration;}
	public String getDate() {return date;}
	public String getStartTime() {return startTime;}
	public String getEndTime() {return endTime;}
	public String getLifeCycleStep() {return lifeCycleStep;}
	public String getEffortCategory() {return effortCategory;}
	
	public void setNext(DataNode nodeInNext) {this.next = nodeInNext;}
	public void setPrevious(DataNode nodeInPrevious) {this.previous = nodeInPrevious;}
	
	public DataNode getNext() {return next;}
	public DataNode getPrevious() {return previous;}
	
	
	public String save() {
		
		String out = "";
		
		out = this.projectName + "\n" + this.logNumber + "\n" + this.duration + "\n" + this.date + "\n" + this.startTime + 
				"\n" + this.endTime + "\n" + this.lifeCycleStep + "\n" + this.effortCategory + "\n";
		
		return out;
		
	}
	
	public String display() {
		
		String out = "";
		
		out = "Project: " + this.projectName + "\n" + "Log Number: " + this.logNumber + "\n" + "Duration: " + this.duration + "\n" 
		+ "Date: " + this.date + "\n" + "Start Time: " + this.startTime + "\n" + "End Time: " + this.endTime + "\n" + 
				"Lifecycle Step: " +this.lifeCycleStep + "\n" + "Effort Category: " + this.effortCategory + "\n";
				
		return out;
		
	}
	
	public void copy(DataNode nodeIn) {
		
		this.projectName = nodeIn.getProjectName();
		this.logNumber = nodeIn.getLogNumber();
		this.duration = nodeIn.getDuration();
		this.date = nodeIn.getDate();
		this.startTime = nodeIn.getStartTime();
		this.endTime = nodeIn.getEndTime();
		this.lifeCycleStep = nodeIn.getLifeCycleStep();
		this.effortCategory = nodeIn.getEffortCategory();
		
	}
	
}

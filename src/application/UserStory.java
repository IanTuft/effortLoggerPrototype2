package application;

public class UserStory {
	
	private String projectName; //Name of project associated with.
	
	//Data variables: can change.
	private String storyTitle;
	private String story;
	
	//Tags for searching in Planning Poker functionality.
	private String primaryTag;
	private String secondaryTag;
	private String additionalTag;
	
	//Linked List management variables.
	private UserStory next;
	private UserStory previous;
	
	//Default constructor. Do not use.
	public UserStory() {
		
		projectName = "NULL";
		storyTitle = "NULL";
		story = "NULL";
		
		primaryTag = "NULL";
		secondaryTag = "NULL";
		additionalTag = "NULL";
		
		next = null;
		previous = null;
		
	}
	
	//Additional Constructors
	
	//Primary constructor. Always use.
	public UserStory(String nameIn, String storyTitleIn, String storyIn) {
		
		projectName = nameIn;
		storyTitle = storyTitleIn;
		story = storyIn;
		
		primaryTag = "NULL";
		secondaryTag = "NULL";
		additionalTag = "NULL";
		
		next = null;
		previous = null;
		
	}
	
	
	//Getters and Setters
	public void setProjectName(String projectNameIn) {this.projectName = projectNameIn;}
	public void setStoryTitle(String storyTitleIn) {this.storyTitle = storyTitleIn;}
	public void setStory(String storyIn) {this.story = storyIn;}
	
	public void setPrimaryTag(String primaryTagIn) {this.primaryTag = primaryTagIn;}
	public void setSecondaryTag(String secondaryTagIn) {this.secondaryTag = secondaryTagIn;}
	public void setAdditionalTag(String additionalTagIn) {this.additionalTag = additionalTagIn;}
	
	public String getProjectName() {return projectName;}
	public String getStoryTitle() {return storyTitle;}
	public String getStory() {return story;}
	
	public String getPrimaryTag() {return primaryTag;}
	public String getSecondaryTag() {return secondaryTag;}
	public String getAdditionalTag() {return additionalTag;}
	
	public void setNext(UserStory nodeInNext) {this.next = nodeInNext;}
	public void setPrevious(UserStory nodeInPrevious) {this.previous = nodeInPrevious;}
	
	public UserStory getNext() {return next;}
	public UserStory getPrevious() {return previous;}
	
	
	public String save() {
		
		String out = "";
		
		out = this.projectName + "\n" + this.storyTitle + "\n" + this.story + "\n";
		
		return out;
		
	}
	
	
	@Override
	public String toString() {//Modified for output for display of contained data in one command.
		
		return "Project Name: " + projectName + "\n"
				+ "Primary Tag: " + primaryTag + "\n"
				+ "Secondary Tag: " + secondaryTag + "\n"
				+ "Additional Tag: " + additionalTag + "\n";
		
	}

}

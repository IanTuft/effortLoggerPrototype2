package application;

public class PlanningPokerSequence {
	
	private ProcessInput processInput;
	private int currentStage;
	private ProjectNode project;
	private UserStory userStory;
	private UserNode userNodeHead;
	private UserStory selectedStory;
	
	
	//Default Constructor. DO NOT USE.
	PlanningPokerSequence(){
		
		processInput = new ProcessInput();
		currentStage = -1;
		project = null;
		userStory = null;
		userNodeHead = null;
		selectedStory = null;
		
	}
	
	//Additional Constructors
	//Always use this constructor
	PlanningPokerSequence(ProjectNode projectIn){
		
		processInput = new ProcessInput();
		currentStage = 0;
		project = projectIn;
		userStory = project.getUserStory();
		userNodeHead = project.getUserNodeHead();
		selectedStory = null;
		
	}
	
	//Public Methods
	
	public void planningPokerSequence() {
		
		while(currentStage == 0) {
			
			currentStage = inviteToSession();
			
			if(currentStage == 0) {
				
				inviteError();
				
			}
			
		}
		
		while(currentStage == 1) {
			
			currentStage = userStorySelection();
			
			if(currentStage == 1) {
				
				storyError();
				
			}
			
		}
		
		while(currentStage == 2) {
			
			currentStage = submitEstimates();
			
			if(currentStage == 2) {
				
				estimateError();
				
			}
			
		}
		
		while(currentStage == 3) {
			
			currentStage = displayEstimates();
			
			if(currentStage == 3) {
				
				displayError();
				
			}
			
		}
		
		while(currentStage == 4) {
			
			currentStage = modifyEstimate();
			
			if(currentStage == 5) {
				
				modifyError();
				
			}
			
		}
		
		while(currentStage == 6) {
			
			currentStage = storyResolution();
			
			if(currentStage == 6) {
				
				storyError();
				
			}
			
		}
		
		while(currentStage == 7) {
			
			currentStage = endSession();
			
		}
		
	}
	
	
	//Private/Helper Methods
	
	private int inviteToSession() {
		
		int status = 0;
		
		//not needed with based on TA specifications
		
		return status;
		
	}
	
	private void inviteError() {
		
		
		
	}
	
	private int userStorySelection() {
		
		int status = 1;
		int storyCount = project.getStoryCount();
		UserStory storyList = project.getUserStory();
		
		UserStory[] currentStories = new UserStory[storyCount];
		
		for(int i = 0; i <= storyCount; i++) {
			
			currentStories[i] = storyList;
			
			storyList = storyList.getNext();
			
		}
		
		status = selectUserStory(currentStories);
		
		
		return status;
		
	}
	
	private int selectUserStory(UserStory[] storyArray) {
		
		int status = 1;
		int i = 0;
		
		while(status == 1) {
			
			storyArray[i].toString();
			
			
			
		}
		
		return status;
		
	}
	
	private void storyError() {
		
		
		
	}
	
	private int submitEstimates() {
		
		int status = 2;
		
		
		
		
		return status;
		
	}
	
	private void estimateError() {
		
		
		
	}
	
	private int displayEstimates() {
		
		int status = 3;
		
		
		return status;
		
	}
	
	private void displayError() {
		
		
		
	}
	
	private int modifyEstimate() {
		
		int status = 4;
		
		
		return status;
		
	}
	
	private void modifyError() {
		
		
		
	}
	
	private int storyResolution() {
		
		int status = 6;
		
		
		return status;
		
	}
	
	private void resolutionError() {
		
		
		
	}
	
	private int endSession() {
		
		int status = 7;
		
		
		return status;
		
	}

}

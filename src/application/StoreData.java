package application;

import java.io.*;

public class StoreData {
	
	private FileWriter userWrite;
	private FileWriter projectWrite;
	
	//Default Constructor.
	StoreData(){
		
		try {
			
			userWrite = new FileWriter("users.txt");
			projectWrite = new FileWriter("projects.txt");
			
		}
		catch(IOException e) {
			
			System.out.println("ERROR creating one or more files.");
			
		}
		
		
	}
	
	//Additional Constructors.
	
	//Public Methods
	public void saveUsers(UserNode userNodeHead) {
		
		int userCount = 0;
		UserNode temp = userNodeHead;
		
		while(temp.getNext() != null) {
			
			userCount++;
			temp = temp.getNext();
			
		}
		
		try {
			
			userWrite.write(userCount);
			userWrite.write("\n");
			
		}
		catch(IOException e) {
			
			System.out.println("ERROR writing user count to file.");
			
		}
		
		for(int i = 0; i < userCount; i++) {
			
			try {
				
				userWrite.write(userNodeHead.save());
				saveUsersData(userNodeHead.getDataHead());
				
				userWrite.write("NEXT");
				
			}
			catch(IOException e){
				
				System.out.println("ERROR writing users to file.");
				
			}
			
			userNodeHead = userNodeHead.getNext();
			
		}
		
	}
	
	private void saveUsersData(DataNode dataNodeHead) {
		
		while(dataNodeHead.getNext() != null) {
			
			try {
				
				userWrite.write(dataNodeHead.save());
				
			}
			catch(IOException e) {
				
				System.out.println("ERROR writing user data to file.");
				
			}
			
			dataNodeHead = dataNodeHead.getNext();
			
		}
		
	}
	
	public void saveProject(ProjectNode projectNodeHead) {
		
		int projectCount = 0;
		ProjectNode temp = projectNodeHead;
		
		while(temp.getNext() != null) {
			
			projectCount++;
			temp = temp.getNext();
			
		}
		
		try {
			
			projectWrite.write(projectCount);
			projectWrite.write("\n");
			
		}
		catch(IOException e) {
			
			System.out.println("ERROR writing project count to file.");
			
		}
		
		for(int i = 0; i < projectCount; i++) {
			
			try {
				
				projectWrite.write(projectNodeHead.save());
				
				saveStories(projectNodeHead.getUserStory());
				projectWrite.write("NEXT");
				
				saveProjectData(projectNodeHead.getDataHead());
				
				projectWrite.write("NEXT");
				
			}
			catch(IOException e) {
				
				System.out.println("ERROR writing projects to file.");
				
			}
			
			projectNodeHead = projectNodeHead.getNext();
			
		}
		
	}
	
	private void saveStories(UserStory userStoryHead) {
		
		while(userStoryHead.getNext() != null) {
			
			try {
				
				projectWrite.write(userStoryHead.save());
				
			}
			catch(IOException e) {
				
				System.out.println("ERROR writing stories to file.");
				
			}
			
			userStoryHead = userStoryHead.getNext();
			
		}
		
	}
	
	private void saveProjectData(DataNode dataNodeHead) {
		
		while(dataNodeHead.getNext() != null) {
			
			try {
				
				projectWrite.write(dataNodeHead.save());
				
			}
			catch(IOException e) {
				
				System.out.println("ERROR writing project data to file.");
				
			}
			
			dataNodeHead = dataNodeHead.getNext();
			
		}
		
	}

}

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
		
		if(temp != null) {
		
			while(temp.getNext() != null) {
				
				userCount++;
				temp = temp.getNext();
				
			}
			if(temp.getNext() == null) {
				
				userCount++;
				
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
				
				if(userNodeHead.getNext() != null) {
				
					userNodeHead = userNodeHead.getNext();
				
				}
			}
		
		}
		
		try {
			
			userWrite.close();
			
		}
		catch(IOException e) {
			
			System.out.println("close user file issue");
			
		}
		
	}
	
	private void saveUsersData(DataNode dataNodeHead) {
		
		if(dataNodeHead != null) {
		
			while(dataNodeHead.getNext() != null) {
				
				try {
					
					userWrite.write(dataNodeHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing user data to file.");
					
				}
				
				dataNodeHead = dataNodeHead.getNext();
				
			}
			
			if(dataNodeHead.getNext() == null) {
				
				try {
					
					userWrite.write(dataNodeHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing user data to file.");
					
				}
				
			}
		
		}
		
	}
	
	public void saveProject(ProjectNode projectNodeHead) {
		
		int projectCount = 0;
		ProjectNode temp = projectNodeHead;
		
		if(temp != null) {
		
			while(temp.getNext() != null) {
				
				projectCount++;
				temp = temp.getNext();
				
			}
			if(temp.getNext() == null) {
				
				projectCount++;
				
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
				
				if(projectNodeHead.getNext() != null) {
				
					projectNodeHead = projectNodeHead.getNext();
				
				}
				
			}
		
		}
		
		try {
			
			projectWrite.close();
			
		}
		catch(IOException e) {
			
			System.out.println("close project file issue");
			
		}
		
	}
	
	private void saveStories(UserStory userStoryHead) {
		
		if(userStoryHead != null) {
		
			while(userStoryHead.getNext() != null) {
				
				try {
					
					projectWrite.write(userStoryHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing stories to file.");
					
				}
				
				userStoryHead = userStoryHead.getNext();
				
			}
			
			if(userStoryHead.getNext() == null) {
				
				try {
					
					projectWrite.write(userStoryHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing stories to file.");
					
				}
				
			}
		
		}
		
	}
	
	private void saveProjectData(DataNode dataNodeHead) {
		
		if(dataNodeHead != null) {
		
			while(dataNodeHead.getNext() != null) {
				
				try {
					
					projectWrite.write(dataNodeHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing project data to file.");
					
				}
				
				dataNodeHead = dataNodeHead.getNext();
				
			}
			
			if(dataNodeHead.getNext() == null) {
				
				try {
					
					projectWrite.write(dataNodeHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing project data to file.");
					
				}
				
			}
		
		}
		
	}

}

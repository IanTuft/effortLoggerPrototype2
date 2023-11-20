package application;

import java.io.*;

public class StoreData {
	
	private FileWriter userWrite;
	private FileWriter projectWrite;
	
	private BufferedWriter user;
	private BufferedWriter project;
	
	//Default Constructor.
	StoreData(){
		
		try {
			
			userWrite = new FileWriter("users.txt");
			projectWrite = new FileWriter("projects.txt");
			
			user = new BufferedWriter(userWrite);
			project = new BufferedWriter(projectWrite);
			
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
				
				user.write("0 \n");
				user.write(Integer.toString(userCount));
				user.write("\n");
				
			}
			catch(IOException e) {
				
				System.out.println("ERROR writing user count to file.");
				
			}
			
			for(int i = 0; i < userCount; i++) {
				
				try {
					
					user.write(userNodeHead.save());
					saveUsersData(userNodeHead.getDataHead());
					
					user.write("NEXT\n");
					
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
			
			user.close();
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
					
					user.write(dataNodeHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing user data to file.");
					
				}
				
				dataNodeHead = dataNodeHead.getNext();
				
			}
			
			if(dataNodeHead.getNext() == null) {
				
				try {
					
					user.write(dataNodeHead.save());
					
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
				
				project.write("0 \n");
				project.write(Integer.toString(projectCount));
				project.write("\n");
				
			}
			catch(IOException e) {
				
				System.out.println("ERROR writing project count to file.");
				
			}
			
			for(int i = 0; i < projectCount; i++) {
				
				try {
					
					project.write(projectNodeHead.save());
					
					saveStories(projectNodeHead.getUserStory());
					project.write("NEXT\n");
					
					//saveProjectData(projectNodeHead.getDataHead());
					
					project.write("NEXT\n");
					
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
			
			project.close();
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
					
					project.write(userStoryHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing stories to file.");
					
				}
				
				userStoryHead = userStoryHead.getNext();
				
			}
			
			if(userStoryHead.getNext() == null) {
				
				try {
					
					project.write(userStoryHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing stories to file.");
					
				}
				
			}
		
		}
		
	}
	
/*	private void saveProjectData(DataNode dataNodeHead) {
		
		if(dataNodeHead != null) {
		
			while(dataNodeHead.getNext() != null) {
				
				try {
					
					project.write(dataNodeHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing project data to file.");
					
				}
				
				dataNodeHead = dataNodeHead.getNext();
				
			}
			
			if(dataNodeHead.getNext() == null) {
				
				try {
					
					project.write(dataNodeHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing project data to file.");
					
				}
				
			}
		
		}
		
	}*/

}

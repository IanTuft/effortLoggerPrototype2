package application;
//All functionality: Andrew Thomas
import java.io.*;

public class StoreData {
	
	private FileWriter userWrite;
	private FileWriter projectWrite;
	
	private BufferedWriter user;
	private BufferedWriter project;
	
	//Default Constructor.
	StoreData(){
		
		try {
			
			//We intentionally overwrite the files here to ensure no data duplication occurs.
			//This does mean we need to be careful not to call StoreData() until we have loaded all the data
			//that was saved in the files.
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
	
	/**
	 * Saves all the users and their associated data.
	 * @param userNodeHead The head of the linked list of UserNodes to be processed for saving.
	 */
	public void saveUsers(UserNode userNodeHead) {
		
		int userCount = 0;
		UserNode temp = userNodeHead;
		
		if(temp != null) { //Ensure there is at least one user.
		
			//Count number of users
			while(temp.getNext() != null) {
				
				userCount++;
				temp = temp.getNext();
				
			}
			//Edge Case: There are multiple users or only one user.
			//Catches the last user who would otherwise be skipped and/or the only user if there is only one.
			if(temp.getNext() == null) {
				
				userCount++;
				
			}
			
			try {
				
				user.write("0 \n"); //Dummy character to be checked first by ReadData.
				user.write(Integer.toString(userCount)); //Save the user count.
				user.write("\n"); //New line
				
			}
			catch(IOException e) {
				
				System.out.println("ERROR writing user count to file.");
				
			}
			
			//Write all the users to the file.
			for(int i = 0; i < userCount; i++) {
				
				try {
					
					user.write(userNodeHead.save()); //Save the user information.
					saveUsersData(userNodeHead.getDataHead()); //Save all the user data.
					
					user.write("NEXT\n"); //Read by ReadData to know that the end of the user data is reached
											//and to prepare for the next user.
					
				}
				catch(IOException e){
					
					System.out.println("ERROR writing users to file.");
					
				}
				
				if(userNodeHead.getNext() != null) { //Double check we won't run off the linked list.
				
					userNodeHead = userNodeHead.getNext();
				
				}
			}
		
		}
		
		try {
			
			//Close files so they are written.
			user.close();
			userWrite.close();
			
		}
		catch(IOException e) {
			
			System.out.println("close user file issue");
			
		}
		
	}
	
	/**
	 * Saves all the data of a user.
	 * @param dataNodeHead The head of the linked list of DataNodes to be processed for saving.
	 */
	private void saveUsersData(DataNode dataNodeHead) {
		
		if(dataNodeHead != null) { //Ensure there is at least one data entry
		
			//Edge Case: There are multiple data entries.
			//Writes each data entry to file.
			while(dataNodeHead.getNext() != null) {
				
				try {
					
					user.write(dataNodeHead.save());
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing user data to file.");
					
				}
				
				dataNodeHead = dataNodeHead.getNext();
				
			}
			
			//Edge Case: There are multiple data entries or only one data entry.
			//Catches the last data entry that would otherwise be skipped and/or the only data entry if there is only one.
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
	/**
	 * Saves all the projects.
	 * @param projectNodeHead The head of the linked list of ProjectNodes to be processed for saving.
	 */
	public void saveProject(ProjectNode projectNodeHead) {
		
		int projectCount = 0;
		ProjectNode temp = projectNodeHead;
		
		if(temp != null) { //Ensure there is at least one project.
		
			//Count number of projects
			while(temp.getNext() != null) {
				
				projectCount++;
				temp = temp.getNext();
				
			}
			//Edge Case: There are multiple projects or only one project.
			//Catches the last project that would otherwise be skipped and/or the only project if there is only one.
			if(temp.getNext() == null) {
				
				projectCount++;
				
			}
			
			try {
				
				project.write("0 \n"); //Dummy character to be checked first by ReadData.
				project.write(Integer.toString(projectCount)); //Save the project count.
				project.write("\n"); //New line
				
			}
			catch(IOException e) {
				
				System.out.println("ERROR writing project count to file.");
				
			}
			
			//Write all the projects to the file.
			for(int i = 0; i < projectCount; i++) {
				
				try {
					
					project.write(projectNodeHead.save()); //Save project information.
					
				}
				catch(IOException e) {
					
					System.out.println("ERROR writing projects to file.");
					
				}
				
				if(projectNodeHead.getNext() != null) { //Double check we won't run off the linked list
				
					projectNodeHead = projectNodeHead.getNext();
				
				}
				
			}
		
		}
		
		try {
			
			//Close files so they are written
			project.close();
			projectWrite.close();
			
		}
		catch(IOException e) {
			
			System.out.println("close project file issue");
			
		}
		
	}

}

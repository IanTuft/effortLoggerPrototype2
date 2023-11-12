package application;

import java.io.*;

public class ReadData {
	
	private BufferedReader usersIn;
	private BufferedReader projectsIn;
	
	private FileReader users;
	private FileReader projects;
	
	//Default Constructor. DO NOT USE.
	
	public ReadData() {
		
		try {
			
			users = new FileReader("users.txt");
			projects = new FileReader("projects.txt");
			
			usersIn = new BufferedReader(users);
			projectsIn = new BufferedReader(projects);
			
		}
		catch(FileNotFoundException e) {
		
			System.out.println("One or more files not found.");
		
		}
		
	}
	
	//Additional Constructors
	
	//ONLY USE THIS ONE
	public ReadData(LinkedListManager manager) {
		
		try {
			
			users = new FileReader("users.txt");
			projects = new FileReader("projects.txt");
			
			usersIn = new BufferedReader(users);
			projectsIn = new BufferedReader(projects);
			
			addUsers(manager);
			addProjects(manager);
			
		}
		catch(FileNotFoundException e) {
		
			System.out.println("One or more files not found.");
		
		}
		
	}
	
	private void addUsers(LinkedListManager manager) {
		
		String endString = "";
		String project = "";
		int time = 0;
		int defect = 0;
		int userCount = 0;
		String primaryTag = "";
		
		try {
			
			userCount = Integer.parseInt(usersIn.readLine());
			
		}
		catch(IOException e) {
			
			System.out.println("Unexepected read error. Position: load user count.");
			
		}
		
		for(int i = 0; i < userCount; i++) {
			
			try {
				
				String name = usersIn.readLine();
				int id = Integer.parseInt(usersIn.readLine());
				
				manager.addNewEmployee(name, id);
				manager.lockUser(id);
				
				endString = usersIn.readLine();
				
				while(!endString.equals("NEXT")) {
					
					project = endString;
					time = Integer.parseInt(usersIn.readLine());
					defect = Integer.parseInt(usersIn.readLine());
					primaryTag = usersIn.readLine();
					
					manager.addNewData(project, time, defect, primaryTag);
					
					endString = usersIn.readLine();
					
				}
				
				manager.unlockUser();
				
			}
			catch(IOException e) {
				
				System.out.println("Unexpected read error. Position: load users.");
				
			}
			
		}
		
	}
	
	private void addProjects(LinkedListManager manager) {
		
		String endString = "";
		String projectName = "";
		int employeeCount = 0;
		int storyCount = 0;
		
		int projectCount = 0;
		
		try {
			
			projectCount = Integer.parseInt(projectsIn.readLine());
			
		}
		catch(IOException e) {
			
			System.out.println("Unexepected read error. Position: load project count.");
			
		}
		
		for(int i = 0; i < projectCount; i++) {
			
			try {
				
				projectName = projectsIn.readLine();
				employeeCount = Integer.parseInt(projectsIn.readLine());
				storyCount = Integer.parseInt(projectsIn.readLine());
				
				manager.loadNewProject(projectName, employeeCount, storyCount);
				
				endString = projectsIn.readLine();
				
				while(!endString.equals("NEXT")) {
					
					//story variables
					
					//add the story
					
					endString = projectsIn.readLine();
					
				}
				
				endString = projectsIn.readLine();
				
				while(!endString.equals("NEXT")) {
					
					//data variables
					
					//add data
					
					endString = projectsIn.readLine();
					
				}
				
			}
			catch(IOException e) {
				
				System.out.println("Unexpected read error. Position: load users.");
				
			}
			
		}
		
	}

}

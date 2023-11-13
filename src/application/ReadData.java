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
			
			usersIn = new BufferedReader(users);
			
			addUsers(manager);
			
		}
		catch(FileNotFoundException e) {
		
			System.out.println("users.txt not found.");
		
		}
		
		try {
			
			projects = new FileReader("projects.txt");
			
			projectsIn = new BufferedReader(projects);
			
			addProjects(manager);
			
		}
		catch(FileNotFoundException e) {
		
			System.out.println("projects.txt not found.");
		
		}
		
	}
	
	private void addUsers(LinkedListManager manager) {
		
		String endString = "";
		int userCount = 0;
		
		String name = "";
		int id = 0;
		String password = "";
		
		String projectName = "";
		int logNumber = 0;
		int duration = 0;
		String date = "";
		String startTime = "";
		String endTime = "";
		String lifeCycleStep = "";
		String effortCategory = "";
		String etc = "";
		
		String primaryTag = "";
		String secondaryTag = "";
		String additionalTag = "";
		
		try {
			
			userCount = Integer.parseInt(usersIn.readLine());
			
		}
		catch(IOException e) {
			
			System.out.println("Unexepected read error. Position: load user count.");
			
		}
		
		for(int i = 0; i < userCount; i++) {
			
			try {
				
				name = usersIn.readLine();
				id = Integer.parseInt(usersIn.readLine());
				password = usersIn.readLine();
				
				manager.addNewEmployee(name, id, password);
				manager.lockUser(id, password);
				
				endString = usersIn.readLine();
				
				while(!endString.equals("NEXT")) {
					
					projectName = endString;
					logNumber = Integer.parseInt(usersIn.readLine());
					duration = Integer.parseInt(usersIn.readLine());
					date = usersIn.readLine();
					startTime = usersIn.readLine();
					endTime = usersIn.readLine();
					lifeCycleStep = usersIn.readLine();
					effortCategory = usersIn.readLine();
					etc = usersIn.readLine();
					
					primaryTag = usersIn.readLine();
					secondaryTag = usersIn.readLine();
					additionalTag = usersIn.readLine();
					
					manager.addNewData(projectName, logNumber, duration, date, startTime, endTime,
							lifeCycleStep, effortCategory, etc, primaryTag, secondaryTag, additionalTag);
					
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
		
		String storyTitle = "";
		String story = "";
		
		int logNumber = 0;
		int duration = 0;
		String date = "";
		String startTime = "";
		String endTime = "";
		String lifeCycleStep = "";
		String effortCategory = "";
		String etc = "";
		String primaryTag = "";
		String secondaryTag = "";
		String additionalTag = "";
		
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
				
				manager.addNewProject(projectName, employeeCount, storyCount);
				
				endString = projectsIn.readLine();
				
				while(!endString.equals("NEXT")) {
					
					projectName = projectsIn.readLine();
					storyTitle = projectsIn.readLine();
					story = projectsIn.readLine();
					
					manager.addNewStory(projectName, storyTitle, story);
					
					endString = projectsIn.readLine();
					
				}
				
				endString = projectsIn.readLine();
				
				while(!endString.equals("NEXT")) {
					
					projectName = endString;
					logNumber = Integer.parseInt(usersIn.readLine());
					duration = Integer.parseInt(usersIn.readLine());
					date = usersIn.readLine();
					startTime = usersIn.readLine();
					endTime = usersIn.readLine();
					lifeCycleStep = usersIn.readLine();
					effortCategory = usersIn.readLine();
					etc = usersIn.readLine();
					
					primaryTag = usersIn.readLine();
					secondaryTag = usersIn.readLine();
					additionalTag = usersIn.readLine();
					
					manager.addNewData(projectName, logNumber, duration, date, startTime, endTime,
							lifeCycleStep, effortCategory, etc, primaryTag, secondaryTag, additionalTag);
					
					endString = projectsIn.readLine();
					
				}
				
			}
			catch(IOException e) {
				
				System.out.println("Unexpected read error. Position: load users.");
				
			}
			
		}
		
	}

}

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
			
			projects = new FileReader("projects.txt");
			
			projectsIn = new BufferedReader(projects);
			
			if(projectsIn.readLine() != null)
				addProjects(manager);
			
		}
		catch(FileNotFoundException e) {
		
			System.out.println("projects.txt not found.");
		
		}
		catch(IOException e) {
			
			System.out.println("IO");
			
		}
		
		try {
			
			users = new FileReader("users.txt");
			
			usersIn = new BufferedReader(users);

			if(usersIn.readLine() != null)
				addUsers(manager);
			
		}
		catch(FileNotFoundException e) {
		
			System.out.println("users.txt not found.");
		
		}
		catch(IOException e) {
			
			System.out.println("IO");
			
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
					
					manager.addNewData(projectName, logNumber, duration, date, startTime, endTime,
							lifeCycleStep, effortCategory);
					
					endString = usersIn.readLine();
					
				}
				
				manager.unlockUser();
				
			}
			catch(IOException e) {
				
				System.out.println("Unexpected read error. Position: load users.");
				
			}
			
		}
		
		try{
			
			usersIn.close();
			
		}
		catch(IOException e) {
			
			System.out.println("uh...");
			
		}
		
		
	}
	
	private void addProjects(LinkedListManager manager) {
		
		String projectName = "";
		
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
				
				manager.addNewProject(projectName);
				
			}
			catch(IOException e) {
				
				System.out.println("Unexpected read error. Position: load users.");
				
			}
			
		}
		
		try{
			
			projectsIn.close();
			
		}
		catch(IOException e) {
			
			System.out.println("uh...");
			
		}
		
	}

}

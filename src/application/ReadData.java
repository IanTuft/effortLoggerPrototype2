package application;
//All functionality: Andrew Thomas
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
	/**
	 * ONLY USE THIS CONSTRUCTOR.
	 * Pass in the LinkedListManager object to receive the data.
	 * @param manager LinkedListManager object to receive file data.
	 */
	public ReadData(LinkedListManager manager) {
		
		try {
			
			projects = new FileReader("projects.txt"); //Projects first because we load like we're inputing new data
			
			projectsIn = new BufferedReader(projects);
			
			if(projectsIn.readLine() != null) //Check for a dummy character. If there is none, the file is empty
												//or does not exist so there is nothing to read.
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

			if(usersIn.readLine() != null) //Check for a dummy character. If there is none, the file is empty
											//or does not exist so there is nothing to read.
				addUsers(manager);
			
		}
		catch(FileNotFoundException e) {
		
			System.out.println("users.txt not found.");
		
		}
		catch(IOException e) {
			
			System.out.println("IO");
			
		}
		
	}
	
	/**
	 * Loads all the user information and their data.
	 * @param manager The LinkedListManager to be loaded with the information.
	 */
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
			
			userCount = Integer.parseInt(usersIn.readLine()); //Read how many users there are
			
		}
		catch(IOException e) {
			
			System.out.println("Unexepected read error. Position: load user count.");
			
		}
		
		//Load users and user data
		for(int i = 0; i < userCount; i++) {
			
			try { //Load users by creating new employees and logging in
				
				name = usersIn.readLine();
				id = Integer.parseInt(usersIn.readLine());
				password = usersIn.readLine();
				
				manager.addNewEmployee(name, id, password);
				manager.lockUser(id, password);
				
				endString = usersIn.readLine();
				
				while(!endString.equals("NEXT")) { //Load data until the flag string "NEXT" to indicate next user.
					
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
				
				manager.unlockUser(); //Logout of user so we can log into next one or so program is ready for login
				
			}
			catch(IOException e) {
				
				System.out.println("Unexpected read error. Position: load users.");
				
			}
			
		}
		
		try{
			
			usersIn.close(); //Close file afterwards so it can be written to later when saving.
			
		}
		catch(IOException e) {
			
			System.out.println("users.txt close issue");
			
		}
		
		
	}
	
	/**
	 * Loads all the project information.
	 * @param manager The LinkedListManager to be loaded with the information.
	 */
	private void addProjects(LinkedListManager manager) {
		
		String projectName = "";
		
		int projectCount = 0;
		
		try {
			
			projectCount = Integer.parseInt(projectsIn.readLine()); //Number of projects to load
			
		}
		catch(IOException e) {
			
			System.out.println("Unexepected read error. Position: load project count.");
			
		}
		
		//Read in projects
		for(int i = 0; i < projectCount; i++) {
			
			try {
				
				projectName = projectsIn.readLine();
				
				manager.addNewProject(projectName); //Create new projects with the name
				
			}
			catch(IOException e) {
				
				System.out.println("Unexpected read error. Position: load users.");
				
			}
			
		}
		
		try{
			
			projectsIn.close(); //Close file afterwards so it can be written to later when saving.
			
		}
		catch(IOException e) {
			
			System.out.println("projects.txt close issue");
			
		}
		
	}

}

package application;

import java.util.Scanner;

public class ProcessInput {
	
	private Scanner scan = new Scanner(System.in);
	
	//Array of allowed characters
	private final char[] allowedChar = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 
			'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

	//Default constructor. Only constructor.
	public ProcessInput() {}
	
	//NO Additional Constructors
	
	/**
	 * Processes a String input and returns an integer.
	 * Ensures that the input is of a length specified.
	 * @param in Input to be processed.
	 * @param numCharReturn Number of characters to return. Do not ask for more than 9 because int cannot handle more than 9 digits.
	 * @return Returns an integer.
	 */
	public int processInt(String in, int numCharReturn) {
		
		int processedInt = -123321;
		int lengthCheck = 0;
		String input = in;
		
		while(true) {
				
			lengthCheck = input.length();
			
			if(lengthCheck <= numCharReturn) {
				
				try {
					
					processedInt = Integer.parseInt(input);
					return processedInt;
					
				}
				catch(Exception e){
					
					System.out.println("Your input was not an integer. Please input an integer of up to size " + numCharReturn + ".\n");
					input = scan.nextLine();
					
				}
				
			}
			else {
				
				System.out.println("Your input was not of the correct size. Please input an integer of up to size " + numCharReturn + ".\n");
				input = scan.nextLine();
				
			}
				
		}
		
	}
	
	/**
	 * Processes a String input and returns a String.
	 * Ensures that the String is of a length specified.
	 * @param in Input to be processed.
	 * @param numCharReturn Number of characters to return.
	 * @return Returns a String.
	 */
	public String processString(String in, int numCharReturn) {
		
		//String processedString = "";
		int lengthCheck = 0;
		String input = in;
		int check = 0;
		
		
		while(true) {
			
			check = 0;
			lengthCheck = input.length();

			if(lengthCheck <= numCharReturn) {
				
				char[] processArray = input.toCharArray();

				for(int i = 0; i < lengthCheck; i++) {
					
					for(int j = 0; j < allowedChar.length; j++) {
						
						if(processArray[i] == allowedChar[j]) {
							
							check++;
							j = allowedChar.length + 1;
							
						}
						
					}
					
				}

				if(check == lengthCheck)
					{return input;}
				else {
					
					System.out.println("Your input contained an invalid character. Please enter a string of up "
							+ "to size " + numCharReturn + " with only English alphanumeric characters and spaces.");
					input = scan.nextLine();
					
				}
				
			}
			else {
				
				System.out.println("Your input was not of the correct size. Please enter a string of up "
						+ "to size " + numCharReturn + " with only English alphanumeric characters and spaces.\n");
				input = scan.nextLine();
				
			}
			
		}
		
	}
	
}

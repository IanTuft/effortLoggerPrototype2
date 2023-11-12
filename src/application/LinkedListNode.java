package application;

public class LinkedListNode {
	
	private LinkedListNode next;
	private LinkedListNode previous;
	
	//Default Constructor
	public LinkedListNode() {
		
		next = null;
		previous = null;
		
	}
	
	//Additional Constructors
	
	
	//Getters and Setters
	
	public void setNext(LinkedListNode nodeInNext) {this.next = nodeInNext;}
	public void setPrevious(LinkedListNode nodeInPrevious) {this.previous = nodeInPrevious;}
	
	public LinkedListNode getNext() {return next;}
	public LinkedListNode getPrevious() {return previous;}
	
	
	@Override
	public String toString() {
		
		return "Next: " + next + "/n";
		
	}

}

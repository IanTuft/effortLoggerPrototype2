package application;

public class SearchData {
	
	private DataNode dataNodeHead;
	private DataNode temp;
	
	
	//Default Constructor.
	
	SearchData(){
		
		dataNodeHead = null;
		temp = null;
		
	}
	
	
	//Additional Constructors
	
	SearchData(DataNode dataNodeIn){
		
		dataNodeHead = dataNodeIn;
		temp = dataNodeHead;
		
	}
	
	//Getters and Setters
	
	public void setDataNode(DataNode dataNodeIn) {this.dataNodeHead = dataNodeIn; this.temp = dataNodeIn;}
	
	
	//Public Methods
	
	public DataNode findProjects(String projectName) {
		int count = 0;
		DataNode nodeReturn = null;
		DataNode firstNode = new DataNode();
		temp = dataNodeHead;
		
		if(temp != null) {
			
			if(temp.getNext() == null && temp.getProjectName().equals(projectName)) {
				System.out.println("only 1 data to return");
				return temp;
				
			}
		
			while(temp.getNext() != null) {
				
				if(temp.getProjectName().equals(projectName)) {
					
					if(nodeReturn == null) {
						
						firstNode.copy(temp);
						nodeReturn = firstNode;
						nodeReturn.setPrevious(null);
						nodeReturn.setNext(null);
						System.out.println("Just added: " + nodeReturn.display());
						
					}
					else {
						
						DataNode additionalNode = new DataNode();
						
						additionalNode.copy(temp);
						System.out.println("Just copied: " + additionalNode.display());
						nodeReturn.setPrevious(additionalNode);
						additionalNode.setNext(nodeReturn);
						additionalNode.setPrevious(null);
	
						nodeReturn = additionalNode;
						
					}
					count++;
				}
				
				
				temp = temp.getNext();
				
			}
			
			if(temp.getNext() == null && temp.getProjectName().equals(projectName)) {
				
				if(nodeReturn == null) {
					
					firstNode.copy(temp);
					nodeReturn = firstNode;
					nodeReturn.setPrevious(null);
					nodeReturn.setNext(null);
					System.out.println("Just added: " + nodeReturn.display());
					
				}
				else {
					
					DataNode additionalNode = new DataNode();
					
					additionalNode.copy(temp);
					System.out.println("Finally: " + nodeReturn.display());
					nodeReturn.setPrevious(additionalNode);
					additionalNode.setNext(nodeReturn);
					additionalNode.setPrevious(null);

					nodeReturn = additionalNode;
					
				}
				count++;
			}
		
		}
		System.out.println("count is: " + count);
		return nodeReturn;
		
	}
	
	public DataNode findLifecycles(String lifecycle) {
		
		DataNode nodeReturn = null;
		DataNode firstNode = new DataNode();
		temp = dataNodeHead;
		
		if(temp != null) {
			
			if(temp.getNext() == null && temp.getLifeCycleStep().equals(lifecycle)) {
				
				return temp;
				
			}
		
			while(temp.getNext() != null) {
				
				if(temp.getLifeCycleStep().equals(lifecycle)) {
					
					if(nodeReturn == null) {
						
						firstNode.copy(temp);
						nodeReturn = firstNode;
						nodeReturn.setPrevious(null);
						nodeReturn.setNext(null);
						System.out.println("Just added: " + nodeReturn.display());
						
					}
					else {
						
						DataNode additionalNode = new DataNode();
						
						additionalNode.copy(temp);
						
						nodeReturn.setPrevious(additionalNode);
						additionalNode.setNext(nodeReturn);
						additionalNode.setPrevious(null);
	
						nodeReturn = additionalNode;
						
					}
					
				}
				
				
				temp = temp.getNext();
				
			}
			
			if(temp.getNext() == null && temp.getLifeCycleStep().equals(lifecycle)) {
				
				if(nodeReturn == null) {
					
					firstNode.copy(temp);
					nodeReturn = firstNode;
					nodeReturn.setPrevious(null);
					nodeReturn.setNext(null);
					System.out.println("Just added: " + nodeReturn.display());
					
				}
				else {
					
					DataNode additionalNode = new DataNode();
					
					additionalNode.copy(temp);
					
					nodeReturn.setPrevious(additionalNode);
					additionalNode.setNext(nodeReturn);
					additionalNode.setPrevious(null);

					nodeReturn = additionalNode;
					
				}
				
			}
		
		}
		
		return nodeReturn;
		
	}
	
	public DataNode findEfforts(String effort) {
		
		DataNode nodeReturn = null;
		DataNode firstNode = new DataNode();
		temp = dataNodeHead;
		
		if(temp != null) {
			
			if(temp.getNext() == null && temp.getEffortCategory().equals(effort)) {
				
				return temp;
				
			}
		
			while(temp.getNext() != null) {
				
				if(temp.getEffortCategory().equals(effort)) {
					
					if(nodeReturn == null) {
						
						firstNode.copy(temp);
						nodeReturn = firstNode;
						nodeReturn.setPrevious(null);
						nodeReturn.setNext(null);
						System.out.println("Just added: " + nodeReturn.display());
						
					}
					else {
						
						DataNode additionalNode = new DataNode();
						
						additionalNode.copy(temp);
						
						nodeReturn.setPrevious(additionalNode);
						additionalNode.setNext(nodeReturn);
						additionalNode.setPrevious(null);
	
						nodeReturn = additionalNode;
						
					}
					
				}
				
				
				temp = temp.getNext();
				
			}
			
			if(temp.getNext() == null && temp.getEffortCategory().equals(effort)) {
				
				if(nodeReturn == null) {
					
					firstNode.copy(temp);
					nodeReturn = firstNode;
					nodeReturn.setPrevious(null);
					nodeReturn.setNext(null);
					System.out.println("Just added: " + nodeReturn.display());
					
				}
				else {
					
					DataNode additionalNode = new DataNode();
					
					additionalNode.copy(temp);
					
					nodeReturn.setPrevious(additionalNode);
					additionalNode.setNext(nodeReturn);
					additionalNode.setPrevious(null);

					nodeReturn = additionalNode;
					
				}
				
			}
		
		}
		
		return nodeReturn;

	}
	
}

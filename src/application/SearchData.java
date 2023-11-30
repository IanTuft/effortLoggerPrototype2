package application;
//All functionality: Andrew Thomas
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
	
	/**
	 * Search a given linked list of DataNodes for all those nodes that have the given project name
	 * And return those nodes as a new linked list of DataNodes.
	 * @param projectName The project name to search for.
	 * @return Returns a linked list of DataNodes that all have the given project name.
	 */
	public DataNode findProjects(String projectName) {
		
		DataNode nodeReturn = null;
		DataNode firstNode = new DataNode();
		temp = dataNodeHead;
		
		if(temp != null) {//Ensure there is data to search through
			
			//Case check: There is only one DataNode in the linked list.
			//Check if that single DataNode matches the given criteria.
			if(temp.getNext() == null && temp.getProjectName().equals(projectName)) {
				
				firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
				
				nodeReturn = firstNode;
				
				//Ensure no loose connections
				nodeReturn.setNext(null);
				nodeReturn.setPrevious(null);
				
				return nodeReturn;
				
			}
			
			//Case check: There are multiple DataNodes in the linked list.
			//Check each node and see if it matches the given criteria.
			while(temp.getNext() != null) {
				
				if(temp.getProjectName().equals(projectName)) { //Check for match
					
					if(nodeReturn == null) { //Use if this is the first DataNode that matches
						
						firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
						nodeReturn = firstNode;
						
						//Ensure no loose connections.
						nodeReturn.setPrevious(null);
						nodeReturn.setNext(null);
						
					}
					else { //Use if there is at least one prior match
						
						DataNode additionalNode = new DataNode();
						
						additionalNode.copy(temp); //Copy. Do not assign or bad pointer things happen.

						//Add to front of list
						nodeReturn.setPrevious(additionalNode);
						additionalNode.setNext(nodeReturn);
						additionalNode.setPrevious(null);
	
						nodeReturn = additionalNode;
						
					}

				}
				
				temp = temp.getNext(); //Move through linked list
				
			}
			
			//Case check: There are multiple DataNodes in the linked list and we need to check the last node.
			//Check the last node in the list and see if it matches the given criteria.
			if(temp.getNext() == null && temp.getProjectName().equals(projectName)) {
				
				if(nodeReturn == null) {//Use if this is the first DataNode that matches
					
					firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
					nodeReturn = firstNode;
					
					//Ensure no loose connections
					nodeReturn.setPrevious(null);
					nodeReturn.setNext(null);
					
				}
				else { //Use if there is at least one prior match
					
					DataNode additionalNode = new DataNode();
					
					additionalNode.copy(temp); //Copy. Do not assign or bad things happen.
					
					//Add to front of list
					nodeReturn.setPrevious(additionalNode);
					additionalNode.setNext(nodeReturn);
					additionalNode.setPrevious(null);

					nodeReturn = additionalNode;
					
				}

			}
		
		}

		return nodeReturn;
		
	}
	/**
	 * Search a given linked list of DataNodes for all those nodes that have the given life cycle
	 * And return those nodes as a new linked list of DataNodes.
	 * @param projectName The project name to search for.
	 * @return Returns a linked list of DataNodes that all have the given project name.
	 */
	public DataNode findLifecycles(String lifecycle) {
		
		DataNode nodeReturn = null;
		DataNode firstNode = new DataNode();
		temp = dataNodeHead;
		
		if(temp != null) {//Ensure there is data to search through
			
			//Case check: There is only one DataNode in the linked list.
			//Check if that single DataNode matches the given criteria.			
			if(temp.getNext() == null && temp.getLifeCycleStep().equals(lifecycle)) {
				
				firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
				
				nodeReturn = firstNode;
				
				//Ensure no loose connections
				nodeReturn.setNext(null);
				nodeReturn.setPrevious(null);
				
				return nodeReturn;
				
			}
			
			//Case check: There are multiple DataNodes in the linked list.
			//Check each node and see if it matches the given criteria.
			while(temp.getNext() != null) {
				
				if(temp.getLifeCycleStep().equals(lifecycle)) { //Check for match
					
					if(nodeReturn == null) { //Use if this is the first DataNode that matches
						
						firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
						nodeReturn = firstNode;
						
						//Ensure no loose connections
						nodeReturn.setPrevious(null);
						nodeReturn.setNext(null);
						
					}
					else { //Use if there is at least one prior match
						
						DataNode additionalNode = new DataNode();
						
						additionalNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
						
						nodeReturn.setPrevious(additionalNode);
						additionalNode.setNext(nodeReturn);
						additionalNode.setPrevious(null);
	
						nodeReturn = additionalNode;
						
					}
					
				}
				
				temp = temp.getNext();//Move through linked list
				
			}
			
			//Case check: There are multiple DataNodes in the linked list and we need to check the last node.
			//Check the last node in the list and see if it matches the given criteria.
			if(temp.getNext() == null && temp.getLifeCycleStep().equals(lifecycle)) {
				
				if(nodeReturn == null) { //Use if this is the first DataNode that matches
					
					firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
					nodeReturn = firstNode;
					
					//Ensure no loose connections
					nodeReturn.setPrevious(null);
					nodeReturn.setNext(null);
					
				}
				else { //Use if there is at least one prior match
					
					DataNode additionalNode = new DataNode();
					
					additionalNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
					
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
				
				firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
				
				nodeReturn = firstNode;
				
				//Ensure no loose connections
				nodeReturn.setNext(null);
				nodeReturn.setPrevious(null);
				
				return nodeReturn;
				
			}
		
			//Case check: There are multiple DataNodes in the linked list.
			//Check each node and see if it matches the given criteria.
			while(temp.getNext() != null) {
				
				if(temp.getEffortCategory().equals(effort)) { //Check for match
					
					if(nodeReturn == null) { //Use if this is the first DataNode that matches
						
						firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
						nodeReturn = firstNode;
						
						//Ensure no loose connections
						nodeReturn.setPrevious(null);
						nodeReturn.setNext(null);
						
					}
					else { //Use if there is at least one prior match
						
						DataNode additionalNode = new DataNode();
						
						additionalNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
						
						nodeReturn.setPrevious(additionalNode);
						additionalNode.setNext(nodeReturn);
						additionalNode.setPrevious(null);
	
						nodeReturn = additionalNode;
						
					}
					
				}
				
				temp = temp.getNext();//Move through linked list
				
			}
			
			//Case check: There are multiple DataNodes in the linked list and we need to check the last node.
			//Check the last node in the list and see if it matches the given criteria.
			if(temp.getNext() == null && temp.getEffortCategory().equals(effort)) {
				
				if(nodeReturn == null) { //Use if this is the first DataNode that matches
					
					firstNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
					nodeReturn = firstNode;
					
					//Ensure no loose connections
					nodeReturn.setPrevious(null);
					nodeReturn.setNext(null);
					
				}
				else { //Use if there is at least one prior match
					
					DataNode additionalNode = new DataNode();
					
					additionalNode.copy(temp); //Copy. Do not assign or bad pointer things happen.
					
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

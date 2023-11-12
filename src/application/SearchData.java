package application;

public class SearchData {
	
	private DataNode dataNodeHead;
	private DataNode temp;
	
	
	//Default Constructor. DO NOT USE.
	
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
	
	public DataNode pullTags(String tagIn) {
		
		DataNode nodeReturn = new DataNode();
		temp = dataNodeHead;
		
		while(temp.getNext() != null) {
			
			if(temp.getPrimaryTag().equals(tagIn)) {
				
				if(nodeReturn.getPrimaryTag().equals("NULL"))
					nodeReturn.copy(temp);
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
		
		if(nodeReturn.getPrimaryTag().equals("NULL"))
			return null;
		
		return nodeReturn;
		
	}

}

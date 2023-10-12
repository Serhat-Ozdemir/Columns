
public class SingleLinkedList {
	SingleNode head;
	
	public void add(Object data) {
		if(head == null) {
			SingleNode newSingleNode = new SingleNode(data);
			head = newSingleNode;
		}else {
			SingleNode temp = head;
			while(temp.getLink() != null) {
				temp = temp.getLink();
			}
			SingleNode newSingleNode = new SingleNode (data);
			temp.setLink(newSingleNode);
		}
	}
	public int size() {
		if(head == null) {
			return 0;
		}else {
			int count = 0;
			SingleNode temp = head;
			while(temp != null) {
				temp = temp.getLink();
				count++;
			}
			return count;
		}
		
	}
	public void display() {
		if(head == null) {
			System.out.println("List is empty!");
		}else {
			SingleNode temp = head;
			while(temp != null) {
				System.out.print(temp.getData() + " ");
				
				temp = temp.getLink();
			}
		}
	}
	
	public void remove(Object dataToDelete) {
		if(head == null)
			System.out.println("Linked list is empty");
		else {
			if((int) head.getData() == (int) dataToDelete) 
				head = head.getLink();
			else {
				SingleNode previous = null;
				SingleNode temp = head;
				while(temp != null) {
					if((int) temp.getData() == (int) dataToDelete) {
						previous.setLink(temp.getLink());
						temp = previous;
						break;
						
					
					}
					previous = temp;
					temp = temp.getLink();
				}
			}
			
		}
		
	}

	public int findMax()
	{
		if (head == null)
		{
			System.err.println("The Linked List is empty");
			return Integer.MIN_VALUE;
		}
		else
		{
		   int maxVal = Integer.MIN_VALUE;
			
		   SingleNode temp = head;
			
		   while(temp != null)
		   {
			  if ((int) temp.getData() > maxVal)
			  {
				  maxVal = (int) temp.getData();
			  }			
			  temp = temp.getLink();
		}		
	      return maxVal;
		}
	}
	public boolean search(Object data) {
		if(head == null) {
			System.out.println("List is empty");
			return false;
		}
		else {
			SingleNode temp = head;
			while(temp != null) {
				if((Integer) temp.getData() == (Integer)data) {
					return true;
					
				}
				temp = temp.getLink();
			}
			return false;
		
		}
	}
	public  int transfer() {	
		int temp = (int)head.getData();
		
		int dataToReturn = (int)head.getData();
		head = head.getLink();

		return dataToReturn;
	}
	

}

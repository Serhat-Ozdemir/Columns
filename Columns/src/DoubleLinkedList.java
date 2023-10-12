
public class DoubleLinkedList {
	private  DoubleNode head;
	private DoubleNode tail;
	
	public DoubleLinkedList() {
		head =null;
		tail = null;
	}
	public void add(Object dataToAdd) {//Add new Node to the end
		if(head == null && tail == null) {
			DoubleNode newNode = new DoubleNode(dataToAdd);
			head = newNode;//pointing the first node
			tail = newNode;//pointing the last node
		}
		else {
			DoubleNode newnode = new DoubleNode(dataToAdd);
			newnode.setPrev(tail);
			tail.setNext(newnode);
			tail = newnode;
		}
	}

	
	public int size() {
		int count = 0;
		if(head == null)
			System.out.println("List is empty");
		else {
			DoubleNode temp = head;
			while(temp != null) {
				count ++;
				temp = temp.getNext();
			}
		}
		return count;
	}
	
	public boolean search(int number) {
		if(head == null)
			System.out.println("List is empty");
		else {
			DoubleNode temp = tail;
			while(temp != null) {
				if(number == (int)temp.getData()) {
					return true;
				}
				temp = temp.getPrev();
			}
		}
		return false;
	}
	public void display1() {
		
		if(head == null)
			System.out.println("List is empty");
		else {
			System.out.println();
			DoubleNode temp = head;
			while(temp!=null) {
				
				System.out.println(((Player)temp.getData()).getName() + " " + ((Player)temp.getData()).getScore());
				temp = temp.getNext();
			
			}
			System.out.println();
		}
	}
	public void display2() {
		if(head == null)
			System.out.println("List is empty");
		else {
			DoubleNode temp = tail;
			while(temp!=null) {
				System.out.println(temp.getData() + " ");
				temp = temp.getPrev();
			}
			System.out.println();
		}
	}
	public Object getElements(int x) {
		if(head == null) {
			System.out.println("List is empty");
			return null;
		}
		else if (x<0 || x> size()) {
			System.out.println("Index is out of range");
			return null;
		}
		else {
			DoubleNode temp = head;
			int count = 1;
			while(temp != null) {
				if(x == count) {
					return temp.getData();
				}
				temp = temp.getNext();
				count++;
			}
			return null;
		}
	}
	
	public void swap(int x) {
		if(head == null) {
			System.out.println("List is empty");
			
		}
		else if (x<0 || x> size()) {
			System.out.println("Index is out of range");
			
		}
		else {
			DoubleNode temp = head;
			int element1 = -1;
			int count = 1;
			while(temp != null) {
				if(count ==x) {
					element1 = (int)temp.getData();
					break;
				}
				temp = temp.getNext();
				count ++;
			}
			
			int element2 =-1;
			DoubleNode temp2 = tail;
			count =1;
			while(temp2 != null) {
				if(count ==x) {
					element2 = (int) temp.getData();
					temp2.setData(element1);
					break;
				}
				temp2 = temp2.getPrev();
				count++;
			}
			temp.setData(element2);
		}
	}
	
	
	
	 public void sortList() {
	        DoubleNode current = null, index = null;
	        Object temp;
	       
	        //Check whether list is empty
	        if(head == null) {
	            System.out.println("List is empty");
	        }
	        else {
	            //Current will point to head
	            for(current = head; current.next != null; current = current.next) {
	                //Index will point to node next to current
	                for(index = current.next; index != null; index = index.next) {
	                    //If current's data is greater than index's data, swap the data of current and index
	                    if(((Player)current.data).getScore() < ((Player)index.data).getScore()) {
	                        temp =  current.data;
	                        current.data = index.data;
	                        index.data = temp;
	                        
	                    }
	                   
	                    
	                }
	               
	            }
	        }
	    }
	
	
	
	
	
	
	
	
}

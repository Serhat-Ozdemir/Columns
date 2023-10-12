
public class DoubleNode {
	Object data;
	private DoubleNode prev;
	DoubleNode next;
	
	public  DoubleNode (Object dataToAdd) {
		data = dataToAdd;
		prev = null;
		next = null;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public DoubleNode getPrev() {
		return prev;
	}
	public void setPrev(DoubleNode prev) {
		this.prev = prev;
	}
	public DoubleNode getNext() {
		return next;
	}
	public void setNext(DoubleNode next) {
		this.next = next;
	}

}

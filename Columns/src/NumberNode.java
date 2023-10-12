
public class NumberNode {
	private Object number;
	private NumberNode next;
	
	public NumberNode(Object dataToAdd) {
		number = dataToAdd;
		next = null;
	}

	public Object getNumber() {
		return number;
	}

	public void setNumber(Object number) {
		this.number = number;
	}

	public NumberNode getNext() {
		return next;
	}

	public void setNext(NumberNode next) {
		this.next = next;
	}
	

}

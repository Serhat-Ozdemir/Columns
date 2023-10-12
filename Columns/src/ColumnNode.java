
public class ColumnNode {
	private Object columnsCode;
	private ColumnNode right;
	private NumberNode down;
	
	public ColumnNode(Object dataToAdd) {
		columnsCode = dataToAdd;
		down = null;
		right = null;
	}

	public Object getColumnsCode() {
		return columnsCode;
	}

	public void setColumnsCode(Object columnsCode) {
		this.columnsCode = columnsCode;
	}

	public ColumnNode getRight() {
		return right;
	}

	public void setRight(ColumnNode right) {
		this.right = right;
	}

	public NumberNode getDown() {
		return down;
	}

	public void setDown(NumberNode down) {
		this.down = down;
	}
	

}

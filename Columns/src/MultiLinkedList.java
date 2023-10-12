import java.awt.Color;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class MultiLinkedList {
    public ColumnNode columnNode;
	private NumberNode numberNode;
	public static enigma.console.Console cn = Enigma.getConsole("Columns",60,30,20);

	
	public MultiLinkedList() {
		columnNode=null;
		numberNode=null;
	}
	
	public void addColumn(int columnName) {
		if(columnNode==null) {
			ColumnNode newNode=new ColumnNode(columnName);
			columnNode=newNode;
		}
		else {
			ColumnNode temp=columnNode;
			while(temp.getRight()!=null) {
				temp=temp.getRight();
			}
			ColumnNode newNode=new ColumnNode(columnName);
			temp.setRight(newNode);
		}
	}
	
	public void addNumber(int columnName,int number) {
		if(columnNode==null) {
			System.out.println("Firstly,please add column name");
		}
		else {
			ColumnNode temp=columnNode;
			while(temp!=null) {
				if(temp.getColumnsCode().equals(columnName)) {
					NumberNode temp2=temp.getDown();
					if(temp2==null) {
						NumberNode newNode=new NumberNode(number);
						temp.setDown(newNode);
					}
					else {
						while(temp2.getNext()!=null)
							temp2=temp2.getNext();
						NumberNode newnode=new NumberNode(number);
						temp2.setNext(newnode);
					}
				}
				temp=temp.getRight();
			}
		}
	}
	
	public int sizeColumn() {
		int count=0;
		if(columnNode==null) {
			System.out.println("linked list is empty");
		}
		else {
			ColumnNode temp=columnNode;
			while(temp!=null) {
				count++;
				temp=temp.getRight();
			}
		}
		return count;
	}
	
	public void display() {
		if(columnNode==null) {
		}
		else {
			int column=1;
			int row=3;
			ColumnNode temp =columnNode;
			while(temp!=null) {
				NumberNode temp2=temp.getDown();
				while(temp2!=null) {
					cn.getTextWindow().setCursorPosition(column+16, row);
					Columns.columnColor(column);
					System.out.println(temp2.getNumber());
					Columns.white();
					temp2=temp2.getNext();
					row++;
				}
				column=column+8;
				row=3;
				temp=temp.getRight();
			}
					
		}
	}
	
	public int lastNumberFor(int whichColumn) {
		ColumnNode temp =columnNode;
		switch(whichColumn) {
		case 2: temp=temp.getRight();break;
		case 3: temp=temp.getRight().getRight();break;
		case 4: temp=temp.getRight().getRight().getRight();break;
		case 5: temp=temp.getRight().getRight().getRight().getRight();break;
		
		}
		int temp3=0;
		NumberNode temp2=temp.getDown();
		while(temp2!=null) {
			temp3=(int)temp2.getNumber();
			temp2=temp2.getNext();	
		}
		return temp3;
	}
	
	public int elementSizeInColumn(int x) {
		ColumnNode temp=columnNode;
		int row=0;
		switch(x) {
		case 2: temp=temp.getRight();break;
		case 3: temp=temp.getRight().getRight();break;
		case 4: temp=temp.getRight().getRight().getRight();break;
		case 5: temp=temp.getRight().getRight().getRight().getRight();break;
		default:
			break;
		}
		NumberNode temp2=temp.getDown();
		while(temp2!=null) {
			temp2=temp2.getNext();
			row++;
		}
		return row;
	}
	public SingleLinkedList deleteFromLast(int columnNumber, int row) {
		SingleLinkedList toBeTransferred = new SingleLinkedList();
		ColumnNode temp=columnNode;
		for(int i=0; i<columnNumber-1;i++)
			temp=temp.getRight();
		NumberNode temp2=temp.getDown();
		NumberNode deleteIndex;
		for(int i =0; i< row-1;i++) {
			temp2 = temp2.getNext();
		}
		deleteIndex = temp2;
		while(temp2.getNext()!=null) {
			toBeTransferred.add(temp2.getNumber());
			temp2 = temp2.getNext();
			
		}
		
		toBeTransferred.add(temp2.getNumber());
		if(row ==0)
			temp.setDown(null);
		deleteIndex.setNext(null);;

		return toBeTransferred;
	}
	
	public int [] deleteElementAccordIndex(int px,int py) {
		ColumnNode temp=columnNode;
		if(px==13  && elementSizeInColumn(1)>= py-7) {
			NumberNode temp2=temp.getDown();
			int howManyDelete=elementSizeInColumn(1)-py+7;
			int[]tempArr=new int[howManyDelete];
			for(int i=0;i<howManyDelete;i++) {
//				tempArr[i]=deleteFromLast(5,5);
			}
			return tempArr;
		}
		else {
			int []deneme=new int [2];
			return deneme;
		}
	}
	
	public void deleteOrderedNumbers(ColumnNode cm,NumberNode nm) {
		
		NumberNode temp = nm;
		int count =1;
		
		while( count != 11) {
			temp = temp.getNext();
			count++;
		}
		if(cm.getDown() == nm) {
			cm.setDown(temp);
		}else {
			nm.setNext(temp.getNext());	
		}
	
	}
	
	
	
	
	
	
	
	
}

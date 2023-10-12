import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Columns {
	public static SingleLinkedList sll = new SingleLinkedList();
	public static SingleLinkedList sllRandom = new SingleLinkedList();
	public static SingleLinkedList tempSll = new SingleLinkedList();
	public static SingleLinkedList sll1 = new SingleLinkedList();
	public static DoubleLinkedList dll = new DoubleLinkedList();
	public static MultiLinkedList mll = new MultiLinkedList();
	public static enigma.console.Console cn = Enigma.getConsole("COLUMNS",80,30,20);
	public static String inp ;
	public static int tempCN, transferCount = 0,score =0 ;
	public static double endScore =0;
	
	
	Columns() throws Exception{
		screen();
		if(inp.equals("3") ) {
			
			Thread.sleep(1000);
			ConsoleClear();
			screen();
		}
		if(inp.equals("2") ) {
			
			Thread.sleep(5000);
			ConsoleClear();
			screen();
		}
		 if (inp.equals("1") ) {
			
			 startGame();
		}
	
	}
	public void startGame() throws Exception {
		  KeyList kl = new KeyList();
		  for(int i =1; i<= 29;i=i+7)
			  mll.addColumn(i);
			 
		
		  box();
		
		 int boxR=0,boxTransfer =0 ; // 
		 boolean flag =false,flag2 = true;
		 
		 for(int  i = 0; i<50; i++ ) {
			 boxShuffle();
		 }
		int x=1;
		for(int i =1; i<= 29;i=i+7) {
			
			for(int j=0;j<6;j++) {
				boxR = boxRandom();
				mll.addNumber(i,boxR );
				cn.getTextWindow().setCursorPosition(x+16, j+3);
				columnColor(x);
				System.out.println(boxR);
				white(); 
			}
			x +=8;
		}
		long starttime = System.currentTimeMillis();
		long timer=0;
		boxR =0;
		 cn.getTextWindow().setCursorPosition(66, 1);
		 cyan();
    	 System.out.print(transferCount);
    	 white();
		  while(true) {
			  
			  timer = 300-((System.currentTimeMillis() - starttime)/1000);   //Timer
			  
			  if(timer<100) {
				  cn.getTextWindow().setCursorPosition(66, 3);
				  cyan();
				  System.out.print(timer+ " ");
				  white();
			  }else {
				  cn.getTextWindow().setCursorPosition(66, 3);
				  cyan();
				  System.out.print(timer);
				  white(); 
			  }
			  
			  if(kl.keypr==1) {    // if keyboard button pressed
				 
		            if(kl.rkey==KeyEvent.VK_B && sllRandom.size() !=0 && boxR ==0) {
		            	boxR = boxRandom();
		            
		            	 
		            	 if(boxR<10) {
		            		 cn.getTextWindow().setCursorPosition(56, 8);
		            		 yellow();
			            	 System.out.print(boxR+" ");
			            	 
			  
			            	 white();
		            	 }else {
		            		 yellow();
		            		 cn.getTextWindow().setCursorPosition(56, 8);
			            	 System.out.print(boxR);
			            	 white();
		            	 }
		            	
		            	
		            }   
		       
		        
		            
		            kl.keypr=0;    // last action  
		         }
			     
		         if((kl.mousepr==1)&&( boxR !=0) && (kl.mousex == 56 && kl.mousey == 8)) {  // if mouse button pressed
			         cn.getTextWindow().setCursorPosition(56, 8);	         
			         System.out.println("  ");
			         flag= true;
			         kl.mousepr=0; 
		         }
		         if(kl.mousepr == 1 && boxR !=0 && flag == true ) {
		        	
			       	 if( kl.mousepr == 1 && (kl.mousex == 17||kl.mousex == 25||kl.mousex == 33||kl.mousex == 41|kl.mousex == 49)&&kl.mousey>=3) {
			       		boxTransfer = (int)mll.lastNumberFor((kl.mousex/8)-1);
			       		 if(boxTransfer == boxR ||boxTransfer - boxR == -1 ||boxTransfer - boxR == 1 
			       				 ||((mll.elementSizeInColumn((kl.mousex/8)-1) == 0) && (boxR == 10 ||boxR == 1)  ) ) {
			       			 mll.addNumber(((((kl.mousex/8)-1)-1)*7)+1, boxR);
							 columnsClear();
				        	 mll.display();
				        	 cn.getTextWindow().setCursorPosition(66, 1);
				        	 transferCount++;
				        	 cyan();
				        	 System.out.print(transferCount);
				        	 white();
					       	 boxR=0;
					         flag = false;
					         kl.mousepr=0; 
					         isOrdered(kl);
							}
			       		
							
			       		

			       	 }else {
			       		 
			       		 cn.getTextWindow().setCursorPosition(56, 8);
			       		 yellow();
			       		 System.out.println(boxR);
			       		 white();
				         flag = false;
				         kl.mousepr=0; 
			       	 }
			       	 
		         }
		         if( !flag && tempSll.size()==0 && kl.mousepr == 1 && (kl.mousex == 17||kl.mousex == 25||kl.mousex == 33||kl.mousex == 41|kl.mousex == 49)&&kl.mousey>=3) {
		        	
		        	 transfer(kl);
		        	 columnsClear();
		        	 mll.display();
		        	 flag2 = false;
		        	
		        	 
		         }
		         else if( !flag &&tempSll.size()>0 &&kl.mousepr == 1 && (kl.mousex == 17||kl.mousex == 25||kl.mousex == 33||kl.mousex == 41|kl.mousex == 49)&&kl.mousey>=3&& flag2 == false) {
		        	 if(tempCN != (kl.mousex/8)-1 && controlTransfer(kl) && (kl.mousex == 17||kl.mousex == 25||kl.mousex == 33||kl.mousex == 41|kl.mousex == 49)&&kl.mousey>=3 ) {
		        		 transfer(kl);
		        		 columnsClear();
			        	 mll.display();
			        	 flag2 = true;
			        	 cn.getTextWindow().setCursorPosition(66, 1);
			        	 transferCount++;
			        	 cyan();
			        	 System.out.print(transferCount);
			        	 white();
			        	 isOrdered(kl);
		        	 }else if(tempCN == (kl.mousex/8)-1  && (kl.mousex == 17||kl.mousex == 25||kl.mousex == 33||kl.mousex == 41|kl.mousex == 49)&&kl.mousey>=3 ) {
		        		 transfer(kl);
		        		 columnsClear();
			        	 mll.display();
		        	 }
		        	 
		        	
		        	 
		         }
		         if(kl.rkey==KeyEvent.VK_E) {
						cn.getTextWindow().setCursorPosition(31 , 21);
		                System.out.print("Game is closing... ");
		                Thread.sleep(1500);
		                ConsoleClear();
						highScoreTable();
						if(score != 0)
							endScore = 100*(score/1000)+(score/transferCount);
						Player player = new Player("Player", endScore);
						dll.add(player);
					    dll.sortList();
					    dll.display1();
					    Thread.sleep(3000);
		                System.exit(0);
		         }
		         
		         if(score == 5000) {
		        	 cn.getTextWindow().setCursorPosition(31 , 21);
		                System.out.print("Congratulations, You Win!!!");
		                Thread.sleep(1500);
		                ConsoleClear();
						highScoreTable();
						if(score != 0)
							endScore = 100*(score/1000)+(score/transferCount);
						Player player = new Player("Player", endScore);
						dll.add(player);
					    dll.sortList();
					    dll.display1();
					    Thread.sleep(3000);
		                System.exit(0);
		         }
		         if(timer == 0) {
		        	 cn.getTextWindow().setCursorPosition(31 , 21);
		                System.out.print("Game over");
		                Thread.sleep(1500);
		                ConsoleClear();
						highScoreTable();
						if(score != 0)
							endScore = 100*(score/1000)+(score/transferCount);
						
						Player player = new Player("Player", endScore);
						dll.add(player);
					    dll.sortList();
					    dll.display1();
					    Thread.sleep(3000);
		                System.exit(0);
		         }
		         
		         kl.mousepr=0; 	          // last action 
		           
		         
		       
			 
			
			  
			  Thread.sleep(25);	
			
		  }		
			
	}
	public  void isOrdered(KeyList kl) {
		int clnumber = (kl.mousex/8)-1;
		int countof = 1;
		ColumnNode temp = mll.columnNode;
		NumberNode numb = null;
		for(int i = 0 ; i< clnumber -1 ; i++) {
			temp = temp.getRight();
		}
		NumberNode temp2 = temp.getDown();
		if((int)temp2.getNumber() == 1 ||(int)temp2.getNumber() == 10) {
			numb = temp2;
		}
		boolean control = false, control2 = false;
	
		if(mll.elementSizeInColumn(clnumber) >= 10) {
			
			while(temp2.getNext() != null) {
				
				if(((int) temp2.getNext().getNumber() ==1 ||(int)temp2.getNext().getNumber()==10) && (control == false && control2 == false)  ) {
					numb = temp2;	
				}
				
				if((int)temp2.getNumber() == 1  ||  control == true) {
					if((int)temp2.getNumber()+1 != (int)temp2.getNext().getNumber()) {
						countof =1;
						control = false;
					}else {
						countof++;
						control = true;
					}
				}else if((int)temp2.getNumber() == 10||  control2 == true) {
					if((int)temp2.getNumber()-1 != (int)temp2.getNext().getNumber()) {
						countof =1;
						control2 = false;
					}else {
						countof++;
						control2 = true;
					}
				}
				 temp2 = temp2.getNext();
				 if(countof ==10  ) {
					score =  score +1000;
					cn.getTextWindow().setCursorPosition(66, 2);
					cyan();
					System.out.println(score);
					white();
					 
					mll.deleteOrderedNumbers(temp,numb);
					columnsClear();
					mll.display();
				 }

				
				
			}
		}
		
		
		
	}
	
	public boolean controlTransfer(KeyList k) {
		int columnNumber= (k.mousex/8)-1;
		int last  = mll.lastNumberFor(columnNumber);
		int  temp = (int)sll1.head.getData();
		if(last == 0) {
			if(temp == 10 ||temp == 1) {
				sll1 = null;
				return true;
				
			}else
				return false;
				
		}
		else if(last - temp == -1 || last - temp == 1 ||last == temp ) {
			
			sll1 = null;
			return true;
			}
			
		else 
			return false;
				
		
	}
	
	public void transfer( KeyList kl) {
		int columnNumber= (kl.mousex/8)-1;
	    
	    	
		if(tempSll.size()==0 && kl.mousey-3 <=mll.elementSizeInColumn(columnNumber)) {
			tempCN = columnNumber;
			tempSll = mll.deleteFromLast(columnNumber, kl.mousey-3);
			if(4 <= kl.mousey) {
				
				 int  temp = (int)tempSll.head.getData();
				 tempSll.remove(temp);
				 sll1 = tempSll;
			 }else {
				 sll1 = tempSll;
			 }
			
			
		}
		else {
				while (tempSll.size()>0) 
					mll.addNumber(((columnNumber-1)*7)+1, tempSll.transfer());
		}
		

	}
	
	public void gameScreen() {
		System.out.println("                                                                               ");
		System.out.println("                 C1      C2      C3      C4      C5                            ");
		System.out.println("                 --      --      --      --      --                            ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");

		
		cn.getTextWindow().setCursorPosition(55, 1);
		blue();
		System.out.println("Transfer : ");
		white();
		cn.getTextWindow().setCursorPosition(55, 2);
		green();
		System.out.println("Score    : ");
		white();
		cn.getTextWindow().setCursorPosition(55, 3);
		magenta();
		System.out.println("Timer    : ");
		white();
		
		cn.getTextWindow().setCursorPosition(55, 6);
		
		System.out.print("Box");
		cn.getTextWindow().setCursorPosition(55, 7);
		
		System.out.print("+---+");
		cn.getTextWindow().setCursorPosition(55, 8);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(55, 9);
		System.out.print("+---+");
	
	
	}
	
	public void box() {
		

		for(int i=1; i<6;i++) {//Add numbers to List
			
			for(int j =1; j<11; j++) {
				sll.add(j);
			
			}
		}
		
		
		
	}
	
	public  void boxShuffle() {
		Random rn = new Random();
		int rand=0;
		boolean flag = true;
		
		while(flag) {
			if(sll.size()!=0) {
				rand = rn.nextInt(10)+1;
			
				if(sll.search(rand)) {
					sllRandom.add(rand);
					flag = false;
				}
			}else {
				
				break;
			}
			
			
		}		
		sll.remove(rand);
		
	}
	
	public int boxRandom() {
		int boxRandom =  (int)sllRandom.head.getData();
		sllRandom.remove(boxRandom);
		
		return boxRandom;
	}
	
	
	public void highScoreTable() throws IOException {
		File filecn = new File("HighScoreTable.txt");
		BufferedReader readercn = null;
		readercn = new BufferedReader(new FileReader(filecn));
		String linecn = readercn.readLine();
		
		while(linecn != null) {
			String[] participants = linecn.split(" ");
			String name = participants[0] + " "+ participants[1];
			String str = participants[2];
			double score = Double.parseDouble(str);
			Player player = new Player(name,score);
			dll.add(player);
	
			linecn = readercn.readLine();
			
			
			if(linecn == null) {
				readercn.close();
				break;
			}
		}
		
		
		
	}
	
	
	public void  screen() throws InterruptedException, IOException {
	
                                                                      
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		yellow();
		System.out.println("                            WELCOME TO SOLITAIRE GAME                          ");
		white();
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		
	
		redBack();
		borders(1,2,3);//start
		borders(5,6,7);//start game	
		borders(9,10,11);//game info 	
		borders(13,14,15);//high score table
		borders(17,18,19);//exit
		white();
		cn.getTextWindow().setCursorPosition(34, 6);
		System.out.print("1 - START GAME");
		cn.getTextWindow().setCursorPosition(34, 10);
		System.out.print("2 - GAME INFO");
		cn.getTextWindow().setCursorPosition(34, 14);
		System.out.print("3 - HIGHSCORE TABLE");
		cn.getTextWindow().setCursorPosition(34, 18);
		System.out.print("4 - EXIT");
		
		Scanner sc = new Scanner(System.in);
		
		cn.getTextWindow().setCursorPosition(26, 21);
		System.out.print("Please  enter your Choise :");
		inp = sc.next();
		int temp = 0;
		while(true) {
			if(tryParseInt(inp, temp) == 1) {
				greenBack();
				borders(5,6,7);//start game	
				white();
				Thread.sleep(1000);
				ConsoleClear();
				gameScreen();
				break;
			}else if(tryParseInt(inp, temp) == 2) {
				greenBack();
				borders(9,10,11);//game info 	
				white();
				Thread.sleep(1000);
				ConsoleClear();
				System.out.println("                                Aim of the game: "
						+ "\n*The game is played in 5 columns. Game elements are numbers (1-10). "
						+ "\n*The aim of the game is reaching the highest score by collecting number sets."
						+ "\n*Draw a number into the Box by pressing B key."
						+ "\n*The transfer is to form an ordered set (1-2-3-4-5-6-7-8-9-10) "
						+ "\n  or reverse ordered set "
						+ "(10-9-8-7-6-5-4-3-2-1) in a column."
						+ "\n*These numbers must be ordered and there must be no other numbers in the column"
						+ "\n*Player gets 1000 points column score for each ordered set."
						+ "\n*The completed set disappears.");

				
				break;
			}else if(tryParseInt(inp, temp) == 3) {
				greenBack();
				borders(13,14,15);//high score table
				
				white();
				Thread.sleep(1000);
				ConsoleClear();
				highScoreTable();
			    dll.sortList();
			    dll.display1();
				break;
			}
			
			else if(tryParseInt(inp, temp) == 4) {
				greenBack();
				borders(17,18,19);//exit
				white();
				Thread.sleep(1000);
				cn.getTextWindow().setCursorPosition(26 , 21);
				System.out.println("                            ");
				cn.getTextWindow().setCursorPosition(31 , 21);
                System.out.print("Game is closing... ");
                Thread.sleep(1000);
                System.exit(0);
				break;
			
			
			}else {
				cn.getTextWindow().setCursorPosition(26, 21);
				System.out.print("Wrong choice try again!                ");
				Thread.sleep(1500);
				cn.getTextWindow().setCursorPosition(26, 21);
				System.out.print("Please  enter your Choise : ");
				inp = sc.next();
			}
		
		}
		
		
	
	
	} 
	public static void columnColor(int columnNumber) {
        switch(columnNumber){
            case 1: 
                TextAttributes  write = new TextAttributes(Color.red);
                cn.setTextAttributes(write);
                break;
            case 9: 
                write = new TextAttributes(Color.yellow);
                cn.setTextAttributes(write);
                break;
            case 17: 
                write = new TextAttributes(Color.cyan);
                cn.setTextAttributes(write);
                break;
            case 25: 
                write = new TextAttributes(Color.pink);
                cn.setTextAttributes(write);
                break;
            case 33: 
                write = new TextAttributes(Color.green);
                cn.setTextAttributes(write);
                break;
        }
    }
	public void borders(int a,int b,  int d ) {
	
		cn.getTextWindow().setCursorPosition(26, a);//This for menu
		System.out.print("+++++++++++++++++++++++++++++");
	
		cn.getTextWindow().setCursorPosition(26,b);
		System.out.print("+");
		cn.getTextWindow().setCursorPosition(54,b);
		System.out.print("+");
		cn.getTextWindow().setCursorPosition(26, d);
		System.out.println("+++++++++++++++++++++++++++++");
		
	}
	public void ConsoleClear() {
		for(int i = 0; i< 31; i ++) {
			cn.getTextWindow().setCursorPosition(0, i);
			cn.getTextWindow().output("                                                                               ");
		  
		}
		  cn.getTextWindow().setCursorPosition(0, 0);
		
	}
	//Clears the numbers under columns
	public void columnsClear() {
		for(int i = 3; i< 31; i ++) {
			cn.getTextWindow().setCursorPosition(0, i);
			cn.getTextWindow().output("                                                      ");
		  
		}
		  cn.getTextWindow().setCursorPosition(0, 0);

	}
	 
	
	public static int tryParseInt(String value, int defaultVal) {
	    try {
	        return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	        return defaultVal;
	    }
	}
	public static void greenBack() {
		TextAttributes  write = new TextAttributes(Color.green,Color.green);
		cn.setTextAttributes(write);
	}
	public static void green() {
		TextAttributes  write = new TextAttributes(Color.green);
		cn.setTextAttributes(write);
	}
	public static void blue() {
		TextAttributes  write = new TextAttributes(Color.blue);
		cn.setTextAttributes(write);
	}
	public static void white() {
		TextAttributes  write = new TextAttributes(Color.white);
		cn.setTextAttributes(write);
	}
	public static void red() {
		TextAttributes  write = new TextAttributes(Color.red);
		cn.setTextAttributes(write);
	}
	public static void redBack() {
		TextAttributes  write = new TextAttributes(Color.red,Color.red);
		cn.setTextAttributes(write);
	}
	public static void gray() {
		TextAttributes  write = new TextAttributes(Color.GRAY);
		cn.setTextAttributes(write);
	}
	public static void grayBack() {
		TextAttributes  write = new TextAttributes(Color.GRAY,Color.GRAY);
		cn.setTextAttributes(write);
	}
	public static void yellow() {
		TextAttributes  write = new TextAttributes(Color.yellow);
		cn.setTextAttributes(write);
	}public static void magenta() {
		TextAttributes  write = new TextAttributes(Color.MAGENTA);
		cn.setTextAttributes(write);
	}
	public static void cyan() {
		TextAttributes  write = new TextAttributes(Color.cyan);
		cn.setTextAttributes(write);
	}
	

	
	
	
	
	

}

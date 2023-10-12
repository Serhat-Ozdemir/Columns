import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

public class KeyList {
	   public static enigma.console.Console cn = Enigma.getConsole("Columns",60,30,20);
	   public TextMouseListener tmlis; 
	   public KeyListener klis; 

	   // ------ Standard variables for mouse and keyboard ------
	   public int mousepr;          // mouse pressed?
	   public int mousex, mousey;   // mouse text coords.
	   public int keypr;   // key pressed?
	   public int rkey;    // key   (for press/release)
	   // ----------------------------------------------------
	   
	   
	   KeyList() throws Exception {   // --- Contructor
	                 
	      // ------ Standard code for mouse and keyboard ------ Do not change
	      tmlis=new TextMouseListener() {
	         public void mouseClicked(TextMouseEvent arg0) {}
	         public void mousePressed(TextMouseEvent arg0) {
	            if(mousepr==0) {
	               mousepr=1;
	               mousex=arg0.getX();
	               mousey=arg0.getY();
	            }
	         }
	         public void mouseReleased(TextMouseEvent arg0) {}
	      };
	      cn.getTextWindow().addTextMouseListener(tmlis);
	    
	      klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      cn.getTextWindow().addKeyListener((java.awt.event.KeyListener) klis);
	      
	
	     
	      
	      
	   }
}

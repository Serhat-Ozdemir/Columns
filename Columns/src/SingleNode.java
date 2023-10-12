public class SingleNode {
  private Object data;
  private SingleNode link; 
 
  

   public SingleNode(Object dataToAdd) {
	     data = dataToAdd;
	     link = null;
   }
   
   public Object getData() { return data; }
   public void setData(Object data) { this.data = data;  }

   public SingleNode getLink() { return link;  }
   public void setLink(SingleNode link) { this.link = link;   }   
}


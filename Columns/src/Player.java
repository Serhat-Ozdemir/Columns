
public class Player {
	private double score;
	private String name;
	
	public Player(String n, double s) {
		score =s;
		name = n;	
	}
	
	public void display() {	
	   System.out.println(name+ " " + score);		
	}		
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	
}

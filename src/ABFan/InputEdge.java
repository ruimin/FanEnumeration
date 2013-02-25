package ABFan;

public class InputEdge {
	public int label;
	public String i;
	public int color; // 1=red; 0=black
	private char[] rep={'a','b','c','d','e','f','g','h','i','j'};
	
	public InputEdge(int order,String rep,int col){
		label=order;
		i=rep;
		color=col;
	}
	

	public String toString(){
		return i;
		
	}
	
}

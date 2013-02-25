package KFan;

public class InputEdge {
	public int label;
	public String i;
	private boolean matched;
	private char[] rep={'a','b','c','d','e','f','g','h','i','j'};
	
	public InputEdge(int order,String rep){
		label=order;
		i=rep;
	}
	

	public String toString(){
		return i;
		
	}
	
}

package KFan;
import java.util.ArrayList;


public class VertexPebble {
	public int i;
	private boolean matched;
	private InputEdge matchTo;
	private boolean deleted;
	public ArrayList<InputEdge> edges;
	//how to identify the acutal vertex
	
	public VertexPebble(int order){
		i=order;
		edges=new ArrayList<InputEdge>();
	}
	

	public VertexPebble(int order,ArrayList<InputEdge> new_edges){
		i=order;
		edges=new_edges;
	}
	
	public void addEdge(InputEdge vertex){
		if(!edges.contains(vertex)){
			edges.add(vertex);
		}
	}
	
	
	public int numOfEdges(){
		return edges.size();
	}
	
	
}

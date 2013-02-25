package ABFan;
import java.util.ArrayList;


public class VertexPebble {
	public int i;
	public ArrayList<InputEdge> edges;
	public int color;// 1=aqua; 0=black
	//how to identify the actual vertex
	
	public VertexPebble(int order,int c){
		i=order;
		color=c;
		edges=new ArrayList<InputEdge>();
	}
	

	public VertexPebble(int order,ArrayList<InputEdge> new_edges,int c){
		i=order;
		color=c;
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

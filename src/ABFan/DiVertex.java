package ABFan;
import java.util.ArrayList;


public class DiVertex {
	int i;
	VertexPebble v1;
	InputEdge v2;
	boolean isV1=false;
	ArrayList<DiVertex> list;
	
	//create a di-graph vertex 
	public DiVertex(int order,VertexPebble v){
		i=order;
		list=new ArrayList<DiVertex>();
		v1=v;
		isV1=true;
	}
	public DiVertex(int order,InputEdge v){
		i=order;
		v2=v;
		list=new ArrayList<DiVertex>();
	}
	public void addEdge(DiVertex v){
		
		list.add(v);
	}
	
	

}

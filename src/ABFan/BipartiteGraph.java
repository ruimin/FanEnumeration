package ABFan;
import java.util.ArrayList;




public class BipartiteGraph {
	
	private ArrayList<VertexPebble> setOfV1;
	public int a;
	public int b;
	
	public BipartiteGraph(ArrayList<VertexPebble> newList){
		setOfV1=newList;
	}
	public int numOfV1(){
		return setOfV1.size();
	}
	
	public VertexPebble getCurrentVertex(){
		return setOfV1.get(0);
	}
	
	public VertexPebble getVertex(int i){
		return setOfV1.get(i);
	}
	
	public BipartiteGraph trimmGraph(BipartiteEdge e){
		InputEdge v2=e.v2;
		ArrayList<VertexPebble> newList=new ArrayList<VertexPebble>();
		for(int i=1;i<setOfV1.size();i++){
			VertexPebble v1=setOfV1.get(i);
			ArrayList<InputEdge> oldEdgeList=v1.edges;
			ArrayList<InputEdge> updatedEdgeList=new ArrayList<InputEdge>();
			for(int j=0;j<oldEdgeList.size();j++){
				InputEdge ve2=oldEdgeList.get(j);
				if(ve2.label!=v2.label){
					updatedEdgeList.add(ve2);
				}
				
			}
			VertexPebble newV1=new VertexPebble(v1.i,updatedEdgeList,v1.color);
			newList.add(newV1);
		}
		BipartiteGraph g=new BipartiteGraph(newList);
		g.a=a;
		g.b=b;
		return g ;
		
	}
	
	
	/**
	 * only keep e connects to e.v2
	 * @param e the G\E2
	 * @return
	 */
	public BipartiteGraph trimmGraphFixEdge(BipartiteEdge e){
		//the fixed edge
		InputEdge v2_fixed=e.v2;
		VertexPebble v1_fixed=e.v1;
		int skipEdge=v1_fixed.i;
		//the new list of vertex for the new graph
		ArrayList<VertexPebble> newList=new ArrayList<VertexPebble>();
		//go over the old graph's vertex list
		for(int i=0;i<setOfV1.size();i++){
			VertexPebble v1=setOfV1.get(i);
			//the edge list for the new vertex
			ArrayList<InputEdge> updatedEdgeList=new ArrayList<InputEdge>();
			//if it's not the fixed edge vertex
			if(v1.i!=skipEdge){
				//the edge list for the old vertex
				ArrayList<InputEdge> oldEdgeList=v1.edges;
				updatedEdgeList=new ArrayList<InputEdge>();
				for(int j=0;j<oldEdgeList.size();j++){
					InputEdge ve2=oldEdgeList.get(j);
					//if the edge is not connected to fixed edge's vertex2
					if(ve2.label!=(v2_fixed.label)){
							updatedEdgeList.add(new InputEdge(ve2.label,ve2.i,ve2.color));
					}
				
				}
			//if it's the fixed edge
			}else{
				updatedEdgeList=new ArrayList<InputEdge>();
				//only add the fixed edge
				updatedEdgeList.add(new InputEdge(v2_fixed.label,v2_fixed.i,v2_fixed.color));

				//System.out.println(v1.i+" "+v2_fixed.i);
				
			}
			VertexPebble newV1=new VertexPebble(v1.i,updatedEdgeList,v1.color);
			newList.add(newV1);
		}
		BipartiteGraph g=new BipartiteGraph(newList);
		g.a=a;
		g.b=b;
		return g ;
		
	}
	
	/**
	 * drop the edge e from the graph
	 * @param e
	 * @return
	 */
	public BipartiteGraph dropEdge(BipartiteEdge e){
		//System.out.println();
		//System.out.println("dropped edge "+e.v1.i+" to "+e.v2.toString());
		InputEdge v2_dropped=e.v2;
		VertexPebble v1_dropped=e.v1;
		int dropEdge=v1_dropped.i;
		ArrayList<VertexPebble> newList=new ArrayList<VertexPebble>();
		//go over the list of vertices of the old vertex list
		for(int i=0;i<setOfV1.size();i++){
			VertexPebble v1=setOfV1.get(i);
			ArrayList<InputEdge> updatedEdgeList=new ArrayList<InputEdge>();
			ArrayList<InputEdge> oldEdgeList=v1.edges;
			updatedEdgeList=new ArrayList<InputEdge>();
				for(int j=0;j<oldEdgeList.size();j++){
					InputEdge ve2=oldEdgeList.get(j);
					//if it's not the dropped edge
					
					
					//if(v1.i/(a+b)==dropEdge/(a+b)&&ve2.label==v2_dropped.label){
					if(v1.i==dropEdge&&ve2.label==v2_dropped.label){	
					}else{
						updatedEdgeList.add(new InputEdge(ve2.label,ve2.i,ve2.color));
					}
				}
				VertexPebble newV1=new VertexPebble(v1.i,updatedEdgeList,v1.color);
				newList.add(newV1);
			
			}
		BipartiteGraph g=new BipartiteGraph(newList);
		g.a=a;
		g.b=b;
		return g ;
		
		
	}
}

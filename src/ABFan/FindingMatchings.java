package ABFan;
import java.util.ArrayList;
import java.util.HashSet;


public class FindingMatchings {
	
	private static ArrayList<ABFan> matchings;
	private static int a;
	private static int b;
	
	//main method
	public static void findPerfectMatchings(BipartiteGraph g,int input_a,int input_b){
		a=input_a;
		b=input_b;
		ArrayList<BipartiteEdge> matching=findAPerfectMatching(g);
		ABFan fan=new ABFan(matching,a,b);
		fan.printInOrder();
		//fan.printWithPebbles();
		matchings=new ArrayList<ABFan>();
		matchings.add(fan);
		recursivePerfectMatchingFinding(g,matching);
		System.out.println();
		System.out.println("Number of fans is "+matchings.size());
	}
	
	
	//recursive call to find new matchings
	public static void recursivePerfectMatchingFinding(BipartiteGraph g,ArrayList<BipartiteEdge> matching){
		//find a different matching on the graph
		//Edge e is in M' but not in M
		Object[] returns=findADifferentMatching(g,matching);
		
		if(returns[0]!=null){
		
			BipartiteEdge e=(BipartiteEdge) returns[0];
			//System.out.println(e.v2.i);
			ArrayList<BipartiteEdge> newMatching=(ArrayList<BipartiteEdge>)returns[1];
			ABFan fan=new ABFan(newMatching,a,b);
			//fan.printWithPebbles();
			//fan.printInOrder();
			//matchings.get(0).printInOrder();
			if(!alreadyFound(fan)){
				fan.printInOrder();
				matchings.add(fan);
			}
			//a new graph without the edge e
			BipartiteGraph g_withDroppedEdge=g.dropEdge(e);
			recursivePerfectMatchingFinding(g_withDroppedEdge,newMatching);
			// a new graph with a fixed matching edge e
			BipartiteGraph g_withFixedEdge=g.trimmGraphFixEdge(e);
			recursivePerfectMatchingFinding(g_withFixedEdge,matching);
		}
		
	}
	
	/**
	 * find a different matching
	 * @param g
	 * @param matching
	 * @return
	 */
	public static Object[] findADifferentMatching(BipartiteGraph g,ArrayList<BipartiteEdge> matching){
		//create a directed graph
		DiGraph digraph=createDigraph(g,matching);
		//find a directed cycle
		DirectedCycle cycle=new DirectedCycle(digraph);
		//the chosen edge e
		BipartiteEdge newEdge=null;
		BipartiteEdge returnedEdge=null;
		//if there is no directed cycle
		if(!cycle.hasCycle()){
			//printAResult(matching);
			//System.out.println();
			//System.out.println("no cycle!!!");
			//digraph.printAll();
			return new Object[]{null,null,false};
		}else{
			//trace back the cycle 
			ArrayList<BipartiteEdge> updatedMatchingEdges=new ArrayList<BipartiteEdge>();
			VertexPebble v1=null;
			InputEdge v2=null;
			
			//put the v1->v2 edges to the new matching
			//backward iterating (Stack)
			for(int i:cycle.cycle()){
				DiVertex v=digraph.vertice[i];
				
				if(!v.isV1){
					v2=v.v2;
					
					if(v1!=null){
						newEdge=new BipartiteEdge(v1,v2);
						updatedMatchingEdges.add(newEdge);
					}
					
				}else{
					v1=v.v1;
					
				}
				
			}
			//add a new matching
			
			
			boolean found=false;
			Object[] returns=addNewMatching(matching,updatedMatchingEdges);
			
			returnedEdge=(BipartiteEdge)returns[1];
			//System.out.println("About to return edge : "+returnedEdge.v1.i+" to "+returnedEdge.v2.i);
			if(returnedEdge!=null){
				found=true;
			}
			///System.out.println(returnedEdge.v2.i);
			return new Object[]{returnedEdge,(ArrayList<BipartiteEdge> )returns[0],found};
		}
	}
	
	/**
	 * add the new matching to the set
	 * @param matching
	 * @param updatedList
	 */
	public static Object[] addNewMatching(ArrayList<BipartiteEdge> matching,ArrayList<BipartiteEdge> updatedList){
		//printAResult(updatedList);
		ArrayList<BipartiteEdge> newMatchings=new ArrayList<BipartiteEdge>();
		BipartiteEdge returnedEdge=null;
		//copy the old matching over
		for(int i=0;i<matching.size();i++){
			newMatchings.add(matching.get(i));
		}

		for(int i=0;i<updatedList.size();i++){
			BipartiteEdge newEdge=updatedList.get(i);
			//if we find the old matching edge has the same vertex
			//replace it with the updated edge
			for(int j=0;j<newMatchings.size();j++){
				BipartiteEdge oldEdge=newMatchings.get(j);
				if(oldEdge.v1.i==newEdge.v1.i){
					newMatchings.set(j, newEdge);
					returnedEdge=oldEdge;
					//System.out.println(oldEdge.v2.i);
				}
			}
		}
		return new Object[]{newMatchings,returnedEdge};
	}
	
	
	/**
	 * create a directed graph
	 * @param g
	 * @param matching
	 * @return
	 */
	public static DiGraph createDigraph(BipartiteGraph g,ArrayList<BipartiteEdge> matching){
		DiGraph digraph=new DiGraph(g.numOfV1()*2);
		//System.out.println(g.numOfV1());
		
		//input all the vertices
		//and add the matching edges
		for(int i=0;i<g.numOfV1();i++){
			BipartiteEdge e=matching.get(i);
			VertexPebble v1=e.v1;
			InputEdge v2=e.v2;
			DiVertex di_1=new DiVertex(v1.i,g.getVertex(v1.i));
			DiVertex di_2=new DiVertex(v2.label+g.numOfV1(),v2);
			
			digraph.addVertex(di_1);
			digraph.addVertex(di_2);
			//matching edges in the direction of v1->v2
			
			digraph.addEdge(v1.i,v2.label+g.numOfV1());
		
		}
		//add the back edges
		//iterating through the list of vertices
		
		for(int i=0;i<g.numOfV1();i++){
				//System.out.println(i);
				VertexPebble v1=g.getVertex(i);
				for(int j=0;j<v1.edges.size();j++){
					InputEdge v2_iter=v1.edges.get(j);
					//System.out.println("Vertex: "+v1.i+" Edge: "+v2_iter.i);
					//check if it's a back edge
					if(isBackEdge(v1,v2_iter,matching)){
						digraph.addEdge(v2_iter.label+g.numOfV1(), v1.i);
						//System.out.println(v2_iter.label+g.numOfV1());
						//System.out.println("Vertex: "+v1.i+" Edge: "+(v2_iter.label+g.numOfV1()));
					}
				}
		}
		//digraph.printAll();
		return digraph;
		
	}
	
	/**
	 * check if it's a valid edge
	 * @param v1
	 * @param v2
	 * @param matching
	 * @return
	 */
	public static boolean isBackEdge1(VertexPebble v1,InputEdge v2,ArrayList<BipartiteEdge> matching){
		
		//find the matching set has the same vertex2
		for(int i=0;i<matching.size();i++){
			BipartiteEdge e=matching.get(i);
			VertexPebble v1_matched=e.v1;
			InputEdge v2_matched=e.v2;
			//it's a matched edge
			if(v1_matched.i==v1.i&&v2_matched.label==v2.label){
				return false;
			}
			if(v2_matched.label==(v2.label)){
				//System.out.println("The Vertex2 is "+v2.i);
				//System.out.println("Compare "+v1.i+" and "+v1_matched.i);
				//if(v1_matched.color==v1.color){
				//	return false;
				//}
				//if v1s are not from the same vertex
				return true;
			}
			
		}
		
		return true;
		
	}
	
	public static boolean isBackEdge(VertexPebble v1,InputEdge v2,ArrayList<BipartiteEdge> matching){
		//find the matching set has the same vertex2
		//System.out.println();
		for(int i=0;i<matching.size();i++){
			BipartiteEdge e=matching.get(i);
			VertexPebble v1_matched=e.v1;
			InputEdge v2_matched=e.v2;
			//it's a matched edge
			if(v1_matched.i==v1.i&&v2_matched.label==v2.label){
				//System.out.println("Got A Matched Edge "+i);
				return false;
			}
			if(v2_matched.label==(v2.label)){
				//System.out.println("The Vertex2 is "+v2.i);
				//System.out.println("Compare "+v1.i+" and "+v1_matched.i);
				
				//if v1s are not from the same vertex
				if((v1.i/(a+b))!=(v1_matched.i/(a+b))){
					return true;
				}else{
					if(v1.color!=v1_matched.color){
						return true;
					}
					return false;
				}
			}
			
		}
		
		return true;
		
	}
	
	
	/**
	 * find a perfect matching by dfs
	 * @param g
	 * @return
	 */
	public static ArrayList<BipartiteEdge> findAPerfectMatching(BipartiteGraph g){
		if(g.numOfV1()==0){
			return new ArrayList<BipartiteEdge>();
		}
		
		//reach the bottom 
		//successfully matched
		VertexPebble currentVertex=g.getCurrentVertex();
		if(currentVertex.edges.size()==0){
			return null;
		}
		
		for(int i=0;i<currentVertex.edges.size();i++){
			BipartiteEdge e=new BipartiteEdge(currentVertex,currentVertex.edges.get(i));
		
			BipartiteGraph newGraph=g.trimmGraph(e);
			newGraph.a=g.a;
			newGraph.a=g.b;
		    ArrayList<BipartiteEdge> matching=findAPerfectMatching(newGraph);
			
			if(matching==null){
				
			}else{
				matching.add(e);
				return matching;
			}
		}
		
		return null;
		
	}
	

	
	public static void printAResult(ArrayList<BipartiteEdge> currentSet){
		System.out.println();
		for(int j=0;j<currentSet.size();j++){

			BipartiteEdge edge=currentSet.get(j);
			String s=" pebble ";
			int order=(edge.v1.i)%2;
			int count=(edge.v1.i)/2;
			System.out.print(s+order+" on "+"vertex "+count+" to "+edge.v2.toString()+" ; ");
			
		}
	}
	
	public static boolean alreadyFound(ABFan newFan){
		for(int i=0;i<matchings.size();i++){
			if(compareTo(newFan,matchings.get(i))==0){
				return true;
			}
		}
		return false;
	}
	
	
	public static int compareTo(ABFan a, ABFan b) {
		ArrayList[] newMatching=a.orderList;
		ArrayList[] orderList=b.orderList;
		
		for(int i=0;i<orderList.length;i++){
			ArrayList<InputEdge> list=orderList[i];
			ArrayList<InputEdge> newList=newMatching[i];
			if(list==null&&newList==null){
				
			}else if(list==null){
				return -1;
			}else if(newList==null){
				return -1;
			}else if(newList.size()!=list.size()){
				return -1;
			}else{
				
				for(int j=0;j<list.size();j++){
					//System.out.println(list.get(j).label+" : "+newList.get(j).label);
					if(list.get(j).label!=newList.get(j).label){
						return -1;
					}
				}
			}
		}
		return 0;
	}
	

}

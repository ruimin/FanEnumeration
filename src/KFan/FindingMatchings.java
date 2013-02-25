package KFan;
import java.util.ArrayList;


public class FindingMatchings {
	
	private static ArrayList<KFan> matchings;
	private static int k;
	
	
	//main method
	public static void findPerfectMatchings(BipartiteGraph g,int input){
		k=input;
		ArrayList<BipartiteEdge> matching=findAPerfectMatching(g);
		KFan kfan=new KFan(matching,k);
		kfan.printInOrder();
		matchings=new ArrayList<KFan>();
		matchings.add(kfan);
		recursivePerfectMatchingFinding(g,matching);
		System.out.println();
		System.out.println("Number of K-Fan is "+matchings.size());
	}
	
	
	//recursive call to find new matchings
	public static void recursivePerfectMatchingFinding(BipartiteGraph g,ArrayList<BipartiteEdge> matching){
		//find a different matching on the graph
		//Edge e is in M' but not in M
		Object[] returns=findADifferentMatching(g,matching);
		
		if(returns[0]!=null){
			BipartiteEdge e=(BipartiteEdge) returns[0];
			ArrayList<BipartiteEdge> newMatching=(ArrayList<BipartiteEdge>)returns[1];
			
			KFan kfan=new KFan(newMatching,k);
			kfan.printInOrder();
			matchings.add(kfan);
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
			return new Object[]{returnedEdge,(ArrayList<BipartiteEdge> )returns[0],found};
		}
	}
	
	/**
	 * add the new matching to the set
	 * @param matching
	 * @param updatedList
	 */
	public static Object[] addNewMatching(ArrayList<BipartiteEdge> matching,ArrayList<BipartiteEdge> updatedList){
		//System.out.println("Found New Matching Edges size: "+updatedList.size());
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
		for(int i=0;i<matching.size();i++){
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
			
				VertexPebble v1=g.getVertex(i);
				for(int j=0;j<v1.edges.size();j++){
					InputEdge v2_iter=v1.edges.get(j);
					//check if it's a back edge
					if(isBackEdge(v1,v2_iter,matching)){
						digraph.addEdge(v2_iter.label+g.numOfV1(), v1.i);
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
		System.out.println();
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
				if((v1.i/k)!=(v1_matched.i/k)){
					return true;
				}else{
					
					return false;
				}
			}
			
		}
		
		return true;
		
	}
	public static boolean isBackEdge(VertexPebble v1,InputEdge v2,ArrayList<BipartiteEdge> matching){
		//find the matching set has the same vertex2
		BipartiteEdge e=getMatchedEdge(v2,matching);
		if(e.v1.i==v1.i){
			return false;
		}else{
			if((e.v1.i/k)==(v1.i/k)){
				return false;
			}else{
				return true;
			}
		}
		
	}
	
	
	public static BipartiteEdge getMatchedEdge(InputEdge v2,ArrayList<BipartiteEdge> matching){
		for(int i=0;i<matching.size();i++){
			BipartiteEdge e=matching.get(i);
			VertexPebble v1_matched=e.v1;
			InputEdge v2_matched=e.v2;
			//it's a matched edge
			if(v2_matched.label==v2.label){
				//System.out.println("Got A Matched Edge "+i);
				return e;
			}
		}
		return null;
		
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
			newGraph.k=g.k;
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
			int order=(edge.v1.i)%k;
			int count=(edge.v1.i)/k;
			System.out.print(s+order+" on "+"vertex "+count+" to "+edge.v2.toString()+" ; ");
			
		}
	}
	
	
	

}

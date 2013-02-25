package KFan;
import java.util.ArrayList;


public class GenerateKFans {
	
	public static void test(String s){
		String input=s;
		int pos_k=input.indexOf(";");
		int k=Integer.parseInt(input.substring(0,pos_k));
		String rest=input.substring(pos_k+1);
		
		int pos_n=rest.indexOf(";");
		int n=Integer.parseInt(rest.substring(0,pos_n));
		rest=rest.substring(pos_n+2);
		
		int end_edges=rest.indexOf(";");
		String edge=rest.substring(0,end_edges);
		String tieDown=rest.substring(end_edges+2);
		//System.out.println(tieDown);
		ArrayList<String> edges=processEdges(edge);
		
		ArrayList<VertexPebble> listOfPebbles=new ArrayList<VertexPebble>();
		int numOfPebbles=n*k;
		for(int i=0;i<(numOfPebbles);i++){
			ArrayList<InputEdge> list=new ArrayList<InputEdge>();
			listOfPebbles.add(new VertexPebble(i,list));
		}
		for(int i=0;i<edges.size();i++){
			int[] values=processEdge(edges.get(i));
			int firstEdge=values[0];
			int secondEdge=values[1];
			int order=values[2];
			InputEdge inputEdge=new InputEdge(order,edges.get(i));
			for(int j=0;j<k;j++){
				int edge_1=firstEdge*k+j;
				int edge_2=secondEdge*k+j;
				listOfPebbles.get(edge_1).addEdge(inputEdge);
				listOfPebbles.get(edge_2).addEdge(inputEdge);
			}
		}

		ArrayList<String> tieDowns=processEdges(tieDown);
		for(int i=0;i<tieDowns.size();i++){
			//System.out.println(tieDowns.get(i));
			int[] array=processTieDown(tieDowns.get(i));
			int tiedown_edge=array[0];
			int tiedown_pebble=array[1];
			InputEdge inputEdge=new InputEdge(listOfPebbles.size()-tieDowns.size()+i,"Tie Down");
			ArrayList<InputEdge> list=new ArrayList<InputEdge>();
			list.add(inputEdge);
			int index=tiedown_edge*k+tiedown_pebble;
			listOfPebbles.set(index,new VertexPebble(index,list));
			
		}

		System.out.println("The Graph is ");
		for(int i=0;i<listOfPebbles.size();i++){
			VertexPebble v=listOfPebbles.get(i);
			System.out.println();
			System.out.print("Vertex "+v.i+" : ");
			for(int j=0;j<v.edges.size();j++){
				InputEdge e=v.edges.get(j);
				System.out.print("Edge "+e.label+",");
			}
		}
		BipartiteGraph graph=new BipartiteGraph(listOfPebbles);
		graph.k=k;
		//System.out.println(graph.k);
		FindingMatchings.findPerfectMatchings(graph,k);
		
		
	}
	

	private static int[] processTieDown(String tieDown){
		int[] array=new int[2];
		int edge_index=tieDown.indexOf(",");
		String edge=tieDown.substring(0,edge_index);
		
		String pebble=tieDown.substring(edge_index+1);
		
		array[0]=Integer.parseInt(edge);
		array[1]=Integer.parseInt(pebble);
		return array;
		
	}
	
	private static int[] processEdge(String edge){
		int[] array=new int[3];
		int edge_1=edge.indexOf(",");
		String firstEdge=edge.substring(0,edge_1);
		String rest=edge.substring(edge_1+1);
		int edge_2=rest.indexOf(",");
		String secondEdge=rest.substring(0,edge_2);
		String order=rest.substring(edge_2+1);
		array[0]=Integer.parseInt(firstEdge);
		array[1]=Integer.parseInt(secondEdge);
		array[2]=Integer.parseInt(order);
		return array;
	}
	
	private static ArrayList<String> processEdges(String input){
		ArrayList<String> edges=new ArrayList<String>();
		while(input.contains("{")){
			int start=input.indexOf("{");
			int end=input.indexOf("}");
			String edge=input.substring(start+1,end);
			//System.out.println(edge);
			edges.add(edge);
			input=input.substring(end+1);
		}
		return edges;
	}

	
	public static void main(String[] args){
		String s_allblack="6;3;{{0,1,0},{0,1,1},{0,1,2},{0,1,3},{1,2,4},{1,2,5},{1,2,6},{1,2,7},{0,2,8},{0,2,9},{0,2,10},{0,2,11}};{{0,0},{0,1},{0,2},{0,3},{0,4},{0,5}}";
		String s="2;3;{{0,1,0},{1,2,1},{1,2,2},{0,2,3}};{{0,0},{0,1}}";
		String s_k3="3;3;{{0,1,0},{0,1,1},{1,2,2},{1,2,3},{1,2,4},{0,2,5}};{{0,0},{0,1},{0,2}}";
		String s_k4="4;3;{{0,1,0},{0,1,1},{0,1,2},{1,2,3},{1,2,4},{1,2,5},{0,2,6},{0,2,7}};{{0,0},{0,1},{0,2},{0,3}}";
		String s_k4_4="4;3;{{0,1,0},{0,1,1},{0,1,2},{1,2,3},{1,2,4},{1,2,5},{0,2,6},{1,2,7}};{{0,0},{0,1},{0,2},{0,3}}";
		String s_k5="5;3;{{0,1,0},{0,1,1},{0,1,2},{0,1,3},{1,2,4},{1,2,5},{1,2,6},{1,2,7},{1,2,8},{0,2,9}};{{0,0},{0,1},{0,2},{0,3},{0,4}}";
		String s_k5_10="5;3;{{0,1,0},{0,1,1},{0,1,2},{1,2,3},{1,2,4},{1,2,5},{1,2,6},{1,2,7},{0,2,8},{0,2,9}};{{0,0},{0,1},{0,2},{0,3},{0,4}}";
		test(args[0]);
	}
}

package ABFan;
import java.util.ArrayList;


public class GenerateABFans {
	
	public static void test(String s){
		String input=s;
		int pos_a=input.indexOf(";");
		int a=Integer.parseInt(input.substring(0,pos_a));
		String rest=input.substring(pos_a+1);
		int pos_b=rest.indexOf(";");
		int b=Integer.parseInt(rest.substring(0,pos_b));
		rest=rest.substring(pos_b+1);
		int pos_n=rest.indexOf(";");
		int n=Integer.parseInt(rest.substring(0,pos_n));
		rest=rest.substring(pos_n+2);
		
		int end_edges=rest.indexOf(";");
		String edge=rest.substring(0,end_edges);
		String tieDown=rest.substring(end_edges+2);
		//System.out.println(tieDown);
		ArrayList<String> edges=processEdges(edge);
		
		ArrayList<VertexPebble> listOfPebbles=new ArrayList<VertexPebble>();
		int numOfPebbles=n*(a+b);
		for(int i=0;i<(numOfPebbles);i++){
			ArrayList<InputEdge> list=new ArrayList<InputEdge>();
			if((i%(a+b))<a){
				listOfPebbles.add(new VertexPebble(i,list,1));
			}else{
				listOfPebbles.add(new VertexPebble(i,list,0));
			}
		}
		for(int i=0;i<edges.size();i++){
			int[] values=processEdge(edges.get(i));
			int firstEdge=values[0];
			int secondEdge=values[1];
			int order=values[2];
			int color=values[3];
			InputEdge inputEdge=new InputEdge(order,edges.get(i),color);
			for(int j=0;j<(a+b);j++){
				//black input edge
				if(color==0){
					int edge_1=firstEdge*(a+b)+j;
					int edge_2=secondEdge*(a+b)+j;
					listOfPebbles.get(edge_1).addEdge(inputEdge);
					listOfPebbles.get(edge_2).addEdge(inputEdge);
				}else{ //red edge
					if(j<a){//all the aqua pebbles
						int edge_1=firstEdge*(a+b)+j;
						int edge_2=secondEdge*(a+b)+j;
						listOfPebbles.get(edge_1).addEdge(inputEdge);
						listOfPebbles.get(edge_2).addEdge(inputEdge);						
					}
				}
			}
		}

		ArrayList<String> tieDowns=processEdges(tieDown);
		for(int i=0;i<tieDowns.size();i++){
			//System.out.println(tieDowns.get(i));
			int[] array=processTieDown(tieDowns.get(i));
			int tiedown_edge=array[0];
			int tiedown_pebble=array[1];
			InputEdge inputEdge=new InputEdge(listOfPebbles.size()-tieDowns.size()+i,"Tie Down",-1);
			ArrayList<InputEdge> list=new ArrayList<InputEdge>();
			list.add(inputEdge);
			int index=tiedown_edge*(a+b)+tiedown_pebble;
			VertexPebble old=listOfPebbles.get(index);
			listOfPebbles.set(index,new VertexPebble(index,list,old.color));
			
		}

		System.out.println("The Graph is ");
		for(int i=0;i<listOfPebbles.size();i++){
			VertexPebble v=listOfPebbles.get(i);
			System.out.println();
			int vertex=v.i/(a+b);
			int pebble=v.i%(a+b);
			System.out.print("Vertex "+vertex+" Pebble "+pebble+" color "+v.color+" : ");
			for(int j=0;j<v.edges.size();j++){
				InputEdge e=v.edges.get(j);
				System.out.print("Edge "+e.label+" color "+e.color+" , ");
			}
		}
		BipartiteGraph graph=new BipartiteGraph(listOfPebbles);
		graph.a=a;
		graph.b=b;
		//System.out.println(graph.k);
		//ABFan fan=new ABFan(FindingMatchings.findAPerfectMatching(graph), a,b);
		
		FindingMatchings.findPerfectMatchings(graph, a,b);
		//FindingMatchings.trial(graph, a,b);
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
		int[] array=new int[4];
		int edge_1=edge.indexOf(",");
		String firstEdge=edge.substring(0,edge_1);
		String rest=edge.substring(edge_1+1);
		int edge_2=rest.indexOf(",");
		String secondEdge=rest.substring(0,edge_2);
		rest=rest.substring(edge_2+1);
		int order_index=rest.indexOf(",");
		String order=rest.substring(0,order_index);
		String color=rest.substring(order_index+1);
		array[0]=Integer.parseInt(firstEdge);
		array[1]=Integer.parseInt(secondEdge);
		array[2]=Integer.parseInt(order);
		array[3]=Integer.parseInt(color);
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
		String s="3;3;3;{{0,1,0,1},{0,1,1,0},{0,1,2,0},{0,1,3,0},{1,2,4,0},{1,2,5,0},{1,2,6,1},{1,2,7,1},{0,2,8,0},{0,2,9,0},{0,2,10,0},{0,2,11,1}};{{0,0},{0,1},{0,2},{0,3},{0,4},{0,5}}";
		String s_allblack="3;3;3;{{0,1,0,0},{0,1,1,0},{0,1,2,0},{0,1,3,0},{1,2,4,0},{1,2,5,0},{1,2,6,0},{1,2,7,0},{0,2,8,0},{0,2,9,0},{0,2,10,0},{0,2,11,0}};{{0,0},{0,1},{0,2},{0,3},{0,4},{0,5}}";
		String s_k2="1;1;4;{{0,1,0,0},{1,3,1,0},{1,2,2,0},{0,2,3,0},{0,3,4,0},{2,3,5,1}};{{0,0},{0,1}}";
		test(args[0]);
	}
}

package KFan;
import java.util.ArrayList;


public class GraphInput {
	


	public static BipartiteGraph createGraph3(){
		InputEdge v2[]=new InputEdge[8];
		for(int i=0;i<8;i++){
			v2[i]=new InputEdge(i,Integer.toString(i));
			
		}
		ArrayList<InputEdge> list_ex1=new ArrayList<InputEdge>();
		list_ex1.add(v2[6]);
		
	
		VertexPebble v1=new VertexPebble(0,list_ex1);
		ArrayList<InputEdge> list_ex2=new ArrayList<InputEdge>();
		
		list_ex2.add(v2[7]);
		VertexPebble v1_0=new VertexPebble(1,list_ex2);
		ArrayList<InputEdge> list1=new ArrayList<InputEdge>();
		list1.add(v2[0]);
		list1.add(v2[1]);
		list1.add(v2[4]);
		VertexPebble v1_1=new VertexPebble(2,list1);
		ArrayList<InputEdge> list2=new ArrayList<InputEdge>();
		list2.add(v2[0]);
		list2.add(v2[1]);
		list2.add(v2[4]);
		VertexPebble v1_2=new VertexPebble(3,list2);
	
		ArrayList<InputEdge> list3=new ArrayList<InputEdge>();
		list3.add(v2[2]);
		list3.add(v2[3]);
		list3.add(v2[4]);
		VertexPebble v1_3=new VertexPebble(4,list3);
		
		ArrayList<InputEdge> list4=new ArrayList<InputEdge>();
		list4.add(v2[2]);
		list4.add(v2[3]);
		list4.add(v2[4]);
		VertexPebble v1_4=new VertexPebble(5,list4);
		
		ArrayList<InputEdge> list5=new ArrayList<InputEdge>();
		list5.add(v2[2]);
		list5.add(v2[3]);
		list5.add(v2[5]);
		VertexPebble v1_5=new VertexPebble(6,list5);
		
		ArrayList<InputEdge> list6=new ArrayList<InputEdge>();
		list6.add(v2[2]);
		list6.add(v2[3]);
		list6.add(v2[5]);
		VertexPebble v1_6=new VertexPebble(7,list6);
		
		
		ArrayList<VertexPebble> list=new ArrayList<VertexPebble>();
		list.add(v1);
		list.add(v1_0);
		list.add(v1_1);
		list.add(v1_2);
		list.add(v1_3);
		list.add(v1_4);
		list.add(v1_5);
		list.add(v1_6);
		
		return new BipartiteGraph(list);
	}
	

	
	public static BipartiteGraph createGraph2(){
		InputEdge v2[]=new InputEdge[6];
		for(int i=0;i<6;i++){
			v2[i]=new InputEdge(i,Integer.toString(i));
			
		}
		ArrayList<InputEdge> list_ex1=new ArrayList<InputEdge>();
		list_ex1.add(v2[4]);
		VertexPebble v1=new VertexPebble(0,list_ex1);
		ArrayList<InputEdge> list_ex2=new ArrayList<InputEdge>();
		list_ex2.add(v2[5]);
		VertexPebble v1_0=new VertexPebble(1,list_ex2);
		ArrayList<InputEdge> list1=new ArrayList<InputEdge>();
		list1.add(v2[0]);
		list1.add(v2[1]);
		list1.add(v2[2]);
		VertexPebble v1_1=new VertexPebble(2,list1);
		ArrayList<InputEdge> list2=new ArrayList<InputEdge>();
		list2.add(v2[0]);
		list2.add(v2[1]);
		list2.add(v2[2]);
		VertexPebble v1_2=new VertexPebble(3,list2);
		ArrayList<InputEdge> list3=new ArrayList<InputEdge>();
		list3.add(v2[1]);
		list3.add(v2[2]);
		list3.add(v2[3]);
		VertexPebble v1_3=new VertexPebble(4,list3);
		ArrayList<InputEdge> list4=new ArrayList<InputEdge>();
		list4.add(v2[1]);
		list4.add(v2[2]);
		list4.add(v2[3]);
		VertexPebble v1_4=new VertexPebble(5,list4);
		
		ArrayList<VertexPebble> list=new ArrayList<VertexPebble>();
		list.add(v1);
		list.add(v1_0);
		list.add(v1_1);
		list.add(v1_2);
		list.add(v1_3);
		list.add(v1_4);
		
		BipartiteGraph or_g=new BipartiteGraph(list);
		return or_g;
		
	}
	public static BipartiteGraph createGraph1(){
		InputEdge v2[]=new InputEdge[8];
		for(int i=0;i<8;i++){
			v2[i]=new InputEdge(i,Integer.toString(i));
			
		}
		ArrayList<InputEdge> list_ex1=new ArrayList<InputEdge>();
		list_ex1.add(v2[6]);
		VertexPebble v1_3=new VertexPebble(4,list_ex1);
		ArrayList<InputEdge> list3=new ArrayList<InputEdge>();
		list3.add(v2[0]);
		list3.add(v2[1]);
		list3.add(v2[5]);
		VertexPebble v1=new VertexPebble(0,list3);
		ArrayList<InputEdge> list_ex2=new ArrayList<InputEdge>();
		
		list_ex2.add(v2[7]);
		VertexPebble v1_0=new VertexPebble(1,list_ex2);
		ArrayList<InputEdge> list1=new ArrayList<InputEdge>();
		list1.add(v2[0]);
		list1.add(v2[1]);
		list1.add(v2[4]);
		VertexPebble v1_1=new VertexPebble(2,list1);
		ArrayList<InputEdge> list2=new ArrayList<InputEdge>();
		list2.add(v2[0]);
		list2.add(v2[1]);
		list2.add(v2[4]);
		VertexPebble v1_2=new VertexPebble(3,list2);
		
		ArrayList<InputEdge> list4=new ArrayList<InputEdge>();
		list4.add(v2[2]);
		list4.add(v2[3]);
		list4.add(v2[4]);
		VertexPebble v1_4=new VertexPebble(5,list4);
		
		ArrayList<InputEdge> list5=new ArrayList<InputEdge>();
		list5.add(v2[2]);
		list5.add(v2[3]);
		list5.add(v2[5]);
		VertexPebble v1_5=new VertexPebble(6,list5);
		
		ArrayList<InputEdge> list6=new ArrayList<InputEdge>();
		list6.add(v2[2]);
		list6.add(v2[3]);
		list6.add(v2[5]);
		VertexPebble v1_6=new VertexPebble(7,list6);
		
		
		ArrayList<VertexPebble> list=new ArrayList<VertexPebble>();
		list.add(v1);
		list.add(v1_0);
		list.add(v1_1);
		list.add(v1_2);
		list.add(v1_3);
		list.add(v1_4);
		list.add(v1_5);
		list.add(v1_6);
		
		return new BipartiteGraph(list);
	}

	/**
	public static void main(String[] args){
		Vertex2[] v2=new Vertex2[8];
		for(int i=0;i<v2.length;i++){
			v2[i]=new Vertex2(i);
			
		}
		int[] set0=new int[]{0,1,4};
		int[] set1=new int[]{2,3,4};
		int[] set2=new int[]{2,3,5};
		int[] set3=new int[]{0,1,5};
		int[] tieDown=new int[]{0,1};
		ArrayList<int[]> inputs=new ArrayList<int[]> ();
		inputs.add(set0);
		inputs.add(set1);
		inputs.add(set2);
		inputs.add(set3);
		int counter=1;
		ArrayList<Vertex> finalList=new ArrayList<Vertex>();
		for(int i=0;i<v2.length;i++){
			if(i!=tieDown[0]||i!=tieDown[1]){
				ArrayList<Vertex2> list=new ArrayList<Vertex2>();
				int[] input=inputs.get(i/2);
				for(int j=0;j<input.length;j++){
					list.add(v2[input[2]]);
				}
				Vertex v=new Vertex(i,list);
				finalList.add(v);
			}else{
				ArrayList<Vertex2> list=new ArrayList<Vertex2>();
				list.add(v2[v2.length-counter]);
				counter++;
				Vertex v=new Vertex(i,list);
				finalList.add(v);
			}
		}
		
		
		
		
		
		Graph or_g=new Graph(finalList);
		findPerfectMatchings(or_g);
		printResult(matchings);
		
	}

	**/

}

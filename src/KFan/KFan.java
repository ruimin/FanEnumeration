package KFan;
import java.util.ArrayList;


public class KFan {
	public static int k;
	public static ArrayList<BipartiteEdge> pebbleList;
	public static ArrayList[] orderList;
	
	public KFan(ArrayList<BipartiteEdge> list,int input){
		pebbleList=list;
		this.k=input;
		int n=list.size()/k;
		orderList=new ArrayList[n];
		for(int i=0;i<list.size();i++){
			BipartiteEdge edge=list.get(i);
			VertexPebble v=edge.v1;
			InputEdge inputEdge=edge.v2;
			int vertex=(v.i)/k;
			if(orderList[vertex]==null){
				ArrayList<InputEdge> newList=new ArrayList<InputEdge>();
				newList.add(inputEdge);
				orderList[vertex]=newList;
			}else{
				ArrayList<InputEdge> newList=orderList[vertex];
				int j;
				for(j=0;j<newList.size();j++){
					
					if(newList.get(j).label>=inputEdge.label){
						
						break;
					}
					
				}
				ArrayList<InputEdge> updateList=new ArrayList<InputEdge>();
				for(int k=0;k<j;k++){
					updateList.add(newList.get(k));
				}
				updateList.add(inputEdge);
				for(int k=j+1;k<newList.size()+1;k++){
					updateList.add(newList.get(k-1));
				}
				orderList[vertex]=updateList;
				
			}
		}
	}
	
	public static void printWithPebbles1(){
		System.out.println();
		for(int j=0;j<pebbleList.size();j++){

			BipartiteEdge edge=pebbleList.get(j);
			String s=" pebble ";
			int order=(edge.v1.i)%k;
			int count=(edge.v1.i)/k;
			System.out.print(s+order+" on "+"vertex "+count+" on edge "+edge.v2.toString()+" ; ");
			
		}
		
	}
	
	public static void printWithPebbles(){
		System.out.println();
		for(int j=pebbleList.size()-1;j>=0;j--){

			BipartiteEdge edge=pebbleList.get(j);
			String s=" pebble ";
			int order=(edge.v1.i)%k;
			int count=(edge.v1.i)/k;
			if(order==0){
				System.out.print(count+ ": ");
			}
			System.out.print("("+edge.v2.toString()+"); ");
			
		}
		
	}
	
	public static void printInOrder(){
		System.out.println();

		System.out.println("NEW KFAN");
		for(int i=0;i<orderList.length;i++){
			System.out.println();
			System.out.print("Vertex "+i+" put pebbles on : ");
			ArrayList<InputEdge> list=orderList[i];
			if(list!=null){
				for(int j=0;j<list.size();j++){
					System.out.print("Edge-{"+list.get(j).toString()+"}, ");
				}
			}
		}
		
	}

}

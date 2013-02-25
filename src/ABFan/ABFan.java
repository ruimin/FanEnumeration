package ABFan;
import java.util.ArrayList;


public class ABFan {
	public static int a;
	public static int b;
	public ArrayList<BipartiteEdge> pebbleList;
	public ArrayList[] orderList;
	
	public ABFan(ArrayList<BipartiteEdge> list,int input_a,int input_b){
		pebbleList=list;
		a=input_a;
		b=input_b;
		int n=list.size()/(a+b);
		orderList=new ArrayList[n];
		for(int i=0;i<list.size();i++){
			BipartiteEdge edge=list.get(i);
			VertexPebble v=edge.v1;
			InputEdge inputEdge=edge.v2;
			int vertex=(v.i)/(a+b);

			//System.out.println(vertex+" : "+inputEdge.label);
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
	
	public void printWithPebbles(){
		System.out.println();
		for(int j=pebbleList.size()-1;j>=0;j--){

			BipartiteEdge edge=pebbleList.get(j);
			String s=" pebble ";
			int order=(edge.v1.i)%(a+b);
			int count=(edge.v1.i)/(a+b);
			if(order==0){
				System.out.print(count+ ": ");
			}
			System.out.print("("+edge.v2.toString()+"); ");
			
		}
	}
	
	public void printInOrder(){
		System.out.println();

		System.out.println("NEW KFAN");
		for(int i=0;i<orderList.length;i++){
			System.out.println();
			System.out.println("Vertex "+i+" put pebbles on : ");
			ArrayList<InputEdge> list=orderList[i];
			if(list!=null){
				for(int j=0;j<list.size();j++){
					System.out.print("Edge-{"+list.get(j).label+"}, ");
				}
			}
		}
		
	}

	
	

}

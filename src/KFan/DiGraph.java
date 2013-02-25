package KFan;
import java.util.ArrayList;



public class DiGraph {
	 int V;
	 private int E;
	 DiVertex[] vertice;
	 
	 public DiGraph(int V_size){
		 V=V_size;
		 vertice=new DiVertex[V];
		 
	 }
	 
	 public void addVertex(DiVertex v){
		 vertice[v.i]=v;
	 }
	 
	 public void addEdge(int v1,int v2){
		 vertice[v1].addEdge(vertice[v2]);
	 }
	 
	 public ArrayList<DiVertex> adj(int v){
		 return vertice[v].list;
	 }
	 
	 public void printAll(){
		 for(int i=0;i<vertice.length;i++){
			 System.out.println();
			 if(i>=V){
				 System.out.print("Edge "+vertice[i].v2.toString()+" adj to :");
			 }else{
				 System.out.print("Vertex "+i+" adj to :");
			 }
			 ArrayList<DiVertex> adj=vertice[i].list;
			 for(int j=0;j<adj.size();j++){
				 DiVertex v=adj.get(j);
				 if(v.i>=V){
					 System.out.print(v.v2.toString()+" ");
				 }else{
					 System.out.print(v.i+" ");
				 }
				 
				 
			 }
		 }
	 }

}

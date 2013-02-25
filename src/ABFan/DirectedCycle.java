package ABFan;
import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;        // marked[v] = has vertex v been marked?
    private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
    private boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    public DirectedCycle(DiGraph G) {
        marked  = new boolean[G.V];
        onStack = new boolean[G.V];
        edgeTo  = new int[G.V];
        for (int v = 0; v < G.V; v++)
            if (!marked[v]) dfs(G, v);

        // check that digraph has a cycle
        assert check(G);
    }


    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(DiGraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DiVertex w : G.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) return;

            //found new vertex, so recur
            else if (!marked[w.i]) {
                edgeTo[w.i] = v;
                dfs(G, w.i);
            }

            // trace back directed cycle
            else if (onStack[w.i]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w.i; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w.i);
                cycle.push(v);
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle()        { return cycle != null;   }
    public Stack<Integer> cycle() { return cycle;           }


    // certify that digraph is either acyclic or has a directed cycle
    private boolean check(DiGraph G) {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }


        return true;
    }

  

}



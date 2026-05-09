import java.util.*;

public class Experiment {

    private Map<Integer, long[]> results;

    public Experiment() {
        results = new LinkedHashMap<>();
    }


    public void runTraversals(Graph g) {
        int size = g.getVertexCount();


        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();
        long bfsTime = bfsEnd - bfsStart;


        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();
        long dfsTime = dfsEnd - dfsStart;

        results.put(size, new long[]{bfsTime, dfsTime});
    }


    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};

        for (int size : sizes) {
            System.out.println("\n=== Graph with " + size + " vertices ===");
            Graph g = buildRandomGraph(size);

            if (size == 10) {
                g.printGraph();
            }

            runTraversals(g);
        }
    }


    private Graph buildRandomGraph(int size) {
        Graph g = new Graph();
        Random rand = new Random(42);


        for (int i = 0; i < size; i++) {
            g.addVertex(new Vertex(i));
        }


        for (int i = 0; i < size; i++) {
            int edgeCount = 2 + rand.nextInt(3);
            for (int j = 0; j < edgeCount; j++) {
                int to = rand.nextInt(size);
                if (to != i) {
                    g.addEdge(i, to);
                }
            }
        }

        return g;
    }

    public void printResults() {
        System.out.println("\n========================================");
        System.out.println("       Performance Results Table");
        System.out.println("========================================");
        System.out.printf("%-15s %-20s %-20s%n", "Graph Size", "BFS Time (ns)", "DFS Time (ns)");
        System.out.println("----------------------------------------");

        for (Map.Entry<Integer, long[]> entry : results.entrySet()) {
            int size = entry.getKey();
            long bfsTime = entry.getValue()[0];
            long dfsTime = entry.getValue()[1];
            System.out.printf("%-15d %-20d %-20d%n", size, bfsTime, dfsTime);
        }

        System.out.println("========================================");
    }
}

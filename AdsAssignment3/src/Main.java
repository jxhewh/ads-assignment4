public class Main {
    public static void main(String[] args) {
        System.out.println("=== Graph Traversal and Representation System ===\n");

        System.out.println(">>> Small Graph (10 vertices) - Manual Setup");
        Graph smallGraph = new Graph();
        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }
        int[][] edges = {
                {0,1},{0,2},{1,3},{1,4},{2,5},{3,6},{4,6},{5,7},{6,8},{7,9},{8,9}
        };
        for (int[] e : edges) {
            smallGraph.addEdge(e[0], e[1]);
        }

        smallGraph.printGraph();

        System.out.println("\n-- Traversals on Small Graph --");

        long start = System.nanoTime();
        smallGraph.bfs(0);
        long end = System.nanoTime();
        System.out.println("  BFS Time: " + (end - start) + " ns");

        start = System.nanoTime();
        smallGraph.dfs(0);
        end = System.nanoTime();
        System.out.println("  DFS Time: " + (end - start) + " ns");


        System.out.println("\n>>> Running Multi-Size Experiments (10, 30, 100 vertices)...");
        Experiment experiment = new Experiment();
        experiment.runMultipleTests();

        experiment.printResults();

        System.out.println("\nDone.");
    }
}

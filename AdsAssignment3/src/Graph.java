import java.util.*;
public class Graph {

    private Map<Integer, Vertex> vertices;

    private Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {

        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            throw new IllegalArgumentException("One or both vertices not found: " + from + ", " + to);
        }
        adjacencyList.get(from).add(to);
    }


    public void printGraph() {
        System.out.println("Graph Adjacency List:");
        for (int id : adjacencyList.keySet()) {
            System.out.print("  " + id + " -> ");
            System.out.println(adjacencyList.get(id));
        }
    }

    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("BFS from " + start + ": ");

        while (!queue.isEmpty()) {

            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }


    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS from " + start + ": ");
        dfsHelper(start, visited);
        System.out.println();
    }


    private void dfsHelper(int current, Set<Integer> visited) {

        visited.add(current);
        System.out.print(current + " ");

        for (int neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public Map<Integer, List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}

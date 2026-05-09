# Assignment 4: Graph Traversal and Representation System

## A. Project Overview

This project implements a Graph Traversal and Representation System in Java. A graph is a data structure consisting of vertices (nodes) and edges (connections between nodes). Graphs are widely used to model real-world problems such as social networks, maps and computer networks.

### Key Concepts

- Vertex is a single node in the graph, identified by a unique integer ID.
- Edge is a directed connection from one vertex (source) to another (destination).
- Adjacency List is a way to represent the graph where each vertex stores a list of its neighbors.

### Traversal Algorithms

- BFS (Breadth-First Search) explores the graph level by level, starting from a source vertex.
- DFS (Depth-First Search) explores the graph as deep as possible along each branch before backtracking.

---

## B. Class Descriptions

### `Vertex`
Represents a single node in the graph.
- Field: `id` - unique integer identifier
- Methods: constructor, `getId()`, `toString()`

### `Edge`
Represents a directed connection between two vertices.
- Fields: `source`, `destination` - both are `Vertex` objects
- Methods: constructor, `getSource()`, `getDestination()`, `toString()`

### `Graph`
The core class that stores the graph structure using an adjacency list
- Internally uses `HashMap<Integer, List<Integer>>` — each vertex ID maps to a list of neighbor IDs.
- Methods: `addVertex(Vertex v)`, `addEdge(int from, int to)`, `printGraph()`, `bfs(int start)`, `dfs(int start)`

#### Why Adjacency List?
An adjacency list is memory-efficient for sparse graphs (graphs with fewer edges). It stores only existing connections, unlike an adjacency matrix which always uses O(V^2) space.

### `Experiment`
Handles automated testing and performance measurement.
- Methods: `runTraversals(Graph g)`, `runMultipleTests()`, `printResults()`
- Builds random graphs of sizes 10, 30, and 100 vertices and records BFS/DFS execution times.

### `Main`
Entry point of the program. Creates a small manual graph (10 vertices) to demonstrate traversal order, then runs full performance experiments via `Experiment`.

---

## C. Algorithm Descriptions

### BFS (Breadth-First Search)

Step by step:
1. Add the start vertex to a queue and mark it as visited.
2. While the queue is not empty:
   - Dequeue the front vertex.
   - Print or process it.
   - Enqueue all unvisited neighbors and mark them visited.

Use cases:
- Finding the shortest path in an unweighted graph.
- Level-order traversal.
- Web crawlers.

Time Complexity: O(V + E) every vertex and every edge is visited once.

---

### DFS (Depth-First Search)

Step  by step:
1. Start at the source vertex, mark it as visited, and print it.
2. Recursively visit each unvisited neighbor.
3. Backtrack when no unvisited neighbors remain.

Use cases:
- Detecting cycles in a graph.
- Topological sorting.
- Solving mazes and puzzles.

Time Complexity: O(V + E) every vertex and every edge is visited once.

---

## D. Experimental Results

Graphs were generated with a fixed random seed for reproducibility. Each vertex had 2–4 random outgoing edges.

### Execution Time Comparison

| Graph Size (Vertices) | BFS Time (ns) | DFS Time (ns) |
|-----------------------|---------------|---------------|
| 10                    | 313,500       | 625,300       |
| 30                    | 999,300       | 842,200       |
| 100                   | 2,555,600     | 2,057,000     |

### Observations

- Both BFS and DFS scale linearly with graph size, consistent with O(V + E) complexity.
- DFS was slightly faster than BFS across all graph sizes in these experiments, possibly because BFS has extra overhead from managing the queue data structure.
- As graph size increases from 10 to 30 and then to 100 vertices, execution time grows proportionally, confirming the expected linear complexity.
- The traversal order differs significantly: BFS visits vertices level by level (closer to start first), while DFS dives deep into one branch before exploring others.
- For the small graph (10 vertices), BFS order was `0 1 2 3 4 5 6 7 8 9` and DFS order was `0 1 3 6 8 9 4 2 5 7`, clearly showing the difference in exploration strategy.

---

## E. Screenshots

Screenshots are located in `docs/screenshots/`

- `![img.png](img.png)` -output of `printGraph()` for the small graph
- `![img_1.png](img_1.png)` -BFS traversal order on small graph
- `![img_2.png](img_2.png)` - DFS traversal order on small graph
- `![img_3.png](img_3.png)` - final performance comparison table

---

## F. Reflection

Working on this assignment gave me a much deeper understanding of how graphs are represented and traversed in memory. Implementing the adjacency list made it clear why this structure is preferred over adjacency matrices for sparse graphs, it only stores what actually exists, making it both memory-efficient and fast to iterate. Seeing the BFS and DFS results side by side also made the conceptual difference between the two very concrete: BFS explores broadly and finds the "closest" vertices first, while DFS commits to a path and only backtracks when forced.

The biggest challenge was making sure visited nodes were properly tracked to avoid infinite loops in graphs with cycles. Another interesting challenge was understanding why DFS was slightly slower in practice despite having the same theoretical complexity the overhead of recursive calls adds up, especially on larger graphs. Overall, this project made graph algorithms feel much more tangible and applicable to real-world problems like network routing or social graph analysis.
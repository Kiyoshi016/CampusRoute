package campusroute;
import java.util.*;

public class WeightedGraph {
    class Edge {
        String destination;
        int weight;    
        
        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
    }
}
    private HashMap<String, List<Edge>> adjList = new HashMap<>();
    public void addEdge(String source, String destination, int weight) {
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(destination, weight));
        adjList.computeIfAbsent(destination, k -> new ArrayList<>()).add(new Edge(source, weight));
}

    public boolean hasVertex(String v) {
        return adjList.containsKey(v);
}

    public void displayGraph() {
        System.out.println("Graph Structure:");
        for (String v : new TreeSet<>(adjList.keySet())) {
            System.out.print(v + " -> ");
            for (Edge e : adjList.get(v)) {
                System.out.print("(" + e.destination + ", " + e.weight + ") ");
            }
        System.out.println();
    }
}
    public void dijkstra(String start, String target) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        Set<String> done = new HashSet<>();
        List<String> visitedOrder = new ArrayList<>();

        for (String v : adjList.keySet()) dist.put(v, Integer.MAX_VALUE);
        dist.put(start, 0);

        PriorityQueue<Map.Entry<String, Integer>> pq =
            new PriorityQueue<>(Map.Entry.comparingByValue());
        pq.add(new AbstractMap.SimpleEntry<>(start, 0));

        while (!pq.isEmpty()) {
            String u = pq.poll().getKey();
            if (done.contains(u)) continue;
            done.add(u);
            visitedOrder.add(u);
            if (u.equals(target)) break;

            for (Edge e : adjList.get(u)) {
                int newDist = dist.get(u) + e.weight;
                if (newDist < dist.get(e.destination)) {
                    dist.put(e.destination, newDist);
                    prev.put(e.destination, u);
                    pq.add(new AbstractMap.SimpleEntry<>(e.destination, newDist));
                }
            }
}

        System.out.println("Visited Vertices: " + String.join(" -> ", visitedOrder));

        if (dist.get(target) == Integer.MAX_VALUE) {
            System.out.println("No path found.");
            return;
}

        LinkedList<String> path = new LinkedList<>();
        for (String at = target; at != null; at = prev.get(at)) path.addFirst(at);

        System.out.println("Shortest Path: " + String.join(" -> ", path));
        System.out.println("Total Distance: " + dist.get(target) + " meters");
    }
}
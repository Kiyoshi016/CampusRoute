package campusroute;
import java.util.Scanner;

public class CampusRouteOptimizer {
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 8);
        graph.addEdge("C", "E", 10);
        graph.addEdge("D", "E", 2);
        graph.addEdge("B", "E", 7);

        System.out.println("Locations: A-Main Gate, B-Registrar, C-Library, D-Laboratory, E-Faculty Room\n");
        graph.displayGraph();
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter source vertex: ");
        String source = sc.nextLine().trim().toUpperCase();
        System.out.print("Enter destination vertex: ");
        String dest = sc.nextLine().trim().toUpperCase();

        if (!graph.hasVertex(source) || !graph.hasVertex(dest)) {
            System.out.println("Invalid vertex. Use A, B, C, D, or E.");
        } else {
            graph.dijkstra(source, dest);
        }
        sc.close();
    }
}
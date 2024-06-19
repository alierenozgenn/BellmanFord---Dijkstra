import java.util.*;

class BellmanFord {
    static class Edge {
        char source;
        char target;
        int weight;

        Edge(char source, char target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, char start, Set<Character> vertices) {
        Map<Character, Integer> distances = new HashMap<>();
        for (char vertex : vertices) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        for (int i = 0; i < vertices.size() - 1; i++) {
            for (Edge edge : edges) {
                if (distances.get(edge.source) != Integer.MAX_VALUE && distances.get(edge.source) + edge.weight < distances.get(edge.target)) {
                    distances.put(edge.target, distances.get(edge.source) + edge.weight);
                }
            }
        }

        for (Edge edge : edges) {
            if (distances.get(edge.source) != Integer.MAX_VALUE && distances.get(edge.source) + edge.weight < distances.get(edge.target)) {
                System.out.println("Graph contains negative weight cycle!");
                return;
            }
        }

        for (Map.Entry<Character, Integer> distance : distances.entrySet()) {
            System.out.println("Distance from " + start + " to " + distance.getKey() + " is " + distance.getValue());
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge('A', 'B', 1));
        edges.add(new Edge('A', 'C', 4));
        edges.add(new Edge('B', 'C', 2));
        edges.add(new Edge('B', 'D', 2));
        edges.add(new Edge('C', 'D', 1));
        edges.add(new Edge('D', 'B', -6));

        Set<Character> vertices = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D'));

        char startNode = 'A';
        bellmanFord(edges, startNode, vertices);
    }
}

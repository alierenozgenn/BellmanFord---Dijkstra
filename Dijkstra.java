import java.util.*;

class Dijkstra {
    static class Edge {
        char target;
        int weight;

        Edge(char target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void dijkstra(Map<Character, List<Edge>> graph, char start) {
        Map<Character, Integer> distances = new HashMap<>();
        for (char node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<Map.Entry<Character, Integer>> priorityQueue = new PriorityQueue<>(Map.Entry.comparingByValue());
        priorityQueue.add(new AbstractMap.SimpleEntry<>(start, 0));

        while (!priorityQueue.isEmpty()) {
            Map.Entry<Character, Integer> entry = priorityQueue.poll();
            char currentNode = entry.getKey();
            int currentDistance = entry.getValue();

            if (currentDistance > distances.get(currentNode)) {
                continue;
            }

            for (Edge edge : graph.get(currentNode)) {
                char neighbor = edge.target;
                int weight = edge.weight;
                int distance = currentDistance + weight;

                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    priorityQueue.add(new AbstractMap.SimpleEntry<>(neighbor, distance));
                }
            }
        }

        for (Map.Entry<Character, Integer> distance : distances.entrySet()) {
            System.out.println("Distance from " + start + " to " + distance.getKey() + " is " + distance.getValue());
        }
    }

    public static void main(String[] args) {
        Map<Character, List<Edge>> graph = new HashMap<>();
        graph.put('A', Arrays.asList(new Edge('B', 1), new Edge('C', 4)));
        graph.put('B', Arrays.asList(new Edge('A', 1), new Edge('C', 2), new Edge('D', 5)));
        graph.put('C', Arrays.asList(new Edge('A', 4), new Edge('B', 2), new Edge('D', 1)));
        graph.put('D', Arrays.asList(new Edge('B', 5), new Edge('C', 1)));

        char startNode = 'A';
        dijkstra(graph, startNode);
    }
}

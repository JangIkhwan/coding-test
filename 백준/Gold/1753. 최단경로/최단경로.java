import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int INF = 5000000;

    public static void main(String[] args) throws IOException {
        int V, E, K;
        List<Node>[] graph;
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        line = br.readLine();
        K = Integer.parseInt(line);
        graph = new List[V + 1];
        for(int i = 1; i <= V; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i <= E; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(weight, end));
        }
        int[] distance = new int[V + 1];
        dijkstra(K, graph, distance);
        for(int i = 1; i <= V; i++){
            if(distance[i] != INF){
                bw.write(Integer.toString(distance[i]) + "\n");
            }
            else{
                bw.write("INF\n");
            }
        }
        bw.flush();
    }
    private static void dijkstra(int start, List<Node>[] graph, int[] distance){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for(int v = 1; v <= graph.length - 1; v++){
            distance[v] = INF;
        }
        distance[start] = 0;
        pq.offer(new Node(0, start));
        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(distance[current.dest] < current.distance){
                continue;
            }

            distance[current.dest] = current.distance;

            for(Node next : graph[current.dest]){
                int nextDistance = current.distance + next.distance;
                if(distance[next.dest] > nextDistance) pq.offer(new Node(nextDistance, next.dest));
            }
        }
    }

    static class Node implements Comparable<Node>{
        int distance;
        int dest;

        public Node(int distance, int dest){
            this.distance = distance;
            this.dest = dest;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}

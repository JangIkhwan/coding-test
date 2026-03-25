import java.io.*;
import java.util.*;

public class Main {
    private static List<Node>[] graph;

    public static void main(String[] args) throws IOException{
        int N, E, v1, v2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        line = br.readLine();
        st = new StringTokenizer(line);
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 다익스트라
        int INF = 20000000;
        int[][] dists = new int[3][N + 1];
        for(int i = 0; i < 3; i++){
            Arrays.fill(dists[i], INF);
        }
        dijkstra(1, dists[0]);
        dijkstra(v1, dists[1]);
        dijkstra(v2, dists[2]);

        // 두 경우 중에서 최단 경로 구하기
        int minDistance = Math.min(dists[0][v1] + dists[1][v2] + dists[2][N], dists[0][v2] + dists[2][v1] + dists[1][N]);
        if(minDistance >= INF){
            System.out.println(-1);
            return;
        }
        System.out.println(minDistance);
    }

    private static void dijkstra(int start, int[] dists){
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.offer(new Node(start, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(dists[cur.vertex] < cur.dist){
                continue;
            }
            dists[cur.vertex] = cur.dist;
            for(Node next : graph[cur.vertex]){
                int nextDistance = dists[cur.vertex] + next.dist;
                if(nextDistance < dists[next.vertex]){
                    pq.offer(new Node(next.vertex, nextDistance));
                }
            }
        }
    }

    static class Node{
        int vertex;
        int dist;

        public Node(int vertex, int dist){
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}
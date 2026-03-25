import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, A, B;
    private static int[] dist;
    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new int[]{e, w});
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        // a를 출발점으로 다익스트라 수행
        dijkstra(A);

        // b까지 가는 최단경로 출력
        System.out.println(dist[B]);
    }

    private static void dijkstra(int start){
        final int INF = 100000000;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new int[] {start, 0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(dist[cur[0]] < cur[1]) continue;
            for(int[] next : graph[cur[0]]){
                int nextDist = cur[1] + next[1];
                if(nextDist < dist[next[0]]){
                    dist[next[0]] = nextDist;
                    pq.offer(new int[]{next[0], nextDist});
                }
            }
        }
    }
}
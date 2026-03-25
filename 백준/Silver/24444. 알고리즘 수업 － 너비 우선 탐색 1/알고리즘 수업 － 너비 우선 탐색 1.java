import java.io.*;
import java.util.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/2563

    2025/01/03 12:06 ~
     */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, R;
    private static List<Integer>[] graph;
    private static int[] visited;

    private static int visitCounter = 1;
    public static void main(String[] args) throws IOException {
        init();
        bfs(R);
        for(int i = 1; i <= N; i++){
            bw.write(visited[i] + "\n");
        }
        bw.flush();
    }

    private static void bfs(int start){
        Queue<Integer> qu = new LinkedList<>();
        qu.add(start);
        while (!qu.isEmpty()) {
            int current = qu.poll();
            if (visited[current] > 0) continue;
            visited[current] = visitCounter++;
            for (int next : graph[current]) {
                qu.add(next);
            }
        }
    }

    private static void init() throws IOException {
        // todo 입력을 받고, graph를 오름차순 정렬, visit 배열 생성
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i <= M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        for(int i = 1; i <= N; i++){
            Collections.sort(graph[i]);
        }
        visited = new int[N + 1];
    }
}
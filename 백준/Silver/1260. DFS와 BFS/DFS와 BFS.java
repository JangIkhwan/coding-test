import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N, M, V;
        List<Integer>[] graph;
        boolean [] visited_dfs;
        boolean [] visited_bfs;
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }
        for(int i = 1; i <= N; i++){
            Collections.sort(graph[i]);
        }
        visited_dfs = new boolean[N + 1];
        visited_bfs = new boolean[N + 1];
        dfs(graph, V, visited_dfs);
        bw.write("\n");
        bfs(graph, V, visited_bfs);
        bw.flush();
    }

    private static void dfs(List<Integer>[] graph, int current, boolean[] visited) throws IOException {
        if(visited[current]){
            return;
        }
        visited[current] = true;
        bw.write(Integer.toString(current) + " ");
        for(int next : graph[current]){
            dfs(graph, next, visited);
        }
    }

    private static void bfs(List<Integer>[] graph, int start, boolean[] visited) throws IOException {
        Queue<Integer> qu = new LinkedList<>();
        qu.add(start);
        while(!qu.isEmpty()){
            int front = qu.poll();
            if(visited[front]) continue;
            visited[front] = true;
            bw.write(Integer.toString(front) + " ");
            for(int next : graph[front]){
                qu.add(next);
            }
        }
    }
}
import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        init();
        dfs(1, 0);
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++){
            sb.append(Integer.toString(parent[i]));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i <= N - 1; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }
        visited = new boolean[N + 1];
        parent = new int[N + 1];
    }

    private static void dfs(int start, int prev){
        if(visited[start]){
            return;
        }
        visited[start] = true;
        parent[start] = prev;
        for(int next : graph[start]){
            dfs(next, start);
        }
    }
}
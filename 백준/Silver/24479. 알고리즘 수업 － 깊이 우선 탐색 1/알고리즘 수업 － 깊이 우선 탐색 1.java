import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, R;
    private static List<Integer>[] graph;
    private static int [] visited;

    private static int visitNumber = 1;
    public static void main(String[] args) throws IOException {
        init();
        dfs(R);
        for(int i = 1; i <= N; i++){
            bw.write(visited[i] + "\n");
        }
        bw.flush();
    }

    public static void init() throws IOException {
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        for(int i = 1; i <= N; i++){
            Collections.sort(graph[i]);
        }
        visited = new int[N + 1];
    }

    public static void dfs(int cur) {
        visited[cur] = visitNumber++;
        for(int next : graph[cur]){
            if(visited[next] == 0){
                dfs(next);
            }
        }
    }
}
import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static boolean isBipartite;
    private static int K, V, E;
    private static List<Integer>[] graph;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        while(K-->0){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new List[V + 1];
            for(int i = 1; i <= V; i++){
                graph[i] = new ArrayList<>();
            }
            visited = new int[V + 1];
            for(int j = 0; j < E; j++){
                line = br.readLine();
                st = new StringTokenizer(line);
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                graph[e].add(s);
            }
            isBipartite = true;
            for(int i = 1; i <= V; i++) {
                if(visited[i] == 0)
                    dfs(i, 1);
            }
            if(isBipartite){
                bw.write("YES\n");
            }
            else{
                bw.write("NO\n");
            }
        }
        bw.flush();
    }

    private static void dfs(int current, int set){
        if(visited[current] != 0){
            if(visited[current] != set) {
                isBipartite = false;
            }
            return;
        }
        visited[current] = set;
        for(int next: graph[current]){
            dfs(next, -set);
        }
    }
}
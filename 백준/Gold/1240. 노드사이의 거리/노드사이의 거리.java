import java.io.*;
import java.util.*;

public class Main {
 
    private static List<Node>[] tree;
    private static int[] visited;
    private static int answer;

    public static void main(String[] args) throws IOException{
        int N, M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            tree[i] = new ArrayList<>();
        }
        for(int i = 1; i <= N - 1; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b, d));
            tree[b].add(new Node(a, d));
        }

        // DFS

        // 모든 쿼리에 대해서 정답 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            visited = new int[N + 1];
            dfs(a, b, 0);

            sb.append(answer + "\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int cur, int target, int dist){
        if(visited[cur] != 0){
            return;
        }

        visited[cur] = 1;
        if(cur == target){
            answer = dist;
        }

        for(Node next : tree[cur]){
            dfs(next.vertex, target,dist + next.dist);
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
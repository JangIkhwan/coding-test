import java.io.*;
import java.util.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/18352

    25/2/11 15:28 ~
     */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, K, X;
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] minimumDists;

    public static void main(String[] args) throws IOException {
        init();
        bfs(X);
        printAnswer();
    }

    private static void init() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
        }
        visited = new boolean[N + 1];
        minimumDists = new int[N + 1];
    }

    private static void bfs(int start){
        Queue<Node> qu = new LinkedList<>();
        qu.add(new Node(start, 0));
        while(!qu.isEmpty()){
            Node current = qu.poll();
            if(visited[current.number]) continue;
            visited[current.number] = true;
            minimumDists[current.number] = current.step;
            for(int next : graph[current.number]){
                qu.add(new Node(next, current.step + 1));
            }
        }
    }

    private static void printAnswer() throws IOException {
        boolean notExist = true;
        for(int i = 1; i <= N; i++){
            if(minimumDists[i] == K){
                bw.write(Integer.toString(i) + "\n");
                notExist = false;
            }
        }
        if(notExist){
            bw.write("-1");
        }
        bw.flush();
    }

    static class Node{
        private int number;
        private int step;

        public Node(int number, int step){
            this.number = number;
            this.step = step;
        }
    }
}
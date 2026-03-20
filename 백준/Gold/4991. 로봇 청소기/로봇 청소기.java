import java.io.*;
import java.util.*;

public class Main {

    private int W, H;
    private int [][] room;
    private List<int[]> dusts;
    private boolean[] selected;
    private int answer = 401;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    public void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if(W == 0 && H == 0){
                break;
            }

            // room은 정수로 저장한다
            // 모든 정점을 찾는다
            room = new int[H][W];
            int vertexNum = 2;
            dusts = new ArrayList<>();
            for(int row = 0; row < H; row++){
                String line = br.readLine();
                for(int col = 0; col < W; col++){
                    if(line.charAt(col) == 'o'){
                        room[row][col] = 1;
                        dusts.add(new int[] { 1, row, col });
                    }
                    else if(line.charAt(col) == '*'){
                        room[row][col] = vertexNum;
                        dusts.add(new int[] { vertexNum, row, col });
                        vertexNum++;
                    }
                    else if(line.charAt(col) == 'x'){
                        room[row][col] = -1;
                    }
                }
            }
            dusts.sort((a, b) -> a[0] - b[0]);

            answer = 401;

            //
//            System.out.println("room[][] = ");
//            for(int i = 0; i < H; i++){
//                System.out.println(Arrays.toString(room[i]));
//            }

            // 방을 그래프로 변환한다
            List<List<int []>> graph = convertToGraph();

            //
//            for(int i = 1; i < vertexNum; i++){
//                System.out.print("graph " + i + "=");
//                for(int[] v : graph.get(i)){
//                    System.out.print("(" + v[0] + ", " + v[1] + ")");
//                }
//                System.out.println();
//            }

            if(graph.get(1).size() < vertexNum - 2){
                System.out.println(-1);
                continue;
            }

            // 그래프의 모든 정점을 세우는 순열을 구해서 최단 거리를 구한다
            selected = new boolean[vertexNum];
            selected[1] = true;
            findMinDist(1, 0, vertexNum - 2, graph);

            // 최단거리를 출력한다
            System.out.println(answer);
        }
    }

    private List<List<int []>> convertToGraph(){
        // 각 정점에서 다른 정점까지의 최단거리를 구한다. bfs로
        // 그래프를 완성한다

        List<List<int []>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for(int[] dust : dusts){
            bfs(graph, dust[1], dust[2]);
        }

        return graph;
    }

    private void bfs(List<List<int []>> graph, int sr, int sc){
        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };
        List<int[]> adj = new ArrayList<>();
        Queue<int []> qu = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];

        qu.add(new int[]{ 0, sr, sc });
        visited[sr][sc] = true;

        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            int dist = cur[0];
            int r = cur[1];
            int c = cur[2];

            for(int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nr >= H || nc < 0 || nc >= W){
                    continue;
                }
                if(visited[nr][nc]){
                    continue;
                }
                if(room[nr][nc] == -1){
                    continue;
                }

                visited[nr][nc] = true;
                qu.add(new int[] { dist + 1, nr, nc });
                if(room[nr][nc] > 0){
                    adj.add(new int [] { room[nr][nc], dist + 1 });
                }
            }
        }

        graph.add(adj);
    }

    private void findMinDist(int prev, int dist, int depth, List<List<int []>> graph){
        if(depth == 0){
            answer = Math.min(answer, dist);
            return;
        }

        for(int[] next : graph.get(prev)){
            if(!selected[next[0]]){
                selected[next[0]] = true;
                findMinDist(next[0], dist + next[1], depth - 1 , graph);
                selected[next[0]] = false;
            }
        }
    }
}
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    https://www.acmicpc.net/problem/14502

    25/1/26 12:03 ~ 12:47 16:10 ~
    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;

    private static int maxSafeArea;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        int [][] map;
        String line = br.readLine();
        new StringTokenizer(line);
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int j = 1; j <= M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
         simulate(1, map);
         System.out.println(maxSafeArea);
    }

    private static void simulate(int depth, int[][] prevVisited){
        int[][] curVisited = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++)
            curVisited[i] = prevVisited[i].clone();
        if(depth > 3){
            for(int y = 1; y <= N; y++){
                for(int x = 1; x <= M; x++){
                    if(curVisited[y][x] == 2){
                        dfs(x, y, curVisited);
                    }
                }
            }
            int safeArea = 0;
            for(int y = 1; y <= N; y++){
                for(int x = 1; x <= M; x++){
                    if(curVisited[y][x] == 0){
                        safeArea++;
                    }
                }
            }
            if(maxSafeArea < safeArea){
                maxSafeArea = safeArea;
            }
            return;
        }
        for(int y = 1; y <= N; y++){
            for(int x = 1; x <= M; x++){
                if(curVisited[y][x] == 0){
                    curVisited[y][x] = 1;
                    simulate(depth + 1, curVisited);
                    curVisited[y][x] = 0;
                }
            }
        }
    }

    private static void dfs(int x, int y, int[][] visited){
        visited[y][x] = 2;
        for(int direct= 0; direct < 4; direct++){
            int nx = x + dx[direct];
            int ny = y + dy[direct];
            if(nx < 1 || nx > M || ny < 1 || ny > N) continue;
            if(visited[ny][nx] != 0) continue;
            dfs(nx, ny, visited);
        }
    }
}

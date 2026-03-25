import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int M, N, answer = 0;
    private static int [][] map;
    private static int [][] dp;
    private static boolean[][] visited;
    private static int [] dx = {-1, 0, 1, 0};
    private static int [] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M + 1][N + 1];
        dp = new int[M + 1][N + 1];
        visited = new boolean[M + 1][N + 1];
        for(int i = 1; i <= M; i++)
            Arrays.fill(dp[i], -1);
        for(int y = 1; y <= M; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 1; x <= N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(1, 1);
        System.out.println(dp[1][1]);
    }

    public static int dfs(int curX, int curY){
        if(curX == N && curY == M) {
            return 1;
        }
        int reachableroutes = 0;
        for(int direct = 0; direct < 4; direct++){
            int nx = curX + dx[direct];
            int ny = curY + dy[direct];
            if(nx < 1 || nx > N || ny < 1 || ny > M) continue;
            if(map[ny][nx] >= map[curY][curX]) continue;
            if(dp[ny][nx] != -1){
                reachableroutes += dp[ny][nx];
            }
            else{
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    reachableroutes += dfs(nx, ny);
                    visited[ny][nx] = false;
                }
            }
        }
        dp[curY][curX] = reachableroutes;
        return reachableroutes;
    }
}
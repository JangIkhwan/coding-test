import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T, M, N, K;
        String line = br.readLine();
        T = Integer.parseInt(line);
        while(T-- > 0){
            int minimumWormNumber = 0;
            int[][] map;
            boolean[][] visited;
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            for(int i = 0; i < K; i++){
                line = br.readLine();
                st = new StringTokenizer(line);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }
            visited = new boolean[N][M];
            for(int y = 0; y < N; y++){
                for(int x = 0; x < M; x++){
                    if(!visited[y][x] && map[y][x] == 1){
                        dfs(map, visited, x, y);
                        minimumWormNumber++;
                    }
                }
            }
            bw.write(minimumWormNumber + "\n");
        }
        bw.flush();
    }

    private static void dfs(int[][] map, boolean[][] visited, int x, int y){
        if(y < 0 || y >= map.length || x < 0 || x >= map[0].length) return;
        if(visited[y][x]) return;
        if(map[y][x] != 1) return;
        visited[y][x] = true;
        dfs(map, visited, x + 1, y);
        dfs(map, visited, x - 1, y);
        dfs(map, visited, x, y + 1);
        dfs(map, visited, x, y - 1);
    }

}
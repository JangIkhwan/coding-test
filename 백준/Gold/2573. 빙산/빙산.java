import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for(int y = 1; y <= N; y++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int x = 1; x <= M; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 시뮬레이션
        int year = 0;
        while(checkIceburgExists()){

//            System.out.println("map = ");
//            for(int y = 1; y <= N; y++){
//                for(int x = 1; x <= M; x++){
//                    System.out.print(map[y][x] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            // map에 대해서 dfs를 통해서 CC의 개수를 알아낸다
            int iceburgCount = 0;
            boolean[][] visited = new boolean[N + 1][M + 1];
            for(int y = 1; y <= N; y++){
                for(int x = 1; x <= M; x++){
                    if(!visited[y][x] && map[y][x] > 0){
                        dfs(x, y, visited);
                        iceburgCount++;
                    }
                }
            }

//            System.out.println("iceburgCount = " + iceburgCount);

            // CC가 최초로 2개 이상이면 종료하고 답을 출력
            if(iceburgCount > 1){
                System.out.println(year);
                return;
            }

            // 빙하를 녹이고 다시 시뮬레이션
            int[][] nextYearMap = new int[N + 1][M + 1];
            for(int y = 1; y <= N; y++){
                for(int x = 1; x <= M; x++){
                    if(map[y][x] > 0){
                        int oceanDirection = 0;
                        for(int direct = 0; direct < 4; direct++){
                            int nx = x + dx[direct];
                            int ny = y + dy[direct];
                            if(nx < 1 || nx > M || ny < 1 || ny > N) continue;
                            if(map[ny][nx] == 0){
                                oceanDirection++;
                            }
                        }
                        nextYearMap[y][x] = Math.max(0, map[y][x] - oceanDirection);
                    }
                }
            }

            map = nextYearMap;
            year++;
        }
        System.out.println(0);
    }

    private static void dfs(int x, int y, boolean[][] visited){
        if(visited[y][x]) {
            return;
        }
        visited[y][x] = true;
        for(int direct = 0; direct < 4; direct++){
            int nx = x + dx[direct];
            int ny = y + dy[direct];
            if(nx < 1 || nx > M || ny < 1 || ny > N) continue;
            if(map[ny][nx] == 0) continue;
            dfs(nx, ny, visited);
        }
    }

    private static boolean checkIceburgExists(){
        for(int y = 1; y <= N; y++){
            for(int x = 1; x <= M; x++) {
                if (map[y][x] > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int N, M;
        int[][] map;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++){
            line = br.readLine();
            for(int j = 1; j <= M; j++){
                map[i][j] = Integer.parseInt(line.substring(j - 1, j));
            }
        }

        // bfs로 최단 거리 구하기
        int result = bfs(map, N, M);

        // map에 저장된 최단거리 출력
        System.out.println(result);
    }

    private static int bfs(int[][] map, int N, int M){
        Queue<Node> qu = new LinkedList();
        boolean[][] visited = new boolean[N + 1][M + 1];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        qu.add(new Node(1, 1, 1));

        while(!qu.isEmpty()){
            Node current = qu.poll();
            if(visited[current.y][current.x]) continue;
            visited[current.y][current.x] = true;
            map[current.y][current.x] = current.step;

//            System.out.println("==debug==");
//            for(int i = 1; i <= N; i++){
//                for(int j = 1; j <= M; j++){
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

            for(int direct = 0; direct < 4; direct++){
                int nx = current.x + dx[direct];
                int ny = current.y + dy[direct];
                if(nx < 1 || nx > M || ny < 1 || ny > N){
                    continue;
                }
                if(map[ny][nx] == 0) {
                    continue;
                }
                qu.add(new Node(nx, ny, current.step + 1));
            }
        }

        return map[N][M];
    }

    static class Node {
        int x;
        int y;
        int step;

        public Node(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}
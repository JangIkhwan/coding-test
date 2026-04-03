import java.io.*;
import java.util.*;

public class Main {
    /*
    26/4/3 23:39 ~ 01:40, 01:43 ~ 02:03

    https://www.acmicpc.net/problem/1261

    (1,1)에서 (N,M)까지 이동할 때 최소 몇개의 벽을 부숴야하는지 구하자

    최소 개수를 구하라

    완탐은 안 됨
    최악의 경우 전체 경우가 2^1만 이기 때문

    dp로 풀 수 있을까? 폐기
    dp[i][j][k] : i행 j열에 k방향으로 들어올 때 벽을 부수는 횟수의 최솟값
    근데 계산하는 방향이 고정되어 있지 않다

    도달불가능하면 벽을 부숴야 한다
    어떤 기준으로?
    만약 0으로 둘러쌓인 영역을 정점으로 보고, 1로 되어 있는 부분을 엣지로 본다면?
    그리고 최단경로를 통해서 이동한다면?
    그러면 원래 문제를 푸는 것과 동일하네

    정점은 최대 몇개일까? 5000개
    그래프를 구성하는데 V^2의 복잡도이다. 최대 5000^2이니 괜찮은듯?

    메모리 초과 발생
    인접행렬을 써서 그런 듯. 리스트로 변환

    그래프로 변환하는 방법은 메모리 초과가 일어나므로 옳지 않은 풀이임...

    힌트를 보니 0-1 너비우선탐색이나 다익스트라로 풀 수 있다는데?
    주어진 정보 자체가 그래프인가?
    도착한 곳이 1이면 거리가 1인거고, 도착한 곳이 0이면 거리고 0이었던거고
    */
    private int N;
    private int M;
    private int[][] maze;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    public void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        maze = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            for(int j = 1; j <= M; j++){
                maze[i][j] = line.charAt(j - 1) - '0';
            }
        }

//        for(int[] m : maze){
//            System.out.println(Arrays.toString(m));
//        }

        int answer = dijkstra();

        System.out.println(answer);
    }

    private int dijkstra(){
        int[] dy = { -1, 0, 1, 0 };
        int[] dx = { 0, 1, 0, -1 };
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        int[][] minDists = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(minDists[i], Integer.MAX_VALUE);
        }

        minDists[1][1] = 0;
        pq.offer(new int[] { 1, 1, 0 });

        while(!pq.isEmpty()){
            int[] poll = pq.poll();
            int y = poll[0];
            int x = poll[1];
            int dist = poll[2];

            if(minDists[y][x] < dist){
                continue;
            }

            for(int i = 0; i < 4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(ny < 1 || ny > N || nx < 1 || nx > M){
                    continue;
                }

                if(minDists[ny][nx] > dist + maze[ny][nx]){
                    minDists[ny][nx] = dist + maze[ny][nx];
                    pq.offer(new int []{ ny, nx, dist + maze[ny][nx] });
                }
            }
        }

//        for(int[] m : minDists){
//            System.out.println(Arrays.toString(m));
//        }

        return minDists[N][M];
    }
}
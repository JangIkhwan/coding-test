import java.io.*;
import java.util.*;

public class Solution {
    /*
    * 26-7-26 11:51 ~
    *
    * 복구 시간이 가장 작은 경로의 복구 시간을 구하자
    *
    * ---
    *
    * 결국 s에서 g까지 이동할 때 합의 최솟값을 구해야 한다
    *
    * bfs를 이용할 수 있을까?
    * -> bfs를 이용하려면 그래프를 직접 구성해야 한다
    *
    * 다익스트라를 이용할까?
    *
    * ---
    *
    * 이동 방향은 상하좌우 방향이다
    * N <= 100이다
    *
    * */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int[] dr = { -1, 0, 1, 0 };
    final int[] dc = { 0, 1, 0, -1 };
    private int N;
    private int[][] map;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int test = 1; test <= T; test++){
            int answer = 0;
            Solution s = new Solution();
            s.init();
            answer = s.solve();
            System.out.println("#" + test + " " + answer);
        }
    }

    private void init() throws IOException{
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int r = 0; r < N; r++){
            String line = br.readLine();
            for(int c = 0; c < N; c++){
                map[r][c] = line.charAt(c) - '0';
            }
        }
    }

    private int solve(){
        int[][] dists = new int[N][N];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        for(int i = 0; i < N; i++){
            Arrays.fill(dists[i], 9 * 100 * 100);
        }

        dists[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dists[cur.toR][cur.toC] < cur.dist){
                continue;
            }

            for(int i = 0; i < 4; i++){
                int nextR = cur.toR + dr[i];
                int nextC = cur.toC + dc[i];

                if(nextR < 0 || nextR > N - 1 || nextC < 0 || nextC > N - 1){
                    continue;
                }

                int nextDist = cur.dist + map[nextR][nextC];

                if(dists[nextR][nextC] > nextDist){
                    dists[nextR][nextC] = nextDist;
                    pq.offer(new Node(nextR, nextC, nextDist));
                }
            }
        }

        return dists[N - 1][N - 1];
    }

    static class Node{
        int toR;
        int toC;
        int dist;

        public Node(int toR, int toC, int dist){
            this.toR = toR;
            this.toC = toC;
            this.dist = dist;
        }
    }
}
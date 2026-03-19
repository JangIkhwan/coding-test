import java.io.*;
import java.util.*;

public class Main {

    private int N, L, R;
    private int[][] A;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    public void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        // 인구 이동 시뮬레이션
        // 연합이 만들어지지 않을때까지 반복
            // 연합을 모두 찾는다
            // 각 연합에서 인구 이동을 시킨다
            // 날짜수 증가

        while(true){
            List<List<int[]>> unions = findUnions();
//            System.out.println("unions " + unions.size());
            if(unions.isEmpty()){
                break;
            }
            distribute(unions);
            answer++;
        }

        // 날짜수 출력
        System.out.println(answer);
    }

    private List<List<int[]>> findUnions(){
        List<List<int[]>> unions = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                if(!visited[row][col]){
                    List<int[]> union = bfs(visited, row, col);
                    if(union.size() > 1){
                        unions.add(union);
                    }
                }
            }
        }
        return unions;
    }

    private List<int[]> bfs(boolean[][] visited, int sr, int sc){
        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0 , -1 };

        List<int[]> union = new ArrayList<>();
        Queue<int []> qu = new LinkedList<> ();
        visited[sr][sc] = true;
        qu.add(new int[] { sr,sc });
        union.add(new int[] { sr, sc });

        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            int r = cur[0];
            int c = cur[1];

            for(int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N){
                    continue;
                }
                if(visited[nr][nc]){
                    continue;
                }
                int diff = Math.abs(A[nr][nc] - A[r][c]);
                if(!(diff >= L && diff <= R)){
                    continue;
                }

                visited[nr][nc] = true;
                qu.add(new int[] { nr, nc });
                union.add(new int[] { nr, nc });
            }
        }

//        System.out.println("union = ");
//        for(int[] u : union){
//            System.out.println(u[0] + ", " + u[1]);
//        }

        return union;
    }

    private void distribute(List<List<int []>> unions){
        for(List<int []> union : unions){
            int sum = 0;
            for(int[] cell : union){
                sum += A[cell[0]][cell[1]];
            }
            int avg = sum / union.size();
            for(int[] cell : union){
                A[cell[0]][cell[1]] = avg;
            }
        }
    }
}
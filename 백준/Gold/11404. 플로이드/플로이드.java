import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;
    private static int[][] dist;
    private static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        init();
        floyd();
        StringBuilder sb = new StringBuilder();
        for(int start = 1; start <= N; start++){
            for(int end = 1; end <= N; end++){
                if (dist[start][end] == INF) sb.append("0");
                else sb.append(Integer.toString(dist[start][end]));
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for(int i = 1; i <= M; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = Math.min(dist[start][end], cost);
        }
    }

    private static void floyd(){
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}
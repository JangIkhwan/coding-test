import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;

    private static int [][] dp;

    private static final int INF = 1000;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(dp[i], INF);
        }
        for(int i = 1; i <= M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dp[a][b] = 1;
        }
    }

    private static void solve() {
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }

        int answer = 0;
        for(int i = 1; i <= N; i++){
            if(canFindSequence(i)){
                answer++;
            }
        }
        System.out.println(answer);
    }


    private static boolean canFindSequence(int one){
        for(int another = 1; another <= N; another++){
            if(another != one && dp[one][another] == INF && dp[another][one] == INF){
                return false;
            }
        }
        return true;
    }

}
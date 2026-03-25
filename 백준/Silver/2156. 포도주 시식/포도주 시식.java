import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] wines = new int[N + 1];
        for(int i = 1; i <= N; i++){
            wines[i] = Integer.parseInt(br.readLine());
        }
        int[][] dp = new int[N + 1][3];
        for(int i = 1; i <= N; i++){
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][2]) + wines[i];
            dp[i][1] = dp[i - 1][2] + wines[i];
            dp[i][2] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])); 
        }
        System.out.println(Math.max(dp[N][0], Math.max(dp[N][1], dp[N][2])));
    }
}
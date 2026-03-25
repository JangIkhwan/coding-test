import java.io.*;
import java.util.*;

public class Main {
   
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] dp;

    private static int INF = 2000;

    // DP
    public static void main(String[] args) throws IOException {
        int N;
        N = Integer.parseInt(br.readLine());
        dp = new int[5001];
        Arrays.fill(dp, INF);
        dp[3] = 1;
        dp[5] = 1;
        for(int i = 6; i <= N; i++){
            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }
        if(dp[N] >= INF) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[N]);
    }
}
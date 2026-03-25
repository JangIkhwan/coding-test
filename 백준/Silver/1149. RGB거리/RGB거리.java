import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*

    1149
    https://www.acmicpc.net/problem/1149

    24/11/19 13:21 ~
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int [][] cost = new int[N + 1][3];
        int [][] dp = new int[N + 1][3];
        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }
        System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
    }
}
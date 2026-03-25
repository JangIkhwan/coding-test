import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        int N, K;
        int[] coins;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N + 1];
        for(int i = 1; i <= N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);

        if(K < coins[0]){
            System.out.println(0);
            return;
        }

        // dp 계산
        int[][] dp = new int[N + 1][K + 1];
        for(int j = 1; j <= K; j++){
            if(j % coins[1] == 0){
                dp[1][j] = 1;
            }
        }
        for(int i = 1; i <= N; i++){
            dp[i][0] = 1;
        }
        for(int i = 2; i <= N; i++){
            for(int j = 1; j <= K; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - coins[i] >= 0) dp[i][j] += dp[i][j - coins[i]];
            }
        }

        // 답 출력
        System.out.println(dp[N][K]);
    }
}
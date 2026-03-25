import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        int N, M;
        boolean[] isVip;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        isVip = new boolean[N + 1];
        for(int i = 1; i <= M; i++){
            int seat = Integer.parseInt(br.readLine());
            isVip[seat] = true;
        }

        // dp 계산
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= N; i++){
            if(!isVip[i] && !isVip[i - 1]) dp[i] = dp[i - 2] + dp[i - 1];
            else dp[i] += dp[i - 1];
        }

        // 출력
        System.out.println(dp[N]);
    }
}
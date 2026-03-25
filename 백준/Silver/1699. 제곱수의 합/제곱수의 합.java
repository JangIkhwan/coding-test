import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;

        // 입력 처리
        N = Integer.parseInt(br.readLine());

        // dp
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= Math.sqrt(i); j++){
                dp[i] = Math.min(dp[i], 1 + dp[i - j*j]);
            }
        }
        System.out.println(dp[N]);
    }
}
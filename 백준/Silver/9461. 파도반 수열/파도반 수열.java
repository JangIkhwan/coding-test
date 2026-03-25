import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int T;
        long [] dp = new long[101];
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for(int i = 6; i <= 100; i++){
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
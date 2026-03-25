import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[10001];
        int[] num = { 1, 2, 3 };
        dp[0] = 1;
        for(int j = 0; j < 3; j++){
            for(int i = 1; i <= 10000; i++){
                if(i - num[j] >=0) dp[i] += dp[i - num[j]];
            }
        }

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N] + "\n");
        }
        System.out.println(sb.toString());
    }
}
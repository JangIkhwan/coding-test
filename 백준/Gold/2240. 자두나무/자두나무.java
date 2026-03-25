import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        int T, W;
        int[] tree;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T + 1];
        for(int i = 1; i <= T; i++){
            tree[i] = Integer.parseInt(br.readLine());
        }

        // dp 계산
        int[][] dp = new int[T + 1][W + 1];
        dp[1][0] = tree[1] == 1 ? 1 : 0;
        dp[1][1] = tree[1] == 2 ? 1 : 0;
        for(int i = 2; i <= T; i++){
            dp[i][0] = dp[i - 1][0] + (tree[i] == 1 ? 1 : 0);
        }
        for(int i = 2; i <= T; i++){
            int limit = Math.min(i, W);
            for(int j = 1; j <= limit; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + (j % 2 == 0 && tree[i] == 1 || j % 2 == 1 && tree[i] == 2 ? 1 : 0);
            }
        }

//        System.out.println("dp = ");
//        for(int i = 0; i <= T; i++){
//            for(int j = 0; j <= W; j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        // 최댓값 출력
        int limit = Math.min(T, W);
        int maxValue = 0;
        for(int i = 0; i <= limit; i++){
            maxValue = Math.max(maxValue, dp[T][i]);
        }
        System.out.println(maxValue);
    }
}
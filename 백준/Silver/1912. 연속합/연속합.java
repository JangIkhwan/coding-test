import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    https://www.acmicpc.net/problem/1912

    24/11/24 12:33 ~

    arr : 10, -4, 3, 1, 5, 6, -35, 12, 21, -1
    dp? : 10, 6, 9, 10, 15, 16, -19, 12, 33, 32

    arr : 2 1 -4 3 4 -4 6 5 -5 1
    dp? : 2 3 -1 3 7 3 9 14 9 10

    dp [i] : i를 마지막 원소로 갖는 연속합 중 최댓값
    dp[i] = max(dp[i - 1] + arr[i], arr[i]);
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i <= N; i++){
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }
        int maxSum = dp[1];
        for(int i = 2; i <= N; i++){
            maxSum = Math.max(maxSum, dp[i]);
        }
        System.out.print(maxSum);
    }
}
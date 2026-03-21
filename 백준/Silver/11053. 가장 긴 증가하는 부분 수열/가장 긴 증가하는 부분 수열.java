import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    public void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        int[] A;
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // LIS 구하기
        int lisLength = 1;
        int[] dp = new int[N + 1];
        for(int i = 1; i <= N; i++){
            dp[i] = 1;
            for(int j = 1; j < i; j++){
                if(A[j] < A[i] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    if(lisLength < dp[i]){
                        lisLength = dp[i];
                    }
                }
            }
        }

        // 출력
        System.out.println(lisLength);
    }
}
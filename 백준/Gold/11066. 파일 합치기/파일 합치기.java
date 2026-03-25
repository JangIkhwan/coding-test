import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T;
        T = Integer.parseInt(br.readLine());
        while(T -- > 0){
            int K = Integer.parseInt(br.readLine());
            int [] chapters = new int[K + 1];
            int [] sum = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= K; i++){
                chapters[i] = Integer.parseInt(st.nextToken());
                sum[i] += sum[i - 1] + chapters[i];
            }
            int[][] dp = new int[K + 1][K + 1];
            for(int i = 1; i <= K; i++) {
                Arrays.fill(dp[i], -1);
                dp[i][i] = 0;
            }
            for(int len = 2; len <= K; len++){
                for(int i = 1; i + len - 1 <= K; i++){
                    int j = i + len - 1;
                    for(int k = i; k + 1 <= j; k++){
                        if(dp[i][j] == -1) {
                            dp[i][j] = dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1];

                        }
                        else{
                            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);
                        }
                    }
                }
            }
            System.out.println(dp[1][K]);
        }
    }
}
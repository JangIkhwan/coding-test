import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int[][] triangle;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        dp = new int[N + 1][N + 1];
        dp[1][1] = triangle[1][1];
        for(int row = 2; row <= N; row++){
            for(int col = 1; col <= row; col++){
                if(col >= 2) {
                    dp[row][col] = dp[row - 1][col - 1] + triangle[row][col];
                }
                if(col < row){
                    dp[row][col] = Math.max(dp[row][col], dp[row - 1][col] + triangle[row][col]);
                }
            }
        }
        int answer = 0;
        for(int col = 1; col <= N; col++){
            answer = Math.max(answer, dp[N][col]);
        }
        System.out.println(answer);
    }

    private static void input() throws IOException {
        String line = br.readLine();
        N = Integer.parseInt(line);
        triangle = new int[N + 1][N + 1];
        for(int row = 1; row <= N; row++){
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for(int col = 1; col <= row; col++){
                triangle[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
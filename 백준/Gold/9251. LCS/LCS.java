import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        String A, B;
        A = br.readLine();
        B = br.readLine();
        lcs(A, B);
    }

    private static void lcs(String A, String B){
        int [][] dp;
        dp = new int[A.length()][B.length()];
        int value = 0;
        for(int i = 0; i < B.length(); i++){
            if(A.charAt(0) == B.charAt(i)){
                value = 1;
            }
            dp[0][i] = value;
        }
        value = 0;
        for(int i = 1; i < A.length(); i++){
            if(A.charAt(i) == B.charAt(0)){
                value = 1;
            }
            dp[i][0] = value;
        }
        for(int i = 1; i < A.length(); i++){
            for(int j = 1; j < B.length(); j++){
                if(A.charAt(i) == B.charAt(j)){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        System.out.println(dp[A.length() - 1][B.length() - 1]);
    }
}
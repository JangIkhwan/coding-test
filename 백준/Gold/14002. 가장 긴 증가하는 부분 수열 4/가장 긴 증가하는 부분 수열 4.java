import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        int N;
        int[] A;
        int[][] dp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // lis 계산
        int maxLength = 1;
        int maxIndex = 0;
        dp = new int[2][N];
        dp[0][0] = 1;
        Arrays.fill(dp[1], -1);
        for(int i = 1; i < N; i++){
            dp[0][i] = 1;
            for(int j = 0; j < i; j++){
                if(A[j] < A[i] && dp[0][i] < dp[0][j] + 1){
                    dp[0][i] = dp[0][j] + 1;
                    dp[1][i] = j;
                }
            }
            if(maxLength < dp[0][i]){
                maxLength = dp[0][i];
                maxIndex = i;
            }
        }

        // 출력
        System.out.println(maxLength);
        Stack<Integer> stack = new Stack<>();
        int index = maxIndex;
        while(index != -1){
            stack.push(A[index]);
            index = dp[1][index];
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
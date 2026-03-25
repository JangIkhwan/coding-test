import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] W;
    private static int[] V;
    private static int[][] dp;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        W = new int[N + 1];
        V = new int[N + 1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        // dp 계산
        dp = new int[N + 1][K + 1];
        for(int i = 0; i <= N; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(backpack(1, K));
    }

    // bag의 여유 공간이 있고 cur 이후에 있는 걸 선택해서 얻을 수 있는 이익의 최대
    private static int backpack(int cur, int bag){
        if(cur > N){
            return 0;
        }
        if(bag == 0){
            return 0;
        }
        
        int ret = dp[cur][bag];
        if(ret != -1){
            return ret;
        }
        if(bag - W[cur] >= 0){
            ret = Math.max(ret, backpack(cur + 1, bag - W[cur]) + V[cur]);
        }
        ret = Math.max(ret, backpack(cur + 1, bag));
        return dp[cur][bag] = ret;
    }
}
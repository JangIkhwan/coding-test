import java.util.*;

class Solution {
    /*
    26-7-20 20:40 ~ 
    
    x를 y로 만들기 위한 최소의 연산 횟수
    
    ---
    
    dp인가?
    
    dp[i] : x를 i로 만들기 위한 최소의 연산 횟수
    
    dp[i] = min(
        dp[i - n] + 1
        dp[i / n] + 1
        dp[i / n] + 1
    )
    
    dp[x + n] = 1
    dp[x * 2] = 1
    dp[x * 3] = 1
    
    dp[15] = 1
    dp[20] = 1
    dp[30] = 1
    */
    public int solution(int x, int y, int n) {
        final int INF = 1000_001;
        int[] dp = new int[y + 1];
        
        Arrays.fill(dp, INF);
        
        dp[x] = 0;
        
        for(int i = x; i <= y; i++){
            if(i > n){
                dp[i] = Math.min(dp[i], dp[i - n] + 1);
            }
            if(i % 2 == 0){
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if(i % 3 == 0){
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
        
        if(dp[y] == INF){
            return -1;
        }
        return dp[y];
    }
}
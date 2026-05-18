class Solution {
    /*
    26/5/18 16:23 ~ 
    
    직사각형을 채우는 방법의 수를 구하자
    
    세로가 3이다
    
    dp인가?
    가로 2칸을 차지하는 방법은 3개다
    가로 2n (n >= 2)개를 차지하는 방법은 2개다
    
    dp[0] = 1
    dp[2] = 3
    
    dp[i] = dp[i - 2] * 3 + sum(2 * dp[i - 2*n]) 
    
    n = 6이면?
    dp[2] = 3
    dp[4] = 11
    dp[6] = 11 * 3 + 2 * 3 + 2 * 1 = 33 + 6 + 2 = 41
    
    */
    
    public int solution(int n) {
        final int MOD = 1000_000_007;
        long[] dp = new long[n + 1];
        
        dp[0] = 1;
        dp[2] = 3;
        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i - 2] * 3) % MOD;
            for(int j = 2; 2 * j <= i; j++){
                dp[i] += (2 * dp[i - 2 * j]) % MOD;
            }
            dp[i] %= MOD;
        }
        
        return (int) dp[n];
    }
}
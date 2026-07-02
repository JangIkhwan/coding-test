class Solution {
    /*
    26/7/2 12:20 ~ 13:00
    
    연속된 집을 피하면서 도둑질을 할 수 있는 돈의 최댓값을 구하자
    
    ---
    
    예제를 돌리자
    
   [1, 2, 3, 1]
    1 2 4 3 // 도둑질 o
    0 1 2 4 // 도둑질 x
    
    [1, 2, 1, 2, 3, 2]
    1 2 2 4 5 6
    0 1 2 2 4 5
    
    ---
    
    문제가 dp 스럽다. 
    
    dp[i][1] : i번째 집을 도둑질 할 때 얻을 수 있는 최대 이득
    dp[i][0] : i번째 집을 도둑질 하지 않을 때 얻을 수 있는 최대 이득
    
    dp[i][1] = money[i] + dp[i - 1][0]
    dp[i][0] = max(dp[i - 1][1], dp[i - 1][0])
    
    최종 답은 max(dp[n][0], dp[n][1])
    
    --- 
    
    1차 시도 결과 정확성에서 실패했다
    
    아 원형이니까 첫번째 집이랑 마지막 집은 둘 다 선택되면 안됨!
    
    ---
    
    2차 시도 결과 효율성에서 틀렸다
    
    money 길이 <= 100만이라 dp 두번 구해도 문제가 안될 줄 알았는데
    이 문제에서는 시간낭비다??
    
    원형이기 때문에 첫번째와 마지막이 둘 다 선택되는 경우만 제외하면 되는데
    
    dp[i][j][1] : 0번 집을 무조건 포함해서 i번째 집에서 j번 행위를 했을 때 얻는 이득
    dp[i][j][0] : 0번 집을 무조건 포함해서 i번째 집에서 j번 행위를 했을 때 얻는 이득
    
    */
    
    
    public int solution(int[] money) {
        int N = money.length;
        
        int[][] dp = new int[2][N];
        dp[1][0] = money[0];
        for(int i = 1; i < N - 1; i++){
            dp[0][i] = Math.max(dp[1][i - 1], dp[0][i - 1]);
            dp[1][i] = money[i] + dp[0][i - 1];
        }
        int startAtFirst = Math.max(dp[0][N - 2], dp[1][N - 2]);
        
        dp = new int[2][N];
        dp[1][1] = money[1];
        for(int i = 2; i < N; i++){
            dp[0][i] = Math.max(dp[1][i - 1], dp[0][i - 1]);
            dp[1][i] = money[i] + dp[0][i - 1];
        }
        int startAtSecond = Math.max(dp[0][N - 1], dp[1][N - 1]);
        
        return Math.max(startAtFirst, startAtSecond);
    }
    
    public int anotherSolution(int[] money) {
        int N = money.length;
        
        int[] dp1 = new int[N]; // 집을 턴다
        int[] dp2 = new int[N]; // 안 턴다
        dp1[0] = money[0];
        for(int i = 1; i < N - 1; i++){
            dp1[i] = money[i] + dp2[i - 1];
            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);
        }
        int startAtFirst = Math.max(dp1[N - 2], dp2[N - 2]);
        
        dp1 = new int[N]; // 집을 턴다
        dp2 = new int[N]; // 안 턴다
        dp1[1] = money[1];
        for(int i = 2; i < N; i++){
            dp1[i] = money[i] + dp2[i - 1];
            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);
        }
        int startAtSecond = Math.max(dp1[N - 1], dp2[N - 1]);
        
        return Math.max(startAtFirst, startAtSecond);
    }
}
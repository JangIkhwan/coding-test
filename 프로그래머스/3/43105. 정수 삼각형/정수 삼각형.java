class Solution {
    /*
    26/6/18 10:21 ~ 
    
    삼각형의 꼭대기에서 바닥까지 이동할 때 거쳐간 숫자의 합의 최댓값을 찾자
    
    ---
    
    완전탐색이 가능한가?
    경우의수가 2^height라서 시간초과 가능성 존재
    
    --- 
    
    삼각형의 일부에 대해서도 똑같은 문제를 풀어야 한다
    
    dp[i][j] : i행 j열을 꼭대기로 하는 삼각형에서 숫자 합의 최댓값 
    
    dp[i][j] = triangle[i][j] + max(dp[i + 1][j], dp[i + 1][j + 1])
    
    */
    public int solution(int[][] triangle) {
        int height = triangle.length;
        int[][] dp = new int[height][height];
        
        for(int i = 0; i < height; i++){
            dp[height - 1][i] = triangle[height - 1][i];
        }
        
        for(int i = height - 2; i >= 0; i--){
            for(int j = 0; j < triangle[i].length; j++){
                dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        
        return dp[0][0];
    }
}
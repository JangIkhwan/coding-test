import java.util.*;

class Solution {
    /*
    26/5/5 15:51 ~ 
    
    도착점까지 이동하는 모든 경로의 수를 구하자
    
    자동차는 오른쪽 또는 아래 방향으로 한칸씩 이동하도록 제한됨
    
    dp인가?
    
    dp[i][j][k] : 출발점에서 i행 j열로 오는 모든 경로의 수 
    k = 0이면 오른쪽에서 들어옴, k=1이면 위쪽에서 들어음
    
    if map[i - 1][j] == 0
    dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]);
    
    if map[i - 1][j] == 1
    dp[i][j][0] = 0;
    
    if map[i - 1][j] == 2
    dp[i][j][0] = dp[i - 1][j][0]
    
    정답은 dp[m][n][0] + dp[m][n][1]
    
    */
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[2][m][n];
        
        for(int r = 0; r < m; r++){
            if(cityMap[r][0] == 1){
                break;
            }
            dp[0][r][0] = 1;
        }
        
        for(int c = 0; c < n; c++){
            if(cityMap[0][c] == 1){
                break;
            }
            dp[1][0][c] = 1;  
        }
        
        for(int r = 1; r < m; r++){
            for(int c = 1; c < n; c++){
                if(r > 0){
                    if(cityMap[r - 1][c] == 0){
                        dp[0][r][c] = (dp[0][r - 1][c] + dp[1][r - 1][c]) % MOD;
                    }
                    else if (cityMap[r - 1][c] == 2){
                        dp[0][r][c] = dp[0][r - 1][c];
                    }
                }
                if(c > 0){
                    if(cityMap[r][c - 1] == 0){
                        dp[1][r][c] = (dp[0][r][c - 1] + dp[1][r][c - 1]) % MOD;
                    }
                    else if (cityMap[r][c - 1] == 2){
                        dp[1][r][c] = dp[1][r][c - 1];
                    }
                }
                
            }
        }
        
//         System.out.println("dp[0] = ");
//         for(int i = 0; i < m; i++){
//             System.out.println(Arrays.toString(dp[0][i]));
//         }
//         System.out.println();
        
//         System.out.println("dp[1] = ");
//         for(int i = 0; i < m; i++){
//             System.out.println(Arrays.toString(dp[1][i]));
//         }
        
        return (dp[0][m - 1][n - 1] + dp[1][m - 1][n - 1]) % MOD;
    }
}
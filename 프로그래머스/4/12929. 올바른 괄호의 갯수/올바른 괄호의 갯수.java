import java.util.*;

class Solution {
    /*
    26/6/29 19:37 ~ 
    
    n개의 괄호 쌍이 주어질 때 만들 수 있는 올바른 괄호 문자열의 개수를 구하자
    
    --
    dp인가?
    
    f(n) : 길이가 n인 만드는 올바른 괄호의 개수
    f(n) = 1 * f(n - 2) + 1 * f(n - 4) + f(2) * f(n - 6) ... + f(n - 4) * 1
     
    n <= 14라서 메모이제이션할 수는 있음
    
    반례
    중복 계산하는 부분이 있다!
    -> 왜 중복이 생길까? 
    
    ()()() 같은 것이 중복되어 세진다
    
    ( 길이 k인 올바른 괄호 ) ( 길이 n - k인 올바른 괄호 ) 
    
    
    -- 
    
    가장 바깥에 괄호 쌍이 1개, 2개, 3개.. 인 경우를 차례로 고려해서 경우의 수를 계산해야 하나?
    
    f(n) : 길이가 n인 괄호 문자열의 개수
    
    f(n) = f(n - 2) 
         + f(0) * f(n - 4) + f(2) * f(n - 6) + ... // 2개
         + f(0) * f(0) * f(n - 6) + f(0) * f(2) * f(n - 8) // 3개
         
         
    f(0) = 1
    f(2) = 1
    f(4) = 2
    f(6) = f(4) + f(0) * f(2) + f(2) * f(0) + f(0) * f(0) * f(0)
        = 2 + 1 + 1 + 1 = 5
        
    
    
    */
    private int[] dp;
    
    public int solution(int n) {
        int length = n * 2;
        
        dp = new int[29];
        
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[2] = 1;
        
        return countCorrectParenthesis(length);
    }
    
    private int countCorrectParenthesis(int length){
        if(length % 2 == 1){
            return 0;
        }
        
        int ret = dp[length];
        if(ret != -1){
            return ret;
        }
        
        ret = 0;
        for(int piece = 1; piece <= length / 2; piece++){
            ret += splitAndCount(piece, length);
        }
        
        return dp[length] = ret;
    }
    
    private int splitAndCount(int piece, int length){
        if(piece <= 1){
            return countCorrectParenthesis(length - 2);
        }
        
        int ret = 0;
        for(int i = 0; ; i += 2) {
            if(2 * (piece - 1) > length - 2 - i){
                break;
            }
            ret += countCorrectParenthesis(i) * splitAndCount(piece - 1, length - 2 - i);
        }
        return ret;
    }
}
class Solution
{
    /*
    26/5/27 16:49 ~ 15:47 : 2개의 테케 실패
    
    S의 부분문자열 중에서 가장 길이가 긴 팰린드롬의 길이를 찾자
    
    LCS랑 유사해보는 문제다
    원래 문자열과 뒤집은 문자열 사이의 LCS를 구하는 문제로 바꿀 수 있을까?
    
    
    dp[i][j] : S[0 ~ i]와 T[0 ~ j]의 공통 접미어의 최대 길이
    
    dp[i][j] = (i == j) dp[i - 1][j - 1] + 1, 
        (i != j) 0
   
    팰린드롬이 아닌 부분 문자열이 LCS로 선택될 수 있을까?
    그렇다. 
    
    따라서 이 방법은 잘못되었다.
  
      
    */
    public int solution(String s)
    {    
        int answer = 0;
        
        for(int center = 0; center < s.length(); center++){
            int length = 1;
            int i = 1;
            while((center - i) >= 0 && (center + i) < s.length()){
                if(s.charAt(center - i) != s.charAt(center + i)){
                    break; 
                }
                length += 2;
                i++;
            }
            answer = Math.max(answer, length);
        }
        
        for(int left = 0; left < s.length() - 1; left++){
            int right = left + 1;
            int length = 0;
            int i = 0;
            while((left - i) >= 0 && (right + i) < s.length()){
                if(s.charAt(left - i) != s.charAt(right + i)){
                    break;
                }
                length += 2;
                i++;
            }
            answer = Math.max(answer, length);
        }

        return answer;
    }
}
import java.util.*;

class Solution {
    /*
    10:15 ~ 10:30 13:50 ~ 14:03
    
    */
    public int solution(int n, int m, int[] section) {
        // 다시 칠할 부분이 모두 칠해질때까지
            // 처음으로 다시 칠할 부분을 찾는다
            // 그곳을 시작으로 페인트를 칠한다
        
        Set<Integer> set = new HashSet<>();
        for(int s : section){
            set.add(s);
        }
        
        int left = section[0];
        int right = section[0];
        int answer = 0;
        while(true){
            if(set.contains(left)){
                if(right - left + 1 < m){
                    right++;
                }
                else {
                    answer++;
                    // System.out.println("left " + left + " right " + right);
                    left = right + 1;
                    right = left;
                }
            }
            else{
                left++;
                right = left;
            }
            
            if(left > n || right > n){
                break;
            }
        }
        if(left <= n){
            answer++;
        }
        return answer;
    }
}
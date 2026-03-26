import java.util.*;

class Solution {
    /*
    11:28 ~ 12:28 13:23 ~ 13:43
    
    모든 차량이 카메라를 만나기 위해 설치할 카메라의 최소 개수를 구하자
    
    아직 카메라를 볼 수 없는 경로들 중에서 경로가 많이 겹치는 지점에 카메라를 설치?

-20 -18 -15 -14 -13 -5 -3
 1    1   0    0   0   0  -1
 
    이 접근이 완전 잘못되었다.
    
    다른 접근 필요
    
    */
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        int answer = 1;
        int end = routes[0][1];
        for(int i = 1; i < routes.length; i++){
            if(routes[i][0] > end){
                answer += 1;
                end = routes[i][1];
            }
        }
        return answer;
    }
}
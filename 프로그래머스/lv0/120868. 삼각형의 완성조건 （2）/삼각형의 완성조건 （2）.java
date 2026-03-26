import java.util.*;

class Solution {
    public int solution(int[] sides) {
        // 입력 중 긴 변이 삼각형에서 제일 긴변이 되는 경우 계산
        int shorter = Math.min(sides[0], sides[1]);
        int longer = Math.max(sides[0], sides[1]);
        int case1 = 0;
        for(int i = longer - shorter + 1; i <= longer - 1; i++){
            case1++;
        }
        
        // 나머지 변이 제일 긴 변이 되는 경우 계산
        int case2 = 0;
        for(int i = longer; i <= longer + shorter - 1; i++){
            case2++;
        }
        
        return case1 + case2;
    }
}
import java.util.*;

class Solution {
    public String solution(int[] food) {
        // 0을 기준으로 왼쪽과 오른쪽을 분리
        // 칼로리가 낮은 순서대로 양을 2로 나눈 몫만큼 왼쪽과 오른쪽에 배치한다
        // 모든 음식을 다 배치하면 0을 가운데에 두고 연결한다.
        
        String left = "";
        String right = "";
        
        for(int i = 1; i < food.length; i++){
            int half = food[i] / 2;
            for(int j = 0; j < half; j++){
                left = left + String.valueOf(i);
                right = String.valueOf(i) + right;
            }
        }

        return left + "0" + right;
    }
}
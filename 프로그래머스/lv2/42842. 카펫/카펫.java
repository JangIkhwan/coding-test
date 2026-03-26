import java.util.*;

class Solution {
    /*
    25/12/21 14:08 ~ 14:10
    
    카펫의 가로 세로 크기를 구하자
    
    노란색은 합성수라고 봐야함
    a x b꼴로 만들 수 있는 경우의 수를 보고
    
    2 * a + 2 * b = 갈색수 - 4 인지 확인 필요
    
    a > b일 때
    만약 맞다면 가로와 세로는 a + 2, b + 2
    */
    public int[] solution(int brown, int yellow) {
        // yellow를 두 수의 곱으로 분해하는 모든 경우 구하기
        int width = -1;
        int height = -1;
        for(int i = 1; i <= Math.sqrt(yellow); i++){
            // 갈색칸의 수가 가능한지 확인
            if(yellow % i == 0){
                int q = yellow / i;
                if(q * 2 + i * 2 == brown - 4){
                    width = (q > i) ? q + 2 : i + 2;
                    height = (q > i) ? i + 2 : q + 2; 
                    break;
                }
            }
        }
        // 답을 반환
        int[] answer = { width, height };
        return answer;
    }
}
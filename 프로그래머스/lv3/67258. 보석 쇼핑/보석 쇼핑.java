import java.util.*;

class Solution {
    /*
    처음에는 삼중 반복문? 시간 초과
    슬라이딩 윈도우?
    */
    public int[] solution(String[] gems) {
        // 전체 보석 종류 알아내기
        Set<String> kinds = new HashSet<>();
        for(String gem : gems){
            if(!kinds.contains(gem)){
                kinds.add(gem);
            }
        }
        
        HashMap<String, Integer> counter = new HashMap<String, Integer>();
        for(String kind : kinds){
            counter.put(kind, 0);
        }
        
         // 슬라이딩 윈도우
            // 처음에는 맨 왼쪽에 있는 N 크기의 윈도우
            // 모든 종류를 찾을 때까지 오른쪽으로 확장
            // 모든 종류가 존재하는 한 왼쪽을 제거
            // 최소 길이 후보 저장
            // 윈도우를 왼쪽으로 민다
                
        int[] answer = new int[2];
        answer[0] = 1;
        answer[1] = gems.length;
        
        int left = 0;
        int right = 0;
        int contains = 0;
            
        if(counter.get(gems[right]) == 0){
            contains++;
        }
        counter.put(gems[right], counter.get(gems[right]) + 1);  
        if(contains >= kinds.size()){
            // System.out.println("in : " + left + " " + right);
            if((answer[1] - answer[0]) > (right - left)){
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
        }
    
        while(true){
            // 왼쪽에서 모든 종류가 모일때까지 수집
            right++;
            if(right >= gems.length){
                break;
            }
            if(counter.get(gems[right]) == 0){
                contains++;
            }
            counter.put(gems[right], counter.get(gems[right]) + 1);  
            
            // System.out.println("current : " + gems[right] + ", contains :" + contains);
            
            while(counter.get(gems[left]) > 1){
                counter.put(gems[left], counter.get(gems[left]) - 1); 
                left++;
            }
            
             // 모든 종류가 모이면 값을 갱신하고 left 이동?
            if(contains >= kinds.size()){
                // System.out.println("in : " + left + " " + right);
                if((answer[1] - answer[0]) > (right - left)){
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }
            }
        }
        return answer;
    }
}
import java.util.*;

class Solution {
    /*
    26-7-20 17:16 ~ 
    
    필요한 순서에 따라 상자를 실을 때 최대 몇 개까지 실을 수 있는가?
    
    ---
    
    스택을 이용?
    
    */
    public int solution(int[] order) {
        int answer = 0;
        
        // order의 모든 원소에 대해서
            // 기존 컨베이어 벨트에서 꺼낼 수 있으면
                // answer 증가 후 continue
            
            // 스택에서 꺼낼 수 있으면 
                // answer 증가 후 continue
        
            
            // 컨베이어 벨트 위의 상자를 스택으로 차례로 이동
                
            // 상자를 발견했으면 answer 증가
            // 발견 못했으면 break
        
        Queue<Integer> qu = new ArrayDeque<>();
        Stack<Integer> st = new Stack<>();
        
        for(int i = 1; i <= order.length; i++){
            qu.offer(i);
        }
        
        int i = 0;
        while(i < order.length){
            int curBox = order[i];
            
            if(!qu.isEmpty() && curBox == qu.peek()){
                qu.poll();
                answer++;
                i++;
                continue;
            }
            
            if(!st.isEmpty() && curBox == st.peek()){
                st.pop();
                answer++;
                i++;
                continue;
            }
            
            if(qu.isEmpty()){
                break;
            }
            
            int front = qu.poll();
            st.push(front);
        }
        
        return answer;
    }
}
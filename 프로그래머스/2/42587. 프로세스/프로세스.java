import java.util.*;

class Solution {
    /*
    26/7/17 20:22 ~ 
    
    특정 프로세스가 몇번째에 스케줄링 되는지 구하자
    
    ---
    
    프로세스 수 <= 100
    
    ---
    
    시뮬레이션인가?
    N * N 정도의 시간복잡도이므로 가능
    
    현재 존재하는 프로세스의 우선순위를 최대 힙에 저장하면 
    우선순위가 더 높은 프로세스가 존재하는지 알 수 있음
    
    */
    private PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    private Queue<Process> qu = new ArrayDeque<>();
    
    public int solution(int[] priorities, int location) {
        
        // 모든 프로세스의 우선순위를 최대 힙에 저장
        for(int i = 0; i < priorities.length; i++){
            pq.offer(priorities[i]);
        }
        
        for(int i = 0; i < priorities.length; i++){
            qu.offer(new Process(priorities[i], i));
        }
                
        // 큐에서 우선순위가 제일 큰 것부터 차례로 뽑는다
            // location에 해당하는 프로세스가 나올 때 몇번째인지 구한다
        int answer = 1;
        
        while(!qu.isEmpty()){
            Process front = qu.poll();
            
            if(isHighest(front)){
                if(front.location == location){
                    break;
                }
                
                pq.poll();
                answer++;
            }
            
            qu.offer(front);
        }
        
        return answer;
    }
    
    private boolean isHighest(Process p){
        return pq.peek() == p.priority;
    }
    
    static class Process{
        int priority;
        int location;
        
        public Process(int priority, int location){
            this.priority = priority;
            this.location = location;
        }
    }
}
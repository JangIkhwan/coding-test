import java.util.*;

class Solution {
    /*
    26/3/26 16:08 ~ 
    
    N명의 멘토를 K개의 유형에 할당했을 때, 대기시간이 최솟값을 구하자
    
    완전탐색이 가능할까?
    N <= 20, 경우의 수는? K ^ (N - K)이다
    가능할 것 같은데?    
    
    멘토를 할당하는 조합마다 멘토에게 상담을 할당하며 총 누적 대기 시간을 구한다
    answer보다 누적 대기 시간이 작으면 교체한다.
    
    5 60 150 190 235 285  
    
      50 130 140 135
      
    시간초과 발생

    */
    
    private int K;
    private int answer = 0;
    private int[] persons;
    private List<int []> [] requests;
    
    public int solution(int k, int n, int[][] reqs) {
        K = k;
        answer = Integer.MAX_VALUE;
        
        persons = new int[k + 1];
        Arrays.fill(persons, 1);
        
        requests = new ArrayList[k + 1];
        for(int i = 1; i <= k; i++){
            requests[i] = new ArrayList<>();
        }
        for(int[] req : reqs){
            requests[req[2]].add(new int[] { req[0], req[1] });
        }
        
        assignMentors(1, n - k);
        // simulate();
        
        return answer;
    }
    
    private void assignMentors(int start, int depth){
        if(depth == 0){
            simulate();
            // System.out.println(Arrays.toString(persons));
            return;
        }
        
        for(int i = start; i <= K; i++){
            persons[i]++;
            assignMentors(i, depth - 1);
            persons[i]--;
        }
    }
    
    private void simulate(){   
        // 각 유형별로 고객을 하나씩 처리하면서 대시시간을 누적
        int waitingTime = 0;
        for(int kind = 1; kind <= K; kind++){
            if(requests[kind].isEmpty()){
                continue;
            }
            
            // 상담이 끝나는 시간을 기준으로 다음 고객을 처리해야 한다
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            
            int i = 0;
            while(i < persons[kind] && i < requests[kind].size()){
                int[] req = requests[kind].get(i);
                pq.offer(req[0] + req[1]);
                i++;
            }
            
            while(!pq.isEmpty()){
                int cur = pq.poll();
                if(i >= requests[kind].size()){
                    break;
                }
                
                int[] req = requests[kind].get(i);
                if(cur >= req[0]){
                    waitingTime += cur - req[0];
                    pq.offer(cur + req[1]);
                    i++;
                }
                else{
                    pq.offer(req[0]);
                }
            }
        }
        
        // answer보다 누적 대기 시간이 작으면 교체
        answer = Math.min(answer, waitingTime);
    }
}
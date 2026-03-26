import java.util.*;

class Solution {
    /*
    11/25 21:11 ~ 
    
    필요한 서버 증설 횟수를 구하자
    
    각 시점마다 서버의 개수와 서버의 남은 수명을 알고 있어야 함
    */
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Integer> servers = new LinkedList<>();
        for(int cur : players){
            int need = cur / m;
            int lack = need - servers.size();
            for(int i = 0; i < lack; i++){
                servers.offer(k);
                answer++;
            }
            
            int size = servers.size();
            for(int i = 0; i < size; i++){
                int life = servers.poll();
                life -= 1;
                if(life > 0){
                    servers.offer(life);
                }
            }
        }
        return answer;
    }
}
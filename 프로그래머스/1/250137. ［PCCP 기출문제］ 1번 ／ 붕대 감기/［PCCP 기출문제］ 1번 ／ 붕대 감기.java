class Solution {
    /*
    26/3/27 14:56 ~ 
    
    마지막 공격 이후에 캐릭터의 남은 체력을 출력한다.
    만약 체력이 0이하면 -1을 출력
    
    
    */
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        
        // 모든 공격에 대해서
            // 마지막 공격시간에서 흐른 시간을 계산해서 체력을 증가
            // 공격으로 체력을 감소
            // 마지막 공격시간을 현재 공격 시간으로 바꾼다.
        
        int lastAttackedTime = 0;
        for(int[] attack : attacks){
            int enlapsed = attack[0] - lastAttackedTime - 1;
            int healed = enlapsed * bandage[1] + (enlapsed / bandage[0]) * bandage[2];
            answer = Math.min(health, answer + healed);
            
            answer -= attack[1];
            if(answer <= 0){
                return -1;
            }
            
            lastAttackedTime = attack[0];
        }
        
        return answer;
    }
}
import java.util.*;

class Solution {
    /*
    26/5/7 14:01 ~ 
    
    당첨에서 제외해야할 아이디 목록의 모든 경우의 수를 구하자
    
    아아디 길이 <= L
    주어진 입력의 길이 <= 8 = N
    불량 사용자 아이디의 길이 <= 8 = M
    
    불량 사용자 아이디에는 같은 문자열이 들어갈 수 있다
    
    완전탐색으로 풀 수 있을까?
    불량사용자와 사용자 아이디를 매칭하는 조합을 계산하면 어떨까?
    
    경우의 수 P(N, M) = 8!
    
    O(N! * M * N * L)  
    시간 초과는 안일어날 것 같음
    
    같은 내용의 아이디 목록은 한번만 세야한다
    
    */
    private int answer = 0;
    private boolean[] selected;
    private boolean[] counted;
    
    public int solution(String[] user_id, String[] banned_id) {
        selected = new boolean[user_id.length];
        counted = new boolean[1 << 9];
                    
        countCases(banned_id.length, 0, 0, user_id, banned_id);
        
        return answer;
    }
    
    private void countCases(int depth, int prev, int state, String[] user_id, String[] banned_id){
        if(depth == 0){
            // System.out.println("state = " + state);
            if(!counted[state]){
                answer++;  
                counted[state] = true;
            }
            return;
        }
        
        // System.out.println("depth = " + depth);
        // System.out.println("selected = " + Arrays.toString(selected));
        
        for(int i = 0; i < user_id.length; i++){
            if(selected[i]){
                continue;
            }
            
            boolean matched = matched(user_id[i], banned_id[depth - 1]);
            // System.out.println("userId = " + user_id[i] + " bannedId = " + banned_id[depth - 1]);
            // System.out.println("matched = " + matched);
            if(matched){
                selected[i] = true;
                countCases(depth - 1, i, state | (1 << i), user_id, banned_id);
                selected[i] = false;
            }
        }
    }
    
    private boolean matched(String userId, String bannedId){
        if(userId.length() != bannedId.length()){
            return false;
        }
        for(int i = 0; i < userId.length(); i++){
            if(!(bannedId.charAt(i) == '*' || userId.charAt(i) == bannedId.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
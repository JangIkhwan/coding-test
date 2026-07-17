import java.util.*;

class Solution {
    /*
    26/7/17 20:03 ~. 
    
    원하는 물건을 모두 살 수 있는 회원가입 날짜의 수를 구하자
    
    ---
    
    슬라이딩 윈도우 유형으로 보인다
    
    */
    private Map<String, Integer> counter = new HashMap();
    private String[] want;
    private int[] number;
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        this.want = want;
        this.number = number;
        
        for(int i = 0; i < want.length; i++){
            counter.put(want[i], 0);
        }
        
        // 크기 10인 윈도우 생성
        for(int i = 0; i < 10; i++){
            int count = counter.getOrDefault(discount[i], 0);
            counter.put(discount[i], count + 1);
        }
        
        // 가능하면 1 증가
        if(canRegister()){
            answer++;
        }
        
        // 1칸씩 이동하면서 가능한지 확인
        for(int i = 10; i < discount.length; i++){
            int count = counter.getOrDefault(discount[i], 0);
            counter.put(discount[i], count + 1);
            
            count = counter.get(discount[i - 10]);
            counter.put(discount[i - 10], count - 1);
            
            if(canRegister()){
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean canRegister(){
        for(int i = 0; i < want.length; i++){
            if(counter.get(want[i]) < number[i]){
                return false;
            }
        }
        return true;
    }
}
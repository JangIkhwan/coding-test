import java.util.*;

class Solution {
    /*
    17:20 ~ 
    
    주문서에서 일부 주문을 제거했을 때 n번째 주문을 구하자
    
    ---
    
    getSpell(i) : i번째 주문을 리턴하는 함수
    가 있다면
    
    getSpell(n + removed)으로 n번째 주문을 구할 수 있다
    
    ---
    
    getSpell()을 만들 수 있을까?
    
    1 : a
    26 : z
    26^1 + 1 : aa
    26^2 : az
    2*26^1 + 1 : ba
    26^2 + 26 : zz
    26^2 + 26^1 + 1 : aaa
    26*26^2 + 26*26^1 + 26 : zzz
    
    0 : a
    25 : z
    26^1 + 0 : aa
    26^1 + 25 : az
    2*26^1 + 0 : ba
    26^2 + 25 : zz
    1 * 26^2 + 1 * 26^1 + 0 : aaa
    26*26^2 + 26*26^1 + 25 : zzz
    
    26진법과 유사하지만 0의 개념이 없다
    */
    
    public String solution(long n, String[] bans) {   
        int removed = 0;
        
        Set<Long> set = new HashSet<>();
        for(String b : bans){
            long i = getIndex(b);
            set.add(i);
            if(i <= n - 1){
                removed++;
            }
        }
        
        // System.out.println(set);
        
        long index = n - 1;
        for(int i = 0; i < removed; i++){
            index++;
            while(set.contains(index)){
                index++;
            }
        }
        
        // System.out.println(index);
       
        return getSpell(index);
    }
    
    private long getIndex(String spell){
        long index = 0;
        for(int i = 0; i < spell.length(); i++){
            if(i == spell.length() - 1){
                index += spell.charAt(i) - 'a';                
            }
            else{
                index += Math.pow(26, spell.length() - i - 1) * (spell.charAt(i) - 'a' + 1);
            }
        }
        return index;
    }
    
    private String getSpell(long index){
        if(index <= 0){
            return "a";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append((char) ('a' + index % 26));
        
        index = index - 26;
        while(index >= 0){
            index = index / 26;  
            sb.append((char) ('a' + index % 26));
            index = index - 26;
        }
        
        sb.reverse();
        return sb.toString();
    }
}
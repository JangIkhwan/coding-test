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
        String cur = getSpell(n - 1);
        String next = null;
        
        Arrays.sort(bans, (a, b) -> {
            if(a.length() < b.length()){
                return -1;
            } 
            if(a.length() == b.length()){
                return a.compareTo(b);
            } 
            return 1;
        });
        
        while(true){
            int removed = getRemoved(bans, cur);
            
            next = getSpell(n - 1 + removed);
            
            if(cur.equals(next)){
                break;
            }

            cur = next;
        }
        
        return next;
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
    
    private int getRemoved(String[] bans, String cur){
        int hi = bans.length;
        int lo = -1;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(isBeforeOrEquals(bans[mid], cur)){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }
        return lo + 1;
    }
    
    private boolean isBeforeOrEquals(String a, String b){
        return a.length() < b.length() || a.length() == b.length() && a.compareTo(b) <= 0;
    }
}
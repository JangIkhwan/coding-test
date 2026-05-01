class Solution {
    /*
    26/5/1 13:10 ~ 14:10
    
    정렬된 주문에서 일부 주분을 지웠을 때 N번째 주문을 구하자
    
    log N보다 작은 시간복잡도가 필요할 것 같다
    
    K번째 주문을 알 수 있다면 해결할 수 있음
    
    - bans에서 K번째 주문보다 앞에 나온 주문의 개수를 구하고
    다시 주문을 구하면 된다.
    
    원래 K번째 주문을 구할 수 있을까?
    
    1 -> 0 -> a
    26 -> 25 -> z
    26 + 1 -> 26 -> aa
    26 + 26 -> 26 + 25 -> az
    26*2 + 1 -> 26*2 -> ba
    26*2 + 26 -> 26*2 + 25 -> bz
    
    26*26 + 26 -> 26*26 + 25 -> zz
    26*26*1 + 26*1 + 1 ->  26*26*1 + 26*1 -> aaa
    
    
    aabaa -> 26 + 2*26*26 + 26*26*26 + 26*26*26*26 + 1

    */
    public String solution(long n, String[] bans) {     
        long lo = 0;
        long hi = 1000_000_000_000_000L + 1;
        while(lo + 1 < hi){
            long mid = (lo + hi) / 2;
            
            String original = findKthOrder(mid);
            // System.out.println("ori = " + original); // 
            
            long removed = countRemovedOrder(original, bans);
            // System.out.println("mid - removed = " + (mid - removed));
            
            if(mid - removed >= n){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        
        String answer = findKthOrder(hi);
        // System.out.println("answer = " + answer + " hi =" + hi);
        
        return answer;
    }
    
    private long countRemovedOrder(String original, String[] bans){
       long count = 0;
        
        for(String ban : bans){
            if(ban.length() < original.length() || ban.length() == original.length() && ban.compareTo(original) <= 0){
                count++;
            }
        }
        
        return count;
    }
    
    private String findKthOrder(long index){
        StringBuffer sb = new StringBuffer();
        
        while(index > 0){
            long rightMost = (index - 1) % 26;
            
            sb.append((char) ('a' + rightMost));
            
            index = (index - 1) / 26;
        }
        
        sb.reverse();
        return sb.toString();
    }
}
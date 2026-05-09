import java.util.*;

class Solution {
    /*
    26/5/9 13:10 ~ 
    
    a의 부분 수열 중에서 가장 길이가 긴 스타수열의 길이를 구하자
    스타수열이 없으면 0을 리턴하자
    
    1 <= a의 길이 = N <= 50만
    
    길이 1 엣지케이스 주의
    
    완전탐색은 당연히 안될 것임
    
    되도록 N log N의 시간복잡도로 맞추어야 함
    
    이분탐색을 이용할 수 있을까?
    매번 그리디하게 스타수열을 만들 수 있는지 판정할 수 있으면 좋은데
    
    dp를 이용할 수는 없을까?
    (교집합, a에서 시작점) => dp 배열로 인해서 메모리초과나기 좋다
    
    0 1 0 1 0
    0 1 1 1 0
    
    */       
    public int solution(int[] a) {
        int answer = 0;
        
        if(a.length <= 1){
            return 0;
        }
        
        int[] cnt = new int[a.length + 1];
        for(int i = 0; i < a.length; i++){
           cnt[a[i]] += 1;
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < a.length; i++){
            set.add(a[i]);    
        }
        
        for(int e : set){
            if(cnt[e] * 2 <= answer){
                continue;
            }
            int maxLength = findMaxStarSeqLen(e, a);
            answer = Math.max(answer, maxLength);
        }
        
        return answer;
    }
    
    private int findMaxStarSeqLen(int intersect, int[] a){
        int length = 0;
        int i = 0;
        while(i + 1 < a.length){
            if(a[i] != a[i + 1] && (a[i] == intersect || a[i + 1] == intersect)){
                length += 2;
                i += 2;
            }
            else{
                i += 1;  
            }
        }
        return length;
    }
}
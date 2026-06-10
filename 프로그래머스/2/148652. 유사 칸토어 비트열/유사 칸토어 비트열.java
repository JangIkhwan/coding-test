class Solution {
    /*
    26/6/10 13:41 ~ 
    
    n번째 유사 칸토어 비트열에서 [l, r] 구간에 존재하는 1의 개수를 구하자
    
    ---
    
    유사칸토어비트열 나열
    a0 = 1 
    
    a1 = 11011 
    
    a2 = 11011 | 11011 | 00000 | 11011 | 11011 = a1 | a1 | 00000 | a1 | a1
    
    a3 = a2 | a2 | 00000 | 00000 | 00000 | 00000 | 00000 | a2 | a2
    
    ...
    
    ---
    
    문자열을 일일이 만들면 시간복잡도는?
    1 + 5 + 5^2 + .. + 5^n 은 5^n에 비례 
    어렵다
    
    --- 
    
    n번째 단계에서 [l, r] 사이에 1이 몇개인지 알 수 있나?
    
    a_n = a_n-1 | a_n-1 | 0 * 5^(n-1) | a_n-1 | a_n-1
    
    분할정복인가? 깊이가 깊어봐야 20인데
    
    */
    public int solution(int n, long l, long r) {
        int count = countOnes(n, l, r);
        
        return count;
    }
    
    private int countOnes(int n, long l, long r){
        if(n == 0){
            return 1;
        }
        
        // 5등분
        int sum = 0;
        long subStrLength = (long) Math.pow(5, n - 1);
        
        for(int i = 1; i <= 5; i++){
            long left = subStrLength * (i - 1) + 1;
            long right = subStrLength * i;
            
            if(left < l){
                left = l;
            }
            if(right > r){
                right = r;
            }
            
            if(right < l || left > r || i == 3){
                continue;
            }
            
            sum += countOnes(n - 1, left - subStrLength * (i - 1), right - subStrLength * (i - 1));
        
        }
        
        // System.out.println("n = " + n + ", l = " + l + ", r = " + r + ", sum = " + sum);
        
        return sum;
    }
}
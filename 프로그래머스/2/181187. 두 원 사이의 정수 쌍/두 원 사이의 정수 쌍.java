class Solution {
    /*
    
    26/3/25 17:29 ~ 
    
    두 원 사이에 좌표가 정수인 점의 개수를 구하자
    원위에 있는 것도 포함한다
    
    가장 큰 원의 반지름이 N일 때 
    N * N 시간 복잡도는 시간초과가 발생한다
    
    N log N 정도면 좋을 것 같은데
    이진 탐색은 어떨까? 
    x좌표마다 윗 부분과 아랫부분을 대상으로 이분 탐색을 수행하면 개수를 알 수 있음
    
    x축에서 구간을 3부분으로 나눠야할 듯
    작은원 왼쪽 : 큰 원에 들어가는 좌표 개수 구하기
    작은원 내부 : 위 아래 좌표 수 구하기
    작은원 오른쪽 : 작은원과 동일
    */
    public long solution(int r1, int r2) {
        long R2 = r2;
        long R1 = r1;
        long answer = 0;
        
        for(long x = -r2; x <= r2; x++){
            if(x <= -r1 || x >= r1){
                answer += 1;
                
                long lo = 0;
                long hi = r2 + 1;
                while(lo + 1 < hi){
                    long mid = (lo + hi) / 2;
                    if(x * x + mid * mid <= R2 * R2){
                        lo = mid;
                    }
                    else{
                        hi = mid;
                    }
                }
                
                if(lo > 0){
                    answer += lo * 2;
                }
            }
            else {
                long lo = 0;
                long hi = r2 + 1;
                while(lo + 1 < hi){
                    long mid = (lo + hi) / 2;
                    if(x * x + mid * mid <= R2 * R2){
                        lo = mid;
                    }
                    else{
                        hi = mid;
                    }
                }
                long upper = lo;
                
                lo = 0;
                hi = r2 + 1;
                while(lo + 1 < hi){
                    long mid = (lo + hi) / 2;
                    if(x * x + mid * mid >= R1 * R1){
                        hi = mid;
                    }
                    else{
                        lo = mid;
                    }
                }
                long lower = hi;
                
                answer += (upper - lower + 1) * 2;
            }
        }
        
        return answer;
    }
}
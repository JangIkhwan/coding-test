import java.util.*;

class Solution {
    /*
    26/5/6 15:52 ~ 
    
    징검다리를 건널 수 있는 친구가 최대 몇명인지 구하자
    
    배열의 길이는 최대 20만
    
    돌을 밟을 수 있는 횟수는 최대 2억
    
    시뮬레이션은 아닌 것 같음

    stones에서 0이 아닌 수 중에서 가장 작은 값을 차례로 찾아서 돌을 무너뜨리면 
    구할 수 있지 않을까?
    근데 1 ~ 20만이 저장된 stones라면 
    20만 * 20만의 시간이 걸린다
    이 방법은 아니다.
    
    
    
    */
    public int solution(int[] stones, int k) {
        int lo = 0;
        int hi = 200000001;
        
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(canCrossUpTo(stones, mid, k)){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }
        
        return lo + 1;
    }
    
    private boolean canCrossUpTo(int[] stones, int people, int k){
        int zeroCount = 0;
        
        int[] copy = stones.clone();
        for(int i = 0; i < copy.length; i++){
            copy[i] = Math.max(copy[i] - people, 0);
        }
        
        // System.out.println(Arrays.toString(copy));
        
        for(int i = 0; i < k; i++){
            if(copy[i] == 0){
                zeroCount++;
            }
        }
        if(zeroCount >= k){
            return false;
        }
        for(int i = k; i < stones.length; i++){
            if(copy[i - k] == 0){
                zeroCount--;
            }
            if(copy[i] == 0){
                zeroCount++;
            }
            if(zeroCount >= k){
                return false;
            }
        }
        return true;
    }
}
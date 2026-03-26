import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        // 이진 탐색으로 난이도의 최솟값을 구한다
        
        int lo = 0;
        int hi = -1;
        for(int i = 0; i < diffs.length; i++){
            if(hi < diffs[i]){
                hi = diffs[i];
            }
        }
        while(lo+ 1 < hi){
            int mid = (lo + hi) / 2;
            if(possible(mid, diffs, times, limit)){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        return hi;
    }
    
    private boolean possible(int level, int[] diffs, int[] times, long limit){
        long totalTime = 0;
        for(int i = 0; i < diffs.length; i++){
            if(level >= diffs[i]){
                totalTime += times[i];
            }
            else{
                totalTime += times[i] * (diffs[i] - level + 1);
                if(i > 0) totalTime += times[i - 1] * (diffs[i] - level);
            }
        }
        return totalTime <= limit;
    }
}
import java.util.*;
import java.util.stream.*;

class Solution {
    /*
    25/10/6 15:44 ~ 15:53
    16:33 ~ 18:00
    
    광고를 삽입할 가장 빠른 위치를 구하자
    
    로그는 30만개 이하다!
    
    로그 크기를 N이라 할 때 복잡도는 높아봐야 N log N
    
    일단은 선형탐색을 써보자
    
    25/10/9 17:41 ~ 18:00, 19:07 ~ 
    
    힌트를 보니 각 초에서 몇 개의 동영상이 있는지를 저장한 후에,
    누적 시간을 계산해보라고 한다
    
    이제보니 문제에서도 이러한 힌트를 주는듯 하다. 
    겹치는 동영상의 개수를 기준으로 구간을 쪼개고 있다.
    
    그렇다면 사전에 모든 재생 시간에서 동영상의 개수를 구한 후에
    슬라이딩 윈도우로 누적 재싱 시간의 최댓값을 구하면 어떨까?
    
    그런데 시간복잡도는? 
    전체 동영상 재생 시간을 M이라하면, N + M 이다
    시간의 최대 값은 99시 59분 59초이니 100 * 60 * 60 초를 벗어나지 못한다.
    따라서 시간 초과는 없을 듯?
    */
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = timeToSec(play_time);
        // 로그를 토대로 전체 영상 시간 중에서 각 초에 겹치는 동영상이 몇개 늘어나거나 줄어드는지 저장
        int[] logDelta = new int[playSec + 1];
        for(String log : logs){
            String s = log.split("-")[0];
            String e = log.split("-")[1];
            int start = timeToSec(s);
            int end = timeToSec(e);
            logDelta[start] += 1;
            logDelta[end] -= 1;
        }
        
        int[] overlapCount = new int[playSec + 1];
        int curOverlap = 0;
        for(int i = 0; i <= playSec; i++){
            if(logDelta[i] != 0){
                curOverlap += logDelta[i];
            }
            overlapCount[i] = curOverlap;
        }
        
        // 슬라이딩 윈도우로 누적 시간의 최댓값을 계산한다. 이때 최댓값을 찾을 때 시작 시간도 저장한다.
        String answer = "00:00:00";
        int adSec = timeToSec(adv_time);
        long maxSum = 0;
        long sum = 0;
        for(int i = 0; i < adSec; i++){
            sum += overlapCount[i];
        }
        maxSum = sum;
        for(int i = 1; i <= playSec - adSec; i++){
            sum += overlapCount[i + adSec - 1];
            sum -= overlapCount[i - 1];
            if(sum > maxSum){
                maxSum = sum;
                answer = secToTime(i);
                // if(i >= 60 * 60 + 30 * 60)
                //     System.out.println("maxSum, answer = " + secToTime(maxSum) + ", " + answer);
            }
        }
        
        // 시작 시간을 반환한다.
        return answer;
    }
    
    private int timeToSec(String time){
        int ret = 0;
        String hh = time.split(":")[0];
        String mm = time.split(":")[1];
        String ss = time.split(":")[2];
        ret += Integer.parseInt(hh) * 60 * 60;
        ret += Integer.parseInt(mm) * 60;
        ret += Integer.parseInt(ss);
        return ret;
    }
    
    private String secToTime(int sec){
        String hh = String.valueOf((int) (sec / (60 * 60)));
        if(hh.length() < 2){
            hh = "0" + hh;
        }
        sec = sec % (60 * 60);
        String mm = String.valueOf((int) (sec / 60));
        if(mm.length() < 2){
            mm = "0" + mm;
        }
        sec = sec % 60;
        String ss = String.valueOf(sec);
        if(ss.length() < 2){
            ss = "0" + ss;
        }
        return hh + ":" + mm + ":" + ss;
    }
    
}
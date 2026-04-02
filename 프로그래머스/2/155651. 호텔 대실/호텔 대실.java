import java.util.*;

class Solution {
    /*
    26/4/2 23:19 ~ 
    
    예약한 손님을 받기 위해서 필요한 최소 객실의 수를 리턴하라
    
    최소 객실은 어떻게 구할까?
    현재 손님을 넣을 방이 없으면 늘리고, 있으면 해당 방에 넣는다
    이것이 최적임.
    
    */
    public int solution(String[][] book_time) {
        // 예약 시간을 분단위로 변환
        int[][] bookTimes = new int[book_time.length][2];
        for(int i = 0; i < book_time.length; i++){
            bookTimes[i][0] = timeToMinute(book_time[i][0]);
            bookTimes[i][1] = timeToMinute(book_time[i][1]);
        }
        
        // 예약 내용을 시작 시간으로 정렬
        Arrays.sort(bookTimes, (a, b) -> a[0] - b[0]);
        
        // for(int[] b : bookTimes){
        //     System.out.println(Arrays.toString(b));
        // }
        
        // 각 예약 내용에 대해서
            // 빈 방이 있으면 빈 방에 배정
            // 빈 방이 없으면 방 개수를 늘리고 그 방에 배정
        int answer = 1;
        List<Integer> rooms = new ArrayList<>();
        rooms.add(0);
        for(int[] bookTime : bookTimes){
            boolean assigned = false;
            for(int i = 0; i < rooms.size(); i++){
                if(rooms.get(i) <= bookTime[0]){
                    rooms.remove(rooms.get(i));
                    rooms.add(bookTime[1] + 10);
                    assigned = true;
                    break;
                }
            }
            if(!assigned){
                rooms.add(bookTime[1] + 10);
                answer++;
            }
        }
        
        // 방 개수 리턴
        return answer;
    }
    
    private int timeToMinute(String time){
        String[] tokens = time.split(":");
        int hh = Integer.parseInt(tokens[0]);
        int mm = Integer.parseInt(tokens[1]);
        return hh * 60 + mm;
    }
}
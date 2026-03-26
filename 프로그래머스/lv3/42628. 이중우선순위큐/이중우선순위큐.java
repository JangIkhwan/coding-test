import java.util.*;

class Solution {
    /*
    26/1/8 23:05 ~ 
    
    최댓값과 최솟값의 쌍을 반환한다
    */
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        for(String op : operations){
            String[] tokens = op.split(" ");
            if(tokens[0].equals("I")){
                int num = Integer.parseInt(tokens[1]);
                maxHeap.offer(num);
                minHeap.offer(num);
                i++;
            }
            if(tokens[0].equals("D")){
                if(maxHeap.isEmpty()){
                    continue;
                }
                if(tokens[1].equals("1")){
                    int poll = maxHeap.poll();
                    minHeap.remove(poll);
                }
                else{
                    int poll = minHeap.poll();
                    maxHeap.remove(poll);
                }
            }
        }
        if(!maxHeap.isEmpty()) {
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }
        return answer;
    }
    /*
    123 -5643
    16 1234
    */
}
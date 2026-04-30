class Solution {
    /*
    26/4/30 14:12 ~ 
    
    합이 k가 되는 가장 짧은 구간의 시작인덱스와 끝인덱스를 구하자
    
    수열은 단조증가
    
    투포인터를 이용할 수 있어 보인다
    
    
    */
    public int[] solution(int[] sequence, int k) {
        int[] answer = { 0, sequence.length - 1 };
        
        int left = 0;
        int right = 1;
        int sum = sequence[0];
        while(true){            
            if(sum == k){
                if(right - left - 1 < answer[1] - answer[0]){
                    answer[0] = left;
                    answer[1] = right - 1;
                }
                sum -= sequence[left];
                left++;
            }
            else if(sum > k){
                sum -= sequence[left];
                left++;
            }
            else{
                if(right == sequence.length){
                    break;
                }
                sum += sequence[right];
                right++;
            }
        }
        
        return answer;
    }
}
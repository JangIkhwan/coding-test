class Solution {
    /*
    26/5/28 15:43 ~ 16:33 : 풀이 방법이 떠올리기 어렵다
    
    s와 e 사이에 수 중에서 억억단 행렬에서 가장 많이 등장하는 수 중 가장 작은 수를 구하자
    
    시간복잡도는 e log e 또는 N log N 정도여야 함. N은 starts의 길이
    
    각 숫자의 빈도를 e log e 정도의 복잡도로 계산하지?
    
    *새로 알게된 점* 
    i의 배수에게 +1씩 하는 방법을 사용할 수 있다!
    이렇게 하면 복잡도가 e log e 정도가 된다.
    
    */
    
    public int[] solution(int e, int[] starts) {
        // 1 ~ e의 빈도를 구한다
        int[] freqs = new int[e + 1];
        for(int i = 1; i <= e; i++){
            for(int j = 1; i * j <= e; j++){
                freqs[i * j] += 1;
            }
        }
        
        // i ~ e 구간에서 빈도가 가장 크고 절댓값이 작은 수를 구한다
        int[] frequentNums = new int[e + 1];
        frequentNums[e] = e;
        for(int i = e - 1; i >= 1; i--){
            if(freqs[i] >= freqs[frequentNums[i + 1]]){
                frequentNums[i] = i;
                continue;
            }
            frequentNums[i] = frequentNums[i + 1];
        }
        
        // starts에 대한 답을 구한다
        int[] answer = new int[starts.length];
        for(int i = 0; i < starts.length; i++){
            answer[i] = frequentNums[starts[i]];
        }
        
        return answer;
    }
}
class Solution {
    /*
    균형을 우리는 짝꿍의 수를 찾는다
    
    ---
    
    weights의 길이 <= 10만
    weights[i]의 범위는 100 ~ 1000
    
    ---
    
    원소는 중복될 수 있다
    원소 값의 범위는 작다
    => 중복값이 많을 수 있다
    
    무게가 작은 것부터 무게가 큰 것과 비교하면 짝꿍이 중복되지 않는다
    배치할 수 있는 경우의 수도 3개로 줄어든다
    
    무게 a,b가 a < b이고, a와 b는 균형을 이루는 경우
    무게가 a인 사람과 짝꿍이 될 수 있는 사람의 수 = sum( count(a) * count(b) )이다
    
    */
    public long solution(int[] weights) {
        long[] counter = new long[3001];
        for(int w : weights){
            counter[w]++;
        }
        
        long answer = 0;
        for(int weight = 0; weight <= 1000; weight++){
            if(counter[weight] <= 0){
                continue;
            }
            
            answer += counter[weight] * (counter[weight] - 1) / 2;
            answer += counter[weight] * counter[weight * 2];
            if(weight % 3 == 0){
                answer += counter[weight] * counter[(weight / 3) * 2];
            }
            if(weight % 4 == 0){
                answer += counter[weight] * counter[(weight / 4) * 3];
            }
        }
        
        return answer;
    }
}
class Solution {
    /*
    26/5/19 14:33 ~ 
    
    연속펄스부분수열의 합의 최대값을 구하자
    
    수열의 길이 <= 50만
    
    시간복잡도는 N log N 이하여야 
    
    전체 수열에 펄스수열을 곱한 후에 연속부분수열의 합 중에 큰 것을 구해도된다
    
    음수와 양수가 공존할 때 연속부분수열의 합의 최댓값을 N log N에 구할 수 있나?
    가장 최근 연속된 수의 합을 최대로 유지하면 된다
    
    
    2 -3 -6 -1 3 1 2 -4
    2 -1 -6 -1 3 4 9 5
    
    -2 3 6 1 -3 -1 -2 4
    -2 3 9 10 7 6 4 8
    
    */
    public long solution(int[] sequence) {
        int[] first = sequence.clone();
        int[] second = sequence.clone();
        
        int firstSign = 1;
        int secondSign = -1;
        for(int i = 0; i < sequence.length; i++){
            first[i] = firstSign * first[i];
            second[i] = secondSign * second[i];
            firstSign *= -1;
            secondSign *= -1;
        }
        
        return Math.max(findMaxSeqSum(first), findMaxSeqSum(second));
    }
    
    private long findMaxSeqSum(int[] seq){
        long result = 0;
        long sum = 0;
        
        for(int i = 0; i < seq.length; i++){
            if(sum >= 0 && seq[i] >= 0){
                sum += seq[i];
            }
            if(sum < 0 && seq[i] >= 0){
                sum = seq[i];
            }
            if(sum >= 0 && seq[i] < 0){
                sum += seq[i];
            }
            if(sum < 0 && seq[i] < 0){
                sum = seq[i];
            }
            
            result = Math.max(result, sum);
        }
        
        return result;
    }
}
class Solution {
    /*
    26/6/9 14:40 ~ 
    
    규칙에 따라서 풍선을 터뜨렸을 때 마지막까지 남을 수 있는 풍선의 개수를 구하자
    
    임의로 인접한 두 풍선 중 하나를 터뜨려야하는데
    이때 숫자가 작은 풍선은 한번만 터뜨릴 수 있다.
    
    어떤 풍선은 마지막까지 남길 수 없을 수 있다
    
    ---
    
    a의 길이 <= 100만 = N
    원소의 절댓값 <= 10억
    
    알고리즘은 N log N 이하의 시간 복잡도여야 함
    
    어떤 경우에 풍선을 남길 수 없는걸까?
    
    ---
    
    a[x]는 마지막에 남을 수 있는가를 판정하는 방법은?
    
    ... a[x] ...
    
    왼쪽에서 남은 수와 오른쪽에 남은 수를 가지고 a[x]가 남을 수 있는지 따져야 한다
    
    1. a[x] < 왼쪽의 마지막 && a[x] < 오른쪽 마지막
    단, 이때는 왼쪽이나 오른쪽에서 필요하면 작은 것을 한번 취한다
    
    2. a[x] > 왼쪽의 마지막 && a[x] < 오른쪽의 마지막
    
    3. a[x] > 오른쪽의 마지막 && a[x] < 왼쪽의 마지막
    
    이 조건 중 하나만 맞으면 true다.
    
    ---
   
    어떤 구간에서 마지막에 남는 값은 어떻게 알지?
    
    관찰 결과 큰 것만 제거하면 해당 구간에서 최솟값이 마지막에 남을 수 있다
    
    
    중요한 관찰 :
    
    양쪽에 현재 풍선보다 작은 값이 존재하는 경우에는 현재 풍선이 지워질 수 밖에 없다!!
    
    */
    public int solution(int[] a) {
        if(a.length < 3){
            return a.length;
        }
      
        int[] leftMin = new int[a.length];
        
        leftMin[0] = Integer.MAX_VALUE;
        for(int i = 1; i < a.length; i++){
            leftMin[i] = Math.min(a[i - 1], leftMin[i - 1]);
        }
        
        int[] rightMin = new int[a.length];
        
        rightMin[a.length - 1] = Integer.MAX_VALUE;
        for(int i = a.length - 2; i >= 0; i--){
            rightMin[i] = Math.min(a[i + 1], rightMin[i + 1]);
        }
        
        int answer = 0;
        
        for(int i = 0; i < a.length; i++){
            if(leftMin[i] >= a[i] || rightMin[i] >= a[i]){
                answer++;
            }
        }
        
        return answer;
    }
}
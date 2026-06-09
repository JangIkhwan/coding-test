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
    
    a[i...j]에서 남길 수 있는 최댓값과 최솟값은 어떻게 구하나?
    
    최솟값은 해당 구간에서 최솟값을 구하면 되어서 쉬움
    
    최댓값은? 두번째로 가장 작은 값이 최댓값이 된다
    
    ---
    
    dp[i][j][0] : i ~ j 구간에서 가장 작은 값
    dp[i][j][1] : i ~ j 구간에서 두번째로 작은 값
    
    */
    public int solution(int[] a) {
        if(a.length < 3){
            return a.length;
        }
        
        // dp를 계산한다
        int[][] left = new int[a.length][2];
        
        int min = a[0];
        int secondMin = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] < min){
                secondMin = min;
                min = a[i];
            }
            else{
                if(a[i] < secondMin){
                    secondMin = a[i];
                }
            }
            
            left[i][0] = min;
            left[i][1] = secondMin;
        }
        
        int[][] right = new int[a.length][2];

        min = a[a.length - 1];
        secondMin = a[a.length - 1];
        for(int i = a.length - 1; i >= 0; i--){
            if(a[i] < min){
                secondMin = min;
                min = a[i];
            }
            else{
                if(a[i] < secondMin){
                    secondMin = a[i];
                }
            }
            
            right[i][0] = min;
            right[i][1] = secondMin;
        }
        
//         for(int i = 0; i < a.length; i++){
//             System.out.print(left[i][0] + " ");
//         }
//         System.out.println();
        
//         for(int i = 0; i < a.length; i++){
//             System.out.print(left[i][1] + " ");
//         }
//         System.out.println();
        
//         for(int i = 0; i < a.length; i++){
//             System.out.print(right[i][0] + " ");
//         }
//         System.out.println();
        
//         for(int i = 0; i < a.length; i++){
//             System.out.print(right[i][1] + " ");
//         }
//         System.out.println();
        
        // dp를 이용해서 각 원소가 마지막까지 남을 수 있는지 판정한다
        int answer = 0;
        
        if(a[0] < right[1][0] || a[0] > right[1][0]){
            // System.out.println(a[0] + " can be last");
            answer++;
        }
        
        for(int i = 1; i < a.length - 1; i++){
            if(canBeLast(a[i], left[i - 1], right[i + 1])){
                // System.out.println(a[i] + " can be last"); 
                answer++;
            }
        }
        
        if(a[a.length - 1] < left[a.length - 2][0] || a[a.length - 1] > left[a.length - 2][0]){
            // System.out.println(a[a.length - 1] + " can be last");
            answer++;
        }
        
        return answer;
    }
    
    private boolean canBeLast(int value, int[] left, int[] right){
        if(value < left[0] && value < right[0]){
            return true;
        }
        if(value > left[0] && value < right[0]){
            return true;
        }
        if(value < left[0] && value > right[0]){
            return true;
        }
        if(value < left[1] && value < right[0]){
            return true;
        }
        if(value < left[0] && value < right[1]){
            return true;
        }
        
        return false;
    }
}
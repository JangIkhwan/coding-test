class Solution {
    /*
    26/6/17 14:52 ~ 
    
    초기상태에서 목표상태로 만들기 위해서 최소 몇번의 뒤집기를 수행하는지 구하자
    목표상태로 만들 수 없다면 -1을 리턴한다
    
    ---
    
    탐색공간을 탐색하면 되지 않을까?
    최소의 뒤집기 횟수를 찾으려면 BFS를 수행해야 하는데 근데 메모리 초과가 발생하지 않을까?
    
    N x N 크기일 때, 매 상태에서 2N개의 상태가 생김 
    공간의 개수 = 2N ^ depth. 너무 빠르게 커지는데
    
    그런데 매번 새로운 선을 선택한다면?
    (2N)!에 비례하는 경우의수가 생김
    
    
    --- 
    
    특이한 건 목표 상태를 만들지 못하는 경우를 알 수 있는 문제라는 점
    
    초기상태가 아래와 같을 때
    000
    000
    000
    
    다음은 만들 수 없음
    101
    000
    000
    
    다음은 만들 수 있음
    111
    000
    000
    
    101
    010
    010
    
    규칙이 있나?
    
    ---
    
    oxoxx
    xoxoo
    oxoxx
    xoxoo
    oxoxx
    
    x는 홀수번, o는 짝수번 선택되는 경우가 존재해야 목표 상태를 만들 수 있다
    선택하는 선의 순서는 결과와 상관이 없다
    같은 선을 두번 선택하는 것은 의미 없는 일이다
    
    ---
    
    시간초과가 발생한다.
    비트마스킹으로 바꾸면 어떨까?
    
    ---
    
    이 문제에서는 행과 열을 선택하는 순서는 고려하지 않고 조합만 고려해야한다.
    그래서 행을 선태하는 경우마다 열을 선택하는 경우를 고려하는 것이 더 효율적이다.
    시간복잡도도 2^N * 2^M * N*M 인데, N, M <= 10이므로 시간초과 우려는 없어 보인다
    
    */
    
    private int answer = Integer.MAX_VALUE;
    private int[][] beginState;
    private int[][] targetState;
    
    public int solution(int[][] beginning, int[][] target) {
        beginState = beginning;
        targetState = target;
        
        findMinFlipNumber();
        
        if(answer == Integer.MAX_VALUE){
            return -1;
        }

        return answer;
    }
    
    private void findMinFlipNumber(){
        for(int selectedRow = 0; selectedRow <= (1 << beginState.length); selectedRow++){
            for(int selectedCol = 0; selectedCol <= (1 << beginState[0].length); selectedCol++){
                int flipCount = 0;
                
                for(int i = 0; i < beginState.length; i++){
                    if(((selectedRow >> i) & 1) == 1){
                        flipCount++;
                    }
                }
                
                for(int i = 0; i < beginState[0].length; i++){
                    if(((selectedCol >> i) & 1) == 1){
                        flipCount++;
                    }
                }
                
                if(checkCurrentIsTarget(selectedRow, selectedCol)){
                    answer = Math.min(answer, flipCount);
                }
            }
        }
    }
    
    private boolean checkCurrentIsTarget(int selectedRow, int selectedCol){
        for(int r = 0; r < beginState.length; r++){
            for(int c = 0; c < beginState[0].length; c++){
                int current = beginState[r][c];
                
                if(((selectedRow >> r) & 1) == 1){
                    current = (current == 0) ? 1 : 0;
                }
                
                if(((selectedCol >> c) & 1) == 1){
                    current = (current == 0) ? 1 : 0;
                }
                
                if(current != targetState[r][c]){
                    return false;
                }
            }
        }
        return true;
    }
}
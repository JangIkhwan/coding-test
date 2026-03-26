class Solution {
    /*
    26/3/18 23:00 ~ 
    
    틱택토에서 나올 수 있는 상황이면 1을 반환하는 함수를 작성한다
    
    O의 개수 - X의 개수는 0이상 1이하다
    가로 세로 대각선에 O가 일렬로 3개 있는데, X가 일렬로 3개 있으면 안된다
    X가 이겼는데 O의 개수가 더 많을 수는 없다
    
    */
    public int solution(String[] board) {        
        int firstCount = 0;
        int secondCount = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i].charAt(j) == 'O'){
                    firstCount++;
                }
                if(board[i].charAt(j) == 'X'){
                    secondCount++;
                }
            }
        }
        if(!(firstCount - secondCount <= 1 && firstCount - secondCount >= 0)){
            return 0;
        }
        
        boolean firstWin = checkWin(board, 'O');   
        boolean secondWin = checkWin(board, 'X');
        
        if(!firstWin && secondWin && firstCount > secondCount){
            return 0;
        }
        
        if(firstWin && firstCount == secondCount){
            return 0;
        }
        
        if(firstWin && secondWin){
            return 0;
        }
    
        return 1;
    }
           
    private boolean checkWin(String[] board, char mark){
        for(int row = 0; row < 3; row++){
            if(board[row].charAt(0) == mark && board[row].charAt(0) == board[row].charAt(1) && board[row].charAt(1) == board[row].charAt(2)){
                return true;
            }
        }
        
        for(int col = 0; col < 3; col++){
            if(board[0].charAt(col) == mark &&  board[0].charAt(col) == board[1].charAt(col) && board[1].charAt(col) == board[2].charAt(col)){
                return true;
            }
        }
        
        if(board[0].charAt(0) == mark && board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
            return true;
        }
        
        
        if(board[0].charAt(2) == mark && board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
            return true;
        }
        
        return false;
    }
}
class Solution {
    /*
    26/3/27 15:14 ~ 
    
    목적을 최대한으로 달성했을 때 가입자 수와 매출액을 구하자
    서비스 가입자 최대화, 매출액 최대화 순으로 중요
    
    이모티콘마다 할인율은 4개 중에 고르면 된다.
    
    완전탐색으로 가능할까?
    m <= 7, 4^m의 경우의 수, 최대 2^14개 
    완탐도 괜찮을 듯?
    
    */
    
    private int maxUserCount = 0;
    private int maxProfit = 0;
    private int[] rates = { 10, 20, 30, 40 };
    private int[] emoticonRates;
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 이모티콘의 할인율을 적용하는 모든 경우의수를 구한다
        // 각 경우마다 가입 유저 수와 매출액을 구한다
        // 가입유저수이 큰 것, 같으면 매출수가 큰 것을 저장한다.
        
        emoticonRates = new int[emoticons.length];
        
        assignDiscountRate(emoticons.length, users, emoticons);
        
        // 답을 출력한다
        return new int [] { maxUserCount, maxProfit };
    }
    
    private void assignDiscountRate(int depth, int[][] users, int[] emoticons){
        if(depth == 0){
            // 최댓값 찾기
            int userCount = 0;
            int profit = 0;
            
            for(int[] user : users){
                int pay = 0;
                for(int i = 0; i < emoticons.length; i++){
                    if(emoticonRates[i] >= user[0]){
                        pay += emoticons[i] * (100 - emoticonRates[i]) / 100;
                    }
                }

                if(pay < user[1]){
                    profit += pay;
                }
                else{
                    userCount++;
                }
            }
            
            if(userCount > maxUserCount){
                maxUserCount = userCount;
                maxProfit = profit;
            }
            else if(userCount == maxUserCount){
                maxProfit = Math.max(maxProfit, profit);
            }
            
            return;
        }
        
        for(int i = 0; i < 4; i++){
            emoticonRates[depth - 1] = rates[i];
            assignDiscountRate(depth - 1, users, emoticons);
        }
    }
}
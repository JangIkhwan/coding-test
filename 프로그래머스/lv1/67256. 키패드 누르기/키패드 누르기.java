class Solution {
    int[] buttonX = {1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2};
    int[] buttonY = {3, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3};
    
    public String solution(int[] numbers, String hand) {
        int left = 10;
        int right = 11;
        StringBuilder sb = new StringBuilder();
        for(int number : numbers){
            if(number == 1 || number == 4 || number == 7){
                left = number;
                sb.append("L");
                continue;
            }
            if(number == 3 || number == 6 || number == 9){
                right = number;
                sb.append("R");
                continue;
            }
            int leftDist = dist(number, left);
            int rightDist = dist(number, right);
            if(leftDist == rightDist){
                if(hand.equals("left")){
                    sb.append("L"); 
                    left = number;
                }
                else{
                    sb.append("R");
                    right = number;
                }
                continue;
            }
            if(leftDist < rightDist){
                sb.append("L");
                left = number;
                continue;
            }
            sb.append("R");
            right = number;
        }    
        return sb.toString();
    }
    
    private int dist(int dst, int src){
        int srcX = buttonX[src];
        int srcY = buttonY[src];
        int dstX = buttonX[dst];
        int dstY = buttonY[dst];
        return Math.abs(srcX - dstX) + Math.abs(srcY - dstY);
    }
}
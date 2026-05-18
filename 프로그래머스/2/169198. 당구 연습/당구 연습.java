class Solution {
    /*
    26/5/18 16:50 ~ 
    
    원쿠션으로 ball들을 맞출 때 친 공이 이동한 최소 거리의 제곱을 구하자
    
    4개의 변에 ball을 반사시켰을 때 최소거리와
    각 꼭짓점에 반사시켰을 때 최소거리를 비교하면 될 듯
    
    단 ball이 흰색공의 진로에 있으면 안됨
    
    반대방향에 ball이 있는지는 어떻게 알까?
    
    y = a * x + b에 (c, d)를 대입해보면 됨
    
    a = m / n일 때
    
    n * d = m * c + n * b 가 성립해야 함

    */
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for(int i = 0; i < balls.length; i++){
            int[] ball = balls[i];
            
            int minSquareDist = 1000 * 1000 + 2000 * 2000 + 1;
            
            // 윗변 대칭
            if(!(ball[0] == startX && ball[1] > startY) && !(ball[1] == n || startY == n)){
                int width = Math.abs(ball[0] - startX);
                int height = 2 * n - ball[1] - startY;
                minSquareDist = Math.min(minSquareDist, width * width + height * height);
            }
            
            // 아랫변 대칭
            if(!(ball[0] == startX && ball[1] < startY) && !(ball[1] == 0 || startY == 0)){
                int width = Math.abs(ball[0] - startX);
                int height = ball[1] + startY;
                minSquareDist = Math.min(minSquareDist, width * width + height * height);
            }
            
            // 왼쪽변 대칭
            if(!(ball[0] < startX && ball[1] == startY) && !(ball[0] == 0 || startX == 0)){
                int width = ball[0] + startX;
                int height = Math.abs(ball[1] - startY);
                minSquareDist = Math.min(minSquareDist, width * width + height * height);
            }
            
            // 오른쪽 변 대칭
            if(!(ball[0] > startX && ball[1] == startY) && !(ball[0] == m || startX == m)){
                int width = 2 * m - ball[0] - startX;
                int height = Math.abs(ball[1] - startY);
                minSquareDist = Math.min(minSquareDist, width * width + height * height);
            }
            
            // 왼쪽 위 꼭짓점 대칭
            if((startY - n) * ball[0] + n * startX == ball[1] * startX){
                int h1 = n - startY;
                int w1 = startX;
                int h2 = n - ball[1];
                int w2 = ball[0];
                int startSquare = h1 * h1 + w1 * w1;
                int ballSquare = h2 * h2 + w2 * w2;
                if(startSquare < ballSquare){
                    int width = ball[0] + startX;
                    int height = 2 * n - ball[1] - startY;
                    minSquareDist = Math.min(minSquareDist, width * width + height * height);
                }
            }
            
            // 오른쪽 위
            if((n - startY) * ball[0] + n * (m - startX) == ball[1] * (m - startX)){
                int h1 = n - startY;
                int w1 = m - startX;
                int h2 = n - ball[1];
                int w2 = m - ball[0];
                int startSquare = h1 * h1 + w1 * w1;
                int ballSquare = h2 * h2 + w2 * w2;
                if(startSquare < ballSquare){
                    int width = 2 * m - ball[0] - startX;
                    int height = 2 * n - ball[1] - startY;
                    minSquareDist = Math.min(minSquareDist, width * width + height * height);
                }
            }
            
            // 오른쪽 아래
            if((-startY) * ball[0] + n * (m - startX) == ball[1] * (m - startX)){
                int h1 = startY;
                int w1 = m - startX;
                int h2 = ball[1];
                int w2 = m - ball[0];
                int startSquare = h1 * h1 + w1 * w1;
                int ballSquare = h2 * h2 + w2 * w2;
                if(startSquare < ballSquare){
                    int width = 2 * m - ball[0] - startX;
                    int height = ball[1] + startY;
                    minSquareDist = Math.min(minSquareDist, width * width + height * height);
                }
            }
            
             // 왼쪽 아래
            if(startY * ball[0] + n * startX == ball[1] * startX){
                int h1 = startY;
                int w1 = startX;
                int h2 = ball[1];
                int w2 = ball[0];
                int startSquare = h1 * h1 + w1 * w1;
                int ballSquare = h2 * h2 + w2 * w2;
                if(startSquare < ballSquare){
                    int width = ball[0] + startX;
                    int height = ball[1] + startY;
                    minSquareDist = Math.min(minSquareDist, width * width + height * height);
                }
            }
                
            answer[i] = minSquareDist;
        }
        
        return answer;
    }
}
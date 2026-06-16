import java.util.*;

class Solution {
    /*
    26/6/16 9:12 ~ 
    
    모든 로봇들이 이동하면서 위험 상황이 발생하는 횟수를 구하자
    
    ---
    
    시뮬레이션 유형으로 보임
    
    매초마다
    - 모든 로봇을 다음 목적지 방향으로 최단거리로 이동
    - 위험상황 개수 카운트
    - 목적지에 도착한 로봇을 제거
    
    ---
    
    최단거리 방향으로 이동하는 방법은?
    
    */
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        List<Robot> robots = new ArrayList<>();
        for(int[] route : routes){
            int[][] pointCoords = new int[route.length][2];
            for(int i = 0; i < route.length; i++){
                pointCoords[i] = points[route[i] - 1];
            }
            
            robots.add(new Robot(pointCoords));
        }
        
        // System.out.println(robots);
        
        answer += countDangers(robots);
        while(!isFinished(robots)){
            moveRobots(robots);
            answer += countDangers(robots);
            removeRobots(robots);
        }
        
        return answer;
    }
    
    private boolean isFinished(List<Robot> robots){
        for(Robot r : robots){
            if(!r.isFinished()){
                return false;
            }
        }
        return true;
    }
    
    private void moveRobots(List<Robot> robots){
        for(Robot r : robots){
            r.move();
        }
    }
    
    private int countDangers(List<Robot> robots){
        Map<String, Integer> counterMap = new HashMap<>();
        for(Robot r : robots){
            if(r.isRemoved()){
                continue;
            }
            Integer count = counterMap.getOrDefault(r.toString(), 0);
            counterMap.put(r.toString(), count + 1);
        }
        
        int count = 0;
        for(String key : counterMap.keySet()){
            if(counterMap.get(key) > 1){
                count++;
            }
        }
        return count;
    }
    
    private void removeRobots(List<Robot> robots){
        for(Robot r : robots){
            if(r.isFinished()){
                r.remove();
            }
        }
    }
    
    static class Robot{
        int row;
        int col;
        int next;
        int dest;
        int[][] route;
        boolean isRemoved = false;
        
        public Robot(int[][] route){
            this.row = route[0][0];
            this.col = route[0][1];
            this.next = 1;
            this.dest = route.length - 1;
            this.route = route;
        }
        
        public void move(){  
            if(isFinished()){
                return;
            }
            
            if(row == route[next][0] && col == route[next][1]){
                next++;
            }
            
            if(row > route[next][0]){
                row--;
                return;
            }
            if(row < route[next][0]){
                row++;
                return;
            }
            
            if(col > route[next][1]){
                col--;
                return;
            }   
            if(col < route[next][1]){
                col++;
                return;
            }    
        }
        
        public boolean isFinished(){
            return next == dest && row == route[dest][0] && col == route[dest][1];
        }
        
        public String toString(){
            return row + "," + col;
        }
        
        public void remove(){
            isRemoved = true;
        }
        
        public boolean isRemoved(){
            return isRemoved;
        }
    }
    
    
    
}
import java.util.*;

class Solution {
    /*
    26/5/30 17:11 ~ 
    
    완호의 석차를 구하자
    
    1. 두 점수가 모두 낮은 경우가 한번이라도 있으면 인센티브 대상에서 제외
    2. 인센티브 대상을 두 점수의 합을 기준으로 정렬. 동점자가 있으면 같은 석차로 매긴다
    
    인센티브 대상을 구하는 과정을 N^2 정도로 작게 만들 수 있나?
    
    6, 3
    5, 7
    2, 4
    1, 6
    
    a 점수를 내림차순으로 b 점수를 내림차순으로 정렬 후에 
    b점수의 최댓값을 보존해서 인센티브 제외 대상인지 확인한다?
    현재 사원보다 a점수가 높은 사람 중에서 b점수가 높은 경우가 있는지만 알면 된다
    
    */
    public int solution(int[][] scores) {
        
        // 인센티브 대상 리스트 생성
        // scores[0]가 인센티브 대상에서 제외되면 -1 리턴
        
        List<int[] > employees = new ArrayList<>();
        for(int i = 0; i < scores.length; i++){
            employees.add(new int[] { scores[i][0], scores[i][1], i });
        }
        
        employees.sort((a, b) -> {
            if(a[0] != b[0]){
                return b[0] - a[0];
            }
            return b[1] - a[1];
        });
            
        // for(int i = 0; i < employees.size(); i++)
        //     System.out.print(Arrays.toString(employees.get(i)));
        // System.out.println();
        
        List<Integer> incentives = new ArrayList<>();
        
        int maxScoreB = -1;
        int prevScoreAMaxScoreB = employees.get(0)[1];
        
        incentives.add(employees.get(0)[0] + employees.get(0)[1]);
        for(int i = 1; i < employees.size(); i++){
            if(employees.get(i - 1)[0] > employees.get(i)[0]){
                maxScoreB = Math.max(maxScoreB, prevScoreAMaxScoreB);
            }
            
            prevScoreAMaxScoreB = Math.max(prevScoreAMaxScoreB, employees.get(i)[1]);
            
            if(employees.get(i)[1] >= maxScoreB){
                incentives.add(employees.get(i)[0] + employees.get(i)[1]);
            }
            else{
                if(employees.get(i)[2] == 0){
                    return - 1;
                }
            }            
        }
        
        // 점수 합을 기준으로 정렬
        incentives.sort((a, b) -> b - a);
        
        // System.out.println(incentives);
        
        // 석차 계산
        if(incentives.get(0) == scores[0][0] + scores[0][1]){
            return 1;
        }
        
        int rank = 1;
        int drawCount = 1;
        for(int i = 1; i < incentives.size(); i++){
            if(incentives.get(i - 1) > incentives.get(i)){
                rank += drawCount;
                drawCount = 0;
            }
            
            if(incentives.get(i) == scores[0][0] + scores[0][1]){
                break;
            }
            
            drawCount++;
        }
        
        return rank;
    }
}
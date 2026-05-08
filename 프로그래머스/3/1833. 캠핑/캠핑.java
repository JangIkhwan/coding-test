import java.util.*;

class Solution {
    /*
    26/5/8 10:46 ~ 11:04, 11:14~
    
    텐트를 설치할 수 있는 쐐기의 쌍의 개수를 구하자
    
    쐐기는 직사각형 대각선상에 위치해야한다
    직사각형 안에 쐐기가 있어서는 안된다
    
    좌표 값 <= int 최대값
    
    완전탐색으로 가능한가?
    N * N * N 시간복잡도인데 시간 초과 가능성이 있어 보임
    
    N * N * log N 정도면 좋은데
    
    쐐기 (a, b), (c, d)
    x좌표로 정렬된 리스트를 준비
    x좌표에 대해서 a ~ c 사이에, y좌표가 b 초과 d 미만의 값을 가지는 좌표가 있는지 찾으면 됨
    
    반례
    [[0, 0], [1, 1], [2, 3], [2, 4]] => 3 , y좌표는 내림차순으로 정렬 필요
    
    [[0, 0], [2123456789, 2123456789]] => 1, long으로 변환 필요
    */
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        Arrays.sort(data, (a, b) -> a[0] - b[0]);
        
        for(int i = 0; i < data.length; i++){
            List<int []> ups = new ArrayList<>();
            List<int []> downs = new ArrayList<>();
            for(int j = i + 1; j < data.length; j++){
                if(data[j][0] > data[i][0] && data[j][1] > data[i][1]){
                    ups.add(data[j]);
                }
                else if(data[j][0] > data[i][0] && data[j][1] < data[i][1]){
                    downs.add(data[j]);
                }
            }
            
            ups.sort((a, b) -> {
                if(a[0] != b[0]){
                    return a[0] - b[0];
                } 
                return b[1] - a[1];
            });
            
            downs.sort((a, b) -> {
                if(a[0] != b[0]){
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            });
            
            int recentMinY = Integer.MAX_VALUE;
            for(int[] up : ups){
                if(areaIsZero(data[i], up)){
                    continue;
                }
                if(recentMinY >= up[1]){
                    // System.out.println(Arrays.toString(data[i]) + " " + Arrays.toString(up));
                    recentMinY = up[1];
                    answer++;
                }
            }
            
            int recentMaxY = Integer.MIN_VALUE;
            for(int[] down : downs){
                // System.out.println(Arrays.toString(data[i]) + " " + Arrays.toString(down));
                
                if(areaIsZero(data[i], down)){
                    continue;
                }
                if(recentMaxY <= down[1]){
                    recentMaxY = down[1];
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    private boolean areaIsZero(int[] a, int[] b){
        return ((long) Math.abs(a[0] - b[0])) * ((long) Math.abs(a[1] - b[1])) <= 0L;
    }
}
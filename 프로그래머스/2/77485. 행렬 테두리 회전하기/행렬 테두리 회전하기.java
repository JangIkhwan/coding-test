import java.util.*;

class Solution {
    /*
    26-7-21 16:04 ~ 
    
    queries의 각 회전에서 위치가 바뀐 숫자 중에서 가장 작은 숫자를 구하자
    
    ---
    
    가로 : N, 세로 : M
    쿼리의 수 : Q
    
    시간복잡도 : Q * ( N + M )
    
    --- 
    
    */
    private int R, C;
    private int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        init(rows, columns);
        
        for(int i = 0; i < queries.length; i++){
            int[] query = queries[i];
            int minValue = rotate(query[0], query[1], query[2], query[3]);
            answer[i] = minValue;
        }
        
        return answer;
    }
    
    private void init(int rows, int columns){
        R = rows;
        C = columns;
        
        map = new int[R + 1][C + 1];
        
        int i = 1;
        for(int r = 1; r <= R; r++){
            for(int c = 1; c <= C; c++){
                map[r][c] = i;
                i++;
            }
        }
    }
    
    private int rotate(int leftR, int leftC, int rightR, int rightC){
        int leftTop = map[leftR][leftC];
        int leftBottom = map[rightR][leftC];
        int rightTop = map[leftR][rightC];
        int rightBottom = map[rightR][rightC];
        
        for(int c = rightC - 1; c >= leftC + 1; c--){
            map[leftR][c + 1] = map[leftR][c];
        }
        for(int r = rightR - 1; r >= leftR + 1; r--){
            map[r + 1][rightC] = map[r][rightC];
        }
        for(int c = leftC + 1; c <= rightC - 1; c++){
            map[rightR][c - 1] = map[rightR][c];
        }
        for(int r = leftR + 1; r <= rightR - 1; r++){
            map[r - 1][leftC] = map[r][leftC];
        }
        
        map[leftR][leftC + 1] = leftTop;
        map[rightR - 1][leftC] = leftBottom;
        map[leftR + 1][rightC] = rightTop;
        map[rightR][rightC - 1] = rightBottom;
        
        // for(int r = 1; r <= R; r++){
        //     System.out.println(Arrays.toString(map[r]));
        // }
        // System.out.println();
        
        int minValue = Integer.MAX_VALUE;
        
        for(int c = leftC; c <= rightC; c++){
            minValue = Math.min(minValue, Math.min(map[leftR][c], map[rightR][c]));
        }
        for(int r = leftR; r <= rightR; r++){
            minValue = Math.min(minValue, Math.min(map[r][leftC], map[r][rightC]));
        }
        
        return minValue;
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        int N, L;
        int[][] map;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int r = 0; r < N; r++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int rowCount = 0;
        for(int r = 0; r < N; r++){
            int prevHeight = map[r][0];
            int prevLength = 1;
            boolean isRoad = true;
            boolean isDescending = false;

            for(int c = 1; c < N; c++){
                if(map[r][c] == prevHeight){
                    prevLength += 1;
                }
                else if(map[r][c] > prevHeight){
                    if(map[r][c] - prevHeight > 1 || isDescending && prevLength < 2 * L || !isDescending && prevLength < L){
                        isRoad = false;
                        break;
                    }
                    prevHeight = map[r][c];
                    prevLength = 1;
                    isDescending = false;
                }
                else{
                    if(prevHeight - map[r][c] > 1 || isDescending && prevLength < L){
                        isRoad = false;
                        break;
                    }
                    prevHeight = map[r][c];
                    prevLength = 1;
                    isDescending = true;
                }
            }
            if(isDescending && prevLength < L){
                isRoad = false;
            }

            if(isRoad) {
                rowCount += 1;
//                System.out.println("row = " + r);
            }
        }


        // 길의 개수를 출력
        int colCount = 0;
        for(int c = 0; c < N; c++){
            int prevHeight = map[0][c];
            int prevLength = 1;
            boolean isRoad = true;
            boolean isDescending = false;

            for(int r = 1; r < N; r++){
                if(map[r][c] == prevHeight){
                    prevLength += 1;
                }
                else if(map[r][c] > prevHeight){
                    if(map[r][c] - prevHeight > 1 || isDescending && prevLength < 2 * L || !isDescending && prevLength < L){
                        isRoad = false;
                        break;
                    }
                    prevHeight = map[r][c];
                    prevLength = 1;
                    isDescending = false;

                }
                else{
                    if(prevHeight - map[r][c] > 1 || isDescending && prevLength < L){
                        isRoad = false;
                        break;
                    }
                    prevHeight = map[r][c];
                    prevLength = 1;
                    isDescending = true;

                }
            }
            if(isDescending && prevLength < L){
                isRoad = false;
            }

            if(isRoad){
                colCount += 1;
//                System.out.println("col = " + c);
            }
        }

        System.out.println(rowCount + colCount);
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M, x, y, K;
        int[][] map;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Dice dice = new Dice(x, y);
        // 주사위 시뮬레이션
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        st = new StringTokenizer(line);
        for(int i = 0; i < K; i++){
            int command = Integer.parseInt(st.nextToken());
            if(dice.roll(command, N, M)){
                dice.copy(map);
                sb.append(dice.getFront() + "\n");
            }
        }
        // 결과 출력
        System.out.println(sb.toString());
    }

    static class Dice {
        String row = new String("000");
        String col = new String("0000");
        int x;
        int y;

        public Dice(int x, int y){
            this.x = x;
            this.y = y;
        }

        public boolean roll(int command, int N, int M) {
            String downFace;
            switch (command) {
                case 1 :
                    if(y + 1 >= M) return false;
                    y++;
                    downFace = row.substring(2);
                    row = col.substring(3) + row.substring(0, 2);
                    col = col.substring(0, 1) + row.substring(1, 2) + col.substring(2, 3) + downFace;
                    break;
                case 2 :
                    if(y - 1 < 0) return false;
                    y--;
                    downFace = row.substring(0, 1);
                    row = row.substring(1) + col.substring(3);
                    col = col.substring(0, 1) + row.substring(1, 2) + col.substring(2, 3) + downFace;
                    break;
                case 3 :
                    if(x - 1 < 0) return false;
                    x--;
                    col = col.substring(1) + col.substring(0, 1);
                    row = row.substring(0, 1) + col.substring(1, 2) + row.substring(2);
                    break;
                case 4 :
                    if(x + 1 >= N) return false;
                    x++;
                    col = col.substring(3) + col.substring(0, 3);
                    row = row.substring(0, 1) + col.substring(1, 2) + row.substring(2);
                    break;
            }

//            System.out.println("x = " + x + ", y = " + y);

            return true;
        }

        public void copy(int[][] map){
            if(map[x][y] == 0){
                map[x][y] = Integer.parseInt(col.substring(3));
            }
            else{
                col = col.substring(0, 3) + String.valueOf(map[x][y]);
                map[x][y] = 0;
            }

//            System.out.println("row =" + row);
//            System.out.println("col =" + col);
//
//            System.out.println("map = ");
//            for(int i = 0; i < map.length; i++){
//                for(int j = 0; j < map[0].length; j++){
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
        }

        public String getFront(){
            return row.substring(1, 2);
        }
    }
}
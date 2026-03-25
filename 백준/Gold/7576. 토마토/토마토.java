import java.io.*;
import java.util.*;

public class Main {
    /*
     https://www.acmicpc.net/problem/7576

     25/2/9 20:53 ~
     */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int M, N;
    private static int[][] box;


    public static void main(String[] args) throws IOException {
        input();
        if(allRipeTomatoes()){
            System.out.println("0");
            return;
        }
        bfs();
        System.out.println(findTotalDays());
    }

    private static void input() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        for(int y = 0; y < N; y++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int x = 0; x < M; x++){
                box[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static boolean allRipeTomatoes(){
        for(int y = 0; y < box.length; y++){
            for(int x = 0; x < box[0].length; x++){
                if(box[y][x] != 1){
                    return false;
                }
            }
        }
        return true;
    }

    private static void bfs(){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Node> qu = new LinkedList<>();
        for(int y = 0; y < box.length; y++){
            for(int x = 0; x < box[0].length; x++){
                if(box[y][x] == 1){
                    qu.add(new Node(x, y, 0));
                    box[y][x] = 0;
                }
            }
        }
        while(!qu.isEmpty()){
            Node front = qu.poll();
            if(front.x < 0 || front.x >= box[0].length || front.y < 0 || front.y >= box.length) continue;
            if(box[front.y][front.x] != 0) continue;
            box[front.y][front.x] = front.prevDays + 1;
            for(int direct = 0; direct < 4; direct++){
                int newX = front.x + dx[direct];
                int newY = front.y + dy[direct];
                qu.add(new Node(newX, newY, front.prevDays + 1));
            }
        }
    }

    private static int findTotalDays(){
        int maxDays = 0;
        for(int y = 0; y < box.length; y++ ){
            for(int x = 0; x < box[0].length; x++){
                if(box[y][x] == 0){
                    return -1;
                }
                if(maxDays < box[y][x]){
                    maxDays = box[y][x];
                }
            }
        }
        return maxDays - 1;
    }

    static class Node{
        int x;
        int y;
        int prevDays;

        public Node(int x, int y, int prevDays){
            this.x = x;
            this.y = y;
            this.prevDays = prevDays;
        }
    }
}
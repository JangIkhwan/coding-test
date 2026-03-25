import java.io.*;
import java.util.*;

public class Main {
   
    static int N, K;
    static int[] durability;
    static int[] robots;
    static int start = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        durability = new int[N * 2];
        for(int i = 0; i < N * 2; i++){
            durability[i] = Integer.parseInt(st.nextToken());
        }

        // 내구도 0인 칸이 K개 미만일 동안
            // 벨트가 이동
            // 가장 왼쪽에 있는 로봇부터 앞으로 이동
            // 추가할 수 있으면 로봇 추가

        start = 0;
        robots = new int[N * 2];
        int round = 1;
        while(true){
//            System.out.println(" >> start = " + start);
//            System.out.println("robots = " + Arrays.toString(robots));
//            System.out.println("durability = " + Arrays.toString(durability));
            moveBelt();
            moveRobots();
            addRobot();
            if(checkEnd()){
                break;
            }
            round++;
        }
        System.out.println(round);
    }

    static void moveBelt(){
        start = (start - 1 + N * 2) % (N * 2);
        int end = (start + N - 1) % (N * 2);
        if(robots[end] == 1){
            robots[end] = 0;
        }
    }

    static void moveRobots(){
        int end = (start + N - 1) % (N * 2);
        int i = (end - 1 + N * 2) % (N * 2);
        while(true){
            if(robots[i] == 1){
                int next = (i + 1) % (N * 2);
                if(durability[next] > 0 && robots[next] == 0) { // 여기가 잘못된 것 같음
                    robots[next] = 1;
                    robots[i] = 0;
                    durability[next] -= 1;
                }
                if(next == end){
                    robots[end] = 0;
                }
            }
            if(i == start){
                break;
            }
            i = (i - 1 + N * 2) % (N * 2);
        }
    }

    static void addRobot(){
        if(durability[start] > 0){
            robots[start] = 1;
            durability[start] -= 1;
        }
    }

    static boolean checkEnd(){
        int count = 0;
        for(int i = 0; i < N * 2; i++){
            if(durability[i] == 0){
                count++;
            }
        }
        return count >= K;
    }
}
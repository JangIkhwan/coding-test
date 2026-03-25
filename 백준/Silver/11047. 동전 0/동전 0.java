import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    private void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for(int i = 0; i < N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        while(K > 0){
//            System.out.println(K);
            for(int i = N - 1; i >= 0; i--){
                if(K >= coins[i]){
                    K -= coins[i];
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int N, W, L;
        int[] truck;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        truck = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            truck[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> qu = new LinkedList<>();
        for(int i = 0; i < W; i++){
            qu.offer(0);
        }

        int answer = 0;
        int totalWeight = 0;
        int in = 0;
        while(!qu.isEmpty()){
            int out = qu.poll();
            totalWeight -= out;
            if(in < N) {
                if (totalWeight + truck[in] <= L) {
                    totalWeight += truck[in];
                    qu.offer(truck[in]);
                    in++;
                } else {
                    qu.offer(0);
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}
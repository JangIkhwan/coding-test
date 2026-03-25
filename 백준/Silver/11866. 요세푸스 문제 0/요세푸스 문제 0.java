import java.io.*;
import java.util.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/11866

    1 2 3 4 5 6 7

    1 2 _ 4 5 6 7
    1 2 4 5 _ 7
    1 _ 4 5 7

    모든 수를 큐에 담음.
    front에서 뽑아서 3번째로 뽑히는 것이 순열에 먼저 등장.
    큐가 빌 때까지 반복

    23:11 ~
    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N, K;
        Queue<Integer> qu = new LinkedList<>();
        int[] seq;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        seq = new int[N];
        for(int i = 1; i <= N; i++){
            qu.add(i);
        }
        int idx = 0;
        while(!qu.isEmpty()){
            for(int j = 1; j <= K; j++){
                int front = qu.poll();
                if(j == K) {
                    seq[idx] = front;
                    idx++;
                    break;
                }
                qu.add(front);
            }
        }
        bw.write("<");
        for(idx = 0; idx < N; idx++){
            bw.write(Integer.toString(seq[idx]));
            if(idx < N - 1) {
                bw.write(", " );
            }
        }
        bw.write(">\n");
        bw.flush();
    }
}

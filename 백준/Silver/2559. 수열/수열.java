import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    https://www.acmicpc.net/problem/2559

    25/1/27 22:32
    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N, K;
        int[] seq;
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        seq = new int[N + 1];
        line = br.readLine();
        st = new StringTokenizer(line);
        for(int i = 1; i <= N; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }
        int maxSum = 0;
        int sum = 0;
        for(int i = 1; i <= K; i++){
            sum += seq[i];
        }
        maxSum = sum;
        for(int i = K + 1; i <= N; i++){
            sum += seq[i] - seq[i - K];
            if(maxSum < sum) maxSum = sum;
        }
        System.out.println(maxSum);
    }
}
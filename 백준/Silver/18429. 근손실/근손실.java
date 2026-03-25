import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    https://www.acmicpc.net/problem/18429

    12:44 ~

    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int answer = 0;
    private static int N, K;
    private static int[] kits;
    private static boolean[] selected;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        kits = new int[N];
        selected = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            kits[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, 500);
        System.out.println(answer);
    }

    private static void dfs(int depth, int weight){
        if(depth == N){
            answer++;
            return;
        }
        if(weight < 500){
            return;
        }
        for(int i = 0; i < kits.length; i++){
            if(!selected[i]) {
                selected[i] = true;
                weight += kits[i] - K;
                dfs(depth + 1, weight);
                weight -= (kits[i] - K);
                selected[i] = false;
            }
        }
    }
}

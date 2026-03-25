import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;

    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sequence = new int[M + 1];
        dfs(1);
        bw.flush();
    }

    private static void dfs(int depth) throws IOException {
        if(depth >= M + 1){
            for(int i = 1; i <= M; i++) {
                bw.write(sequence[i] + " ");
            }
            bw.write("\n");
            return;
        }
        for(int i = 1; i <= N; i++){
            if(sequence[depth - 1] <= i){
                sequence[depth] = i;
                dfs(depth + 1);
            }
        }
    }
}
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N, M, K;
        int[][] A;
        int[][] B;
        int[][] result;
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int j = 1; j <= M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        line = br.readLine();
        st = new StringTokenizer(line);
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = new int[M + 1][K + 1];
        for(int i = 1; i <= M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int j = 1; j <= K; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = new int[N + 1][K + 1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= K; j++){
                for(int k = 1; k <= M; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= K; j++){
                bw.write(Integer.toString(result[i][j]) + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int[] A;
    private static int[] operatorNumber = new int[4];;
    private static int maxValue = -1234567890;
    private static int minValue = 1234567890;
    public static void main(String[] args) throws IOException {
        input();
        dfs(1, A[0]);
        System.out.println(maxValue);
        System.out.println(minValue);
    }

    private static void dfs(int depth, int prevValue) {
        if(depth == N){
            if(maxValue < prevValue) {
                maxValue = prevValue;
            }
            if(minValue > prevValue){
                minValue = prevValue;
            }
            return;
        }
        int[] values = new int[4];
        values[0] = prevValue + A[depth];
        values[1] = prevValue - A[depth];
        values[2] = prevValue * A[depth];
        values[3] = prevValue / A[depth];
        for(int oper = 0; oper < 4; oper++){
            if(operatorNumber[oper] > 0){
                operatorNumber[oper] -= 1;
                dfs(depth + 1, values[oper]);
                operatorNumber[oper] += 1;
            }
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4 ; i++){
            operatorNumber[i] = Integer.parseInt(st.nextToken());
        }
    }

}
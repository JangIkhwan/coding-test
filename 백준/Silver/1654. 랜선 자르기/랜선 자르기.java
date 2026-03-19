import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    public void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K, N;
        long[] lines;

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lines = new long[K];
        long lineMaxLen = 0;
        for(int i = 0; i < K; i++){
            lines[i] = Long.parseLong(br.readLine());
            lineMaxLen = Math.max(lineMaxLen, lines[i]);
        }

        // 파라매트릭 서치를 통해서 최대 길이를 찾는다
        long lo = 0;
        long hi = lineMaxLen + 1;
        while(lo + 1 < hi){
            long mid = (lo + hi) / 2;
            if(cutLines(lines, mid) >= N){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }

        // 최대 길이를 출력한다
        System.out.println(lo);
    }

    private long cutLines(long[] lines, long len){
        long sum = 0;
        for(int i = 0; i < lines.length; i++){
            sum += lines[i] / len;
        }
        return sum;
    }
}
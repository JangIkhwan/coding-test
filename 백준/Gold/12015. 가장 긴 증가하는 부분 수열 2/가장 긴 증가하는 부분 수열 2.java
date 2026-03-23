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
        int N;
        int[] A;

        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // LIS 길이 계산
        int[] lis = new int[N + 1];
        lis[1] = A[1];
        int length = 1;
        for(int i = 2; i <= N; i++){
            int lo = 0;
            int hi = length + 1;
            while(lo + 1 < hi){
                int mid = (lo + hi) / 2;
                if(lis[mid] >= A[i]){
                    hi = mid;
                }
                else{
                    lo = mid;
                }
            }
            if(hi < length + 1){
                lis[hi] = A[i];
            }
            else{
                lis[++length] = A[i];
            }
        }

        // 정답 출력
        System.out.println(length);
    }
}
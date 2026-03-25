import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int N, S;
        int[] A;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 슬라이딩 윈도우로 가장 짧은 길이 찾기
        int answer = 200000;
        int left = 0;
        int right = 0;
        int sum = 0;
        while(right < N){
            sum += A[right];
            while(sum - A[left] >= S){
                sum -= A[left];
                left++;
            }
            if(sum >= S){
                answer = Math.min(answer, right - left + 1);
            }
            right++;
        }
        if(answer == 200000){
            System.out.println(0);
            return;
        }
        System.out.println(answer);
    }
}
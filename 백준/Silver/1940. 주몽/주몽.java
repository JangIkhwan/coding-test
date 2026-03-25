import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int N, M;
        int []A;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        // 투 포인터 이용
        // 처음에는 left와 right가 양 끝에 있음
        // left < right일때까지
            // left + right > M이면 right를 왼쪽으로
            // == M이면 카운트하고 left를 오른쪽, right를 왼쪽으로 이동
            // < M이면 left를 오른쪽으로 이동
        int answer = 0;
        int left = 0;
        int right = N - 1;
        while(left < right){
            int sum = A[left] + A[right];
            if(sum > M){
                right--;
            }
            else if(sum == M){
                answer++;
                left++;
                right--;
            }
            else{
                left++;
            }
        }

        // 카운트 값을 출력
        System.out.println(answer);
    }
}
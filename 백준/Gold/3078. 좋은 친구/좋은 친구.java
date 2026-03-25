import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        // 입력을 처리
        int N, K;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String[] students = new String[N];
        for(int i = 0; i < N; i++){
            students[i] = br.readLine();
        }

        // i번 학생을 중심으로 슬라이딩 윈도우로 탐색하여 길이가 같은 친구의 수를 계산
        long answer = 0;
        int[] friends = new int[21];
        int right = 0;
        for(; right <= K; right++){
            friends[students[right].length()]++;
        }
        for(int cur = 0; cur < N; cur++){
            friends[students[cur].length()]--;
            answer += friends[students[cur].length()];
            if(right < N) {
                friends[students[right].length()]++;
                right++;
            }
        }

        // 답을 출력
        System.out.println(answer);
    }
}
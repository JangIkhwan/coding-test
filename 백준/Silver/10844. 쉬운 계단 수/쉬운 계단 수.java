import java.io.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/10844

    25/2/28 16:17 ~ 16:45

    브루트포스 방식
    > 맨 앞자리는 9개 중 하나, 그 뒤부터는 10개 중 하나
    9 * 10^N-1 의 경우의 수 -> 시간 초과 예상

    백트래킹
    > 9 * 2^N-1 의 경우의 수 -> 시간 초과 예

    DP => 선택한 방법
    dp[i][j] 길이가 i이고 마지막 자리수가 j인 계단수의 개수
    dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1] (i >= 2, 0 <= j <= 9)

    첫번째 시도 틀림
    답을 10억으로 나눈 나머지를 출력해야 함을 잊음

    두번째 시도 틀림
    답을 10억으로 나눈 나머지를 계산하는 부분이 잘못된 것으로 보임
    모듈로 연산 전개를 잘못했기 때문에 이를 수정함

    세번째 시도만에 성공
    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        final int divisor = 1000000000;
        int N;
        int [][] dp;
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][11];
        for(int i = 1; i <= 9; i++){
            dp[1][i] = 1;
        }
        for(int i = 2; i <= N; i++){
            for(int j = 0; j <= 9; j++){
                if(j >= 1){
                    dp[i][j] += dp[i-1][j-1] % divisor;
                }
                if(j <= 8){
                    dp[i][j] += dp[i-1][j+1] % divisor;
                }
                dp[i][j] %= divisor;
            }
        }
        int answer = 0;
        for(int i = 0; i <= 9; i++){
            answer += dp[N][i]; // dp값이 나눈 나머지이므로 나누기 연산을 삭제함
            answer %= divisor; // 이걸 추가함
        }
        System.out.println(answer);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        int[][] cost;

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1][4];
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 계산
        int[][][] dp = new int[N + 1][4][4];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= 3; j++){
                Arrays.fill(dp[i][j], 1000001);
            }
        }

        for(int color = 1; color <= 3; color++)
            dp[1][color][color] = cost[1][color];

        for(int color = 1; color <= 3; color++){
            for(int start = 1; start <= 3; start++)
                if(color != start) dp[2][color][start] = Math.min(dp[2][color][start], dp[1][start][start] + cost[2][color]);
        }

        for(int house = 3; house <= N - 1; house++){
            for(int start = 1; start <= 3; start++){
                for(int color = 1; color <= 3; color++){
                    for(int prev = 1; prev <= 3; prev++){
                        if(prev != color) dp[house][color][start] = Math.min(dp[house][color][start], dp[house - 1][prev][start] + cost[house][color]);
                    }
                }
            }
        }

        int answer = 1000001;
        for(int color = 1; color <= 3; color++){
            for(int start = 1; start <= 3; start++){
                for(int prev = 1; prev <= 3; prev++){
                    if(color != start && color != prev) {
                        dp[N][color][start] = Math.min(dp[N][color][start], dp[N - 1][prev][start] + cost[N][color]);
                        answer = Math.min(answer, dp[N][color][start]);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
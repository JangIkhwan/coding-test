import java.io.*;
import java.util.*;

public class Main {
    /*
    26/4/3 00:21 ~ 01:20

    https://www.acmicpc.net/problem/31091

    거짓말을 하는 사람의 수로 가능한 수를 오름차순으로 모두 출력하라

    완전탐색은 불가능.
    N <= 50만이니 2^50만 개의 경우의 수를 따져야 해서 안됨

    N log N 정도의 알고리즘이 있을까?

    x명이 거짓말을 하고 있는가에 대해서 판정할 수 있을까?

    0 1 2 3 4 : 거짓말하는 사람의 추정수
    2 3 2 2 2 : 주장한 사람의 수

    위의 정보를 이용해서 판정할 수 있을 것 같은데

    근데 시간초과가 발생...

    시간초과를 줄이려면 주장한 사람의 수를 구하는 시간복잡도를 줄여야 함
    구간합?

    */

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solution();
    }

    public void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 주장한 사람의 수 구하기
        int[] temp = new int[N + 1];
        int[] temp2 = new int[N + 1];
        for(int i = 1; i <= N; i++){
            int lierNumber = Integer.parseInt(st.nextToken());
            if(lierNumber > 0){
                temp[lierNumber] += 1;
            }
            else{
                temp2[-lierNumber] -= 1;
            }
        }

        for(int i = 1; i <= N; i++){
            if(temp[i] > 0){
                temp[i] = temp[i - 1] + temp[i];
            }
            else if (temp[i] == 0){
                temp[i] = temp[i - 1];
            }
        }

        if(temp2[N] < 0){
            temp2[N] = -temp2[N];
        }
        for(int i = N - 1; i >= 0; i--){
            if(temp2[i] < 0){
                temp2[i] = temp2[i + 1] - temp2[i];
            }
            else if (temp2[i] == 0){
                temp2[i] = temp2[i + 1];
            }
        }

        int[] freqs = new int[N + 1];
        for(int i = 0; i <= N; i++){
            freqs[i] += temp[i] + temp2[i];
        }

//        System.out.println(Arrays.toString(freqs));

        // 가능한지 판정하기
        int count = 0;
        StringBuffer sb = new StringBuffer();
        for(int lier = 0; lier <= N; lier++){
            if(N - freqs[lier] == lier){
                sb.append(lier);
                if(lier < N){
                    sb.append(" ");
                }
                count++;
            }
        }
        System.out.println(count);
        if(count > 0){
            System.out.println(sb.toString());
        }
    }

}
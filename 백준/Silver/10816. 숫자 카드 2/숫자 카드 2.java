import java.io.*;
import java.util.*;

public class Main {
    /*
    https://www.acmicpc.net/problem/10816

    25/2/11 14:51 ~ 14:55 15:01 ~

    N과 M은 최대 50만이므로 브루트포스 N*M의 복잡도로는 불가
    N개에서 숫자가 몇 개 있는지를 빠르게 알 수 있어야 함. log N 정도로.
    log N -> 이진 탐색?
    매 쿼리미다 이진탐색으로 해당 숫자이상부터 해당숫자 초과인 구간을 찾는다.
    그 구간의 길이가 곧 쿼리의 답이다.

     */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N, M;
        int[] cards;
        int[] querys;
        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        for(int i = 0; i < N; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        querys = new int[M];
        line = br.readLine();
        st = new StringTokenizer(line);
        for(int i = 0; i < M; i++){
            querys[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);
        for(int query : querys){
            int start = binarySearch(cards, query);
            int end = binarySearch(cards, query + 1);
            int number = end - start;
            bw.write(Integer.toString(number) + " ");
        }
        bw.flush();
    }

    private static int binarySearch(int[] A, int key){
        int lo = -1;
        int hi = A.length;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(A[mid] < key){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }
        return hi;
    }
}

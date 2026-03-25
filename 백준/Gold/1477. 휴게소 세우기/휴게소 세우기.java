import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, L;
    private static int[] station;
    private static int[] length;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        if(N == 0){
            System.out.println((int) Math.ceil((double)L / (M + 1)));
            return;
        }

        st = new StringTokenizer(br.readLine());
        station = new int[N];
        for(int i = 0; i < N; i++){
            station[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(station);

        length = new int[N + 1];
        length[0] = station[0];
        for(int i = 1; i < N; i++){
            length[i] = station[i] - station[i - 1];
        }
        length[N] = L - station[N - 1];
        Arrays.sort(length);

        // 파라매트릭 서치
        int lo = 0;
        int hi = length[N];
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(place(mid)){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        System.out.println(hi);
    }

    private static boolean place(int maxDist){
        // 가능 여부 확인
        int neededStation = 0;
        for(int i = 0; i < length.length; i++){
            neededStation += length[i] / maxDist;
            if(length[i] % maxDist == 0){
                neededStation -= 1;
            }
        }
        return neededStation <= M;
    }
}
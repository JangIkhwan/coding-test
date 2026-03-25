import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int N, K;
        int[] makgullis;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받는다
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        makgullis = new int[N];
        for(int i = 0; i < N; i++){
            makgullis[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(makgullis);

//        System.out.println(Integer.MAX_VALUE);

        // 파라매트릭 서치를 한다
        long lo = -1;
        long hi = (long)makgullis[N - 1] + 1; // 문제는 여기서 발생함
        while(lo + 1 < hi){
            long mid = (lo + hi) / 2;
            // 각 막걸리를 mid로 나눈 몫의 합이 K이상이면 lo = mid
            if(canDistribute(mid, makgullis, K)){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }

        // lo를 출력한다.
        System.out.println(lo);
    }

    private static boolean canDistribute(long portion, int[] makgullis, int K){
        if(portion == 0) return true;
        int sumOfDrinkers = 0;
        for(int i = 0; i < makgullis.length; i++){
            sumOfDrinkers += makgullis[i] / portion;
            if(sumOfDrinkers >= K){
                return true;
            }
        }
        return false;
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, K;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        int lo = 0;
        int hi = 1000000001;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(count(mid, N) >= K){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        System.out.println(hi);
    }

    private static int count(int number, int N){
        int count = 0;
        for(int i = 1; i <= N; i++) {
            count += Math.min(N, number / i);
        }
        return count;
    }
}
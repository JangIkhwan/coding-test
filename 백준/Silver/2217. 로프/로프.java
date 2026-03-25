import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N;
        int[] rope;
        N = Integer.parseInt(br.readLine());
        rope = new int[N];
        for(int i = 0; i < N; i++){
            rope[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rope);

        int maxWeight = 0;
        for(int i = 0; i < N; i++){
            int lo = -1;
            int hi = N;
            while(lo + 1 < hi){
                int mid = (lo + hi) / 2;
                if(rope[mid] >= rope[i]){
                    hi = mid;
                }
                else{
                    lo = mid;
                }
            }
            int numberOfRope = N - hi;
            int weight = numberOfRope * rope[i];
            if(weight > maxWeight){
                maxWeight = weight;
            }
        }

        System.out.println(maxWeight);
    }
}
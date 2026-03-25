import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    /*
    25/07/01 22:03 ~
     */

    public static void main(String[] args) throws IOException{
        int N, M;
        int[] trees;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        line = br.readLine();
        st = new StringTokenizer(line);
        trees = new int[N];
        int maxHeight = 0;
        for(int i = 0; i < N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
            if(trees[i] > maxHeight){
                maxHeight = trees[i];
            }
        }

        int hi = maxHeight + 1;
        int lo = -1;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(sumOfLogs(trees, mid) >= M){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }
        System.out.println(lo);

    }

    private static long sumOfLogs(int[] trees, int cuttingHeight){
        long sum = 0;
        for(int height : trees){
            if(height > cuttingHeight){
                sum += height - cuttingHeight;
            }
        }
        return sum;
    }
}
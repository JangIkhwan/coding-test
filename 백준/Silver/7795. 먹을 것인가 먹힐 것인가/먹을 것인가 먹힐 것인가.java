import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T;
        int a, b;
        int[] A;
        int[] B;
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            A = new int[a];
            B = new int[b];
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int i = 0; i < a; i++){
                A[i] = Integer.parseInt(st.nextToken());
            }
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int i = 0; i < b; i++){
                B[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(A);
            int pairCount = 0;
            for(int i = 0; i < b; i++){
                int ret = binarySearch(A, B[i]);
                if(ret < A.length && ret > -1){
                    pairCount += (A.length - ret);
                }
            }
            bw.write(Integer.toString(pairCount) + "\n");
        }
        bw.flush();
    }

    private static int binarySearch(int[] A, int key){
        int lo = -1;
        int hi = A.length;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(A[mid] <= key){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }
        return hi;
    }
}
import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, C;
    private static int[] houses;

    private static int minimumDist = 2123456789;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(findMaximumDist());
    }

    private static void init() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];
        for(int i = 0; i < N; i++){
            line = br.readLine();
            houses[i] = Integer.parseInt(line);
        }
        Arrays.sort(houses);
        for(int i = 1; i < N; i++){
            int dist = houses[i] - houses[i - 1];
            if(minimumDist > dist){
                minimumDist = dist;
            }
        }
    }

    private static int findMaximumDist(){
        int lo = minimumDist;
        int hi = houses[N - 1] - houses[0] + 1;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(canPlaceAccessPoints(mid)){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }
        return lo;
    }

    private static boolean canPlaceAccessPoints(int length){
        int accessPointNumber = 1;
        int dist = 0;
        for(int current = 1; current < N; current++){
            dist += houses[current] - houses[current - 1];
            if(dist >= length){
                accessPointNumber++;
                dist = 0;
            }
            if(accessPointNumber >= C){
                return true;
            }
        }
        return false;
    }
}
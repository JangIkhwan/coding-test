import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        long[] distances = new long[N];
        long[] prices = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N -1; i++){
            distances[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            prices[i] = Long.parseLong(st.nextToken());
        }
        long totalCost = 0;
        long minPrice = prices[1];
        for(int city = 1; city < N; city++){
            if(prices[city] < minPrice) minPrice = prices[city];
            totalCost += minPrice * distances[city];
        }
        System.out.println(totalCost);
    }

}
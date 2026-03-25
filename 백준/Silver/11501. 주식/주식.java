import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T, N;
        int[] stock;
        T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            stock = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i =0; i < N; i++){
                stock[i] = Integer.parseInt(st.nextToken());
            }

            long maxProfit = 0;
            int maxStock = stock[N - 1];
            for(int i = N - 2; i >= 0; i--){
                if(maxStock < stock[i]){
                    maxStock = stock[i];
                    continue;
                }
                maxProfit += maxStock - stock[i];
            }

            System.out.println(maxProfit);
        }
    }
}
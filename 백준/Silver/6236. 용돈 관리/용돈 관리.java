import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] money;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        money = new int[N];
        int maxMoney = 0;
        int sum = 0;
        for(int i = 0; i < N; i++){
            money[i] = Integer.parseInt(br.readLine());
            maxMoney = Math.max(maxMoney, money[i]);
            sum += money[i];
        }

        int lo = maxMoney - 1;
        int hi = sum + 1;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(isPossible(mid)){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        System.out.println(hi);
    }

    private static boolean isPossible(int k){
        // k원씩 m번 나누어주기가 가능한가?
        int group = 0;
        int amount = 0;
        for(int i = 0; i < N; i++){
            amount += money[i];
            if(amount > k){
                group++;
                amount = money[i];
            }
        }
        if(amount > 0){
            group++;
        }
        return group <= M;
    }
}